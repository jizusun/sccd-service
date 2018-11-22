#!/usr/bin/node

var HTTP = require("http");
var URL = require("url");
var DB = require("../lib/db");
var MySql = require("mysql");


var oServer = HTTP.createServer(function(request, response){
    var sUrl = request.url;
    console.log(sUrl);

    //Parse the URL and route to corresponding entityset
    var oUrl = URL.parse(sUrl, true);
    var sEntitySet, oUrlParam;
    if(oUrl.pathname.match(/(\w+)$/)){
        sEntitySet = oUrl.pathname.match(/(\w+)$/)[0];
        oUrlParam = oUrl.query;
        if(Object.keys(oUrlParam).length > 0){
            console.log("Get url parameter: " + JSON.stringify(oUrlParam));
        }

        //Match url parameter to DB table field value  
        if(oUrlParam.ptype){
            oUrlParam.ptype = oUrlParam.ptype.substr(0, 3).toUpperCase();
        }
    }

    response.writeHead(200, HTTP.STATUS_CODES[200], {
        'Content-Type': 'text/plain'
    });

    request.on("data", function(chunk){
        response.write("XC Test");
    });

    request.on("end", function(){

        if(sEntitySet === "KpiSet"){
            var sSqlTotalProject = "select count(*) totalProjects from Project where type='" + oUrlParam.ptype + "'";
            var sSqlTop3Active = "select top.projectId, top.projectName, top.commit_times, top.tcid, ut.passed, ut.failed, ut.passed+ut.failed totalUT, ut.branch, ut.type from ( select u.pid projectId, p.name projectName, count(*) commit_times, max(u.tcid) tcid from UT u, Project p where DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(timestamp) and u.pid = p.pid and u.type = '" + oUrlParam.ptype + "' and u.branch = 'master' group by u.pid, p.name ) top, UT ut where top.tcid = ut.tcid order by commit_times desc limit 3;";
            var sSqlHealthyUTProject = "select * from (select count(*) UT_TotalProject from (select t.pid, substring_index(group_concat(t.tcid), ',', -1) last_commit_id from (select u.tcid,   u.pid,   u.timestamp from UT u where timestamp > 0 and branch = 'master' and type = '" + oUrlParam.ptype + "' order by u.pid asc, timestamp desc) t group by t.pid) l left join UT ut on l.last_commit_id = ut.tcid) total,  (select count(*) UT_FailedProject from (select t.pid, substring_index(group_concat(t.tcid), ',', -1) last_commit_id from (select u.tcid,   u.pid,   u.timestamp from UT u where timestamp > 0 and branch = 'master' and type = '" + oUrlParam.ptype + "' order by u.pid asc, timestamp desc) t group by t.pid) l left join UT ut on l.last_commit_id = ut.tcid where ut.failed > 0) failed,  (select count(*) UT_PassedProject from (select t.pid, substring_index(group_concat(t.tcid), ',', -1) last_commit_id from (select u.tcid,   u.pid,   u.timestamp from UT u where timestamp > 0 and branch = 'master' and type = '" + oUrlParam.ptype + "' order by u.pid asc, timestamp desc) t group by t.pid) l left join UT ut on l.last_commit_id = ut.tcid where ut.failed = 0) passed";
            var sSqlHealthyITProject = "select * from (select count(*) IT_TotalProject from (select i.pid, substring_index(group_concat(i.tcid), ',', -1) last_commit_id from (select i.tcid, i.pid, i.timestamp from IT i where timestamp > 0 and branch = 'master' and type='" + oUrlParam.ptype + "' order by i.pid asc, i.timestamp desc) i group by i.pid) l left join IT it on l.last_commit_id = it.tcid) total, (select count(*) IT_FailedProject from (select i.pid, substring_index(group_concat(i.tcid), ',', -1) last_commit_id from (select i.tcid, i.pid, i.timestamp from IT i where timestamp > 0 and branch = 'master' and type='" + oUrlParam.ptype + "' order by i.pid asc, i.timestamp desc) i group by i.pid) l left join IT it on l.last_commit_id = it.tcid where it.failed > 0) failed, (select count(*) IT_PassedProject from (select i.pid, substring_index(group_concat(i.tcid), ',', -1) last_commit_id from (select i.tcid, i.pid, i.timestamp from IT i where timestamp > 0 and branch = 'master' and type='" + oUrlParam.ptype + "' order by i.pid asc, i.timestamp desc) i group by i.pid) l left join IT it on l.last_commit_id = it.tcid where it.failed = 0) passed";

            var aKpiSql = [sqlPromise(sSqlTotalProject), sqlPromise(sSqlTop3Active), sqlPromise(sSqlHealthyUTProject), sqlPromise(sSqlHealthyITProject)];
            Promise.all(aKpiSql).then(function(aKpi){
                response.end(JSON.stringify({
                    "totalProjects": aKpi[0][0].totalProjects,
                    "topActive": aKpi[1],
                    "UTStatus": {
                        totalUTProjects: aKpi[2][0].UT_TotalProject,
                        healthyUTProjects: aKpi[2][0].UT_PassedProject,
                        unhealthyUTProjects: aKpi[2][0].UT_FailedProject
                    },
                    "ITStatus": {
                        totalITProjects: aKpi[3][0].IT_TotalProject,
                        healthyITProjects: aKpi[3][0].IT_PassedProject,
                        unhealthyITProjects: aKpi[3][0].IT_FailedProject,
                    }
                }));
            }, function(sErrorMsg){
                console.log(sErrorMsg); //write header instead
            });

        }else{
            var sQuery = null, bEntity = false;
            switch(sEntitySet){
                case "HomeSet":
                    sQuery = "SELECT ut.projectId, ut.projectName, ut.teamId, ut.teamName, ut.inclstmtlines AS includedLine, ut.inclstmtcover AS includedCover, ut.allstmtlines AS allLine, ut.allstmtcover AS allCover, ut.passed + ut.failed AS totalUT, ut.assertion AS UTAssertion, it.passed + ut.failed AS totalIT, it.assertion AS ITAssertion FROM ( SELECT ut.tcid AS tcid, ut.pid AS projectId, p.name AS projectName, p.team AS teamId, t.name AS teamName, ut.inclstmtlines, ut.inclstmtcover, ut.allstmtlines, ut.allstmtcover, ut.passed AS passed, ut.failed AS failed, ut.assertion AS assertion, ut.branch AS branch, ut.timestamp AS timestamp FROM ( SELECT t.pid, substring_index(GROUP_CONCAT(t.tcid), ',', -1) AS last_commit_id FROM ( SELECT u.tcid, u.pid, u.timestamp FROM UT u WHERE timestamp > 0 AND branch = 'master' AND type = '" + oUrlParam.ptype + "' ORDER BY u.pid ASC, timestamp DESC ) t GROUP BY t.pid ) l LEFT JOIN UT ut ON l.last_commit_id = ut.tcid LEFT JOIN Project p ON ut.pid = p.pid LEFT JOIN Team t ON p.team = t.tid ) ut LEFT JOIN ( SELECT it.tcid AS tcid, it.pid AS projectId, p.name AS projectName, p.team AS teamId, t.name AS teamName, it.passed AS passed, it.failed AS failed, it.assertion AS assertion, it.branch AS branch, it.timestamp AS timestamp FROM ( SELECT i.pid, substring_index(GROUP_CONCAT(i.tcid), ',', -1) AS last_commit_id FROM ( SELECT i.tcid, i.pid, i.timestamp FROM IT i WHERE timestamp > 0 AND branch = 'master' AND type = '" + oUrlParam.ptype + "' ORDER BY i.pid ASC, i.timestamp DESC ) i GROUP BY i.pid ) l LEFT JOIN IT it ON l.last_commit_id = it.tcid LEFT JOIN Project p ON it.pid = p.pid LEFT JOIN Team t ON p.team = t.tid ) it ON ut.projectId = it.projectId ORDER BY ut.teamId DESC, ut.assertion DESC, it.assertion DESC";
                    break;

                case "UTSet":
                    /**
                     * Return unit test overview of all projects when project id is not specified,
                     *   and return unit test history of specical project when project id is provided. 
                     */
                    if(!oUrlParam.pid){
                        //sQuery = "select ut.tcid tcid, ut.pid projectId, p.name projectName, p.team teamId, t.name teamName, ut.passed passed, ut.failed failed, ut.assertion assertion, ut.skipped, ut.inclstmtlines includedLine, ut.inclstmtcover includedCover, ut.allstmtlines allLine, ut.allstmtcover allCover, ut.timestamp timestamp from (select t.pid, substring_index(group_concat(t.tcid), ',', -1) last_commit_id from (select u.tcid, u.pid, u.timestamp from UT u where timestamp > 0 and branch = 'master' order by u.pid asc, timestamp desc) t group by t.pid) l left join UT ut on l.last_commit_id = ut.tcid left join Project p on ut.pid = p.pid left join Team t on p.team = t.tid order by p.team desc, ut.assertion desc";
                        sQuery = "SELECT ut.tcid AS tcid, ut.pid AS projectId, p.name AS projectName, p.team AS teamId, t.name AS teamName, ut.passed AS passed, ut.failed AS failed, ut.assertion AS assertion, ut.skipped, ut.inclstmtlines AS includedLine, ut.inclstmtcover AS includedCover, round(ut.inclstmtlines * ut.inclstmtcover) AS includedCoverLine, ut.inclstmtlines - round(ut.inclstmtlines * ut.inclstmtcover) AS notIncludedCoverLine, ut.allstmtlines AS allLine, ut.allstmtcover AS allCover, round(ut.allstmtlines * ut.allstmtcover) AS allCoverLine, ut.allstmtlines - ut.inclstmtlines AS notIncludedLine, ut.timestamp AS timestamp, ut.branch, ut.type FROM ( SELECT t.pid , substring_index(GROUP_CONCAT(t.tcid), ',', -1) AS last_commit_id FROM ( SELECT u.tcid, u.pid, u.timestamp FROM UT u WHERE timestamp > 0 AND branch = 'master' AND type = '" + oUrlParam.ptype + "' ORDER BY u.pid ASC, timestamp DESC ) t GROUP BY t.pid ) l LEFT JOIN UT ut ON l.last_commit_id = ut.tcid LEFT JOIN Project p ON ut.pid = p.pid LEFT JOIN Team t ON p.team = t.tid ORDER BY p.team DESC, ut.assertion DESC";
                    }else{
                        sQuery = "SELECT ut.tcid AS tcid, ut.pid AS projectId, p.name AS projectName, ut.passed AS passed, ut.failed AS failed, ut.passed + ut.failed AS allTestCases, ut.assertion AS assertion, ut.skipped, ut.inclstmtlines AS includedLine, ut.inclstmtcover AS includedCover, ut.allstmtlines AS allLine, ut.allstmtcover AS allCover, p.team AS teamId, t.name AS teamName, ut.timestamp AS timestamp, ut.branch, ut.type FROM UT ut LEFT JOIN Project p ON ut.pid = p.pid LEFT JOIN Team t ON p.team = t.tid WHERE ut.pid = '" + oUrlParam.pid + "' AND ut.branch = 'master' AND ut.type = '" + oUrlParam.ptype + "' ORDER BY timestamp DESC LIMIT 30";
                    }
                    break;
               
                case "ITSet":
                    /**
                     * Return integration test overview of all projects when project id is not specified,
                     *   and return integration test history of specical project when project id is provided. 
                     */
                    if(!oUrlParam.pid){
                        sQuery = "SELECT it.tcid AS tcid, it.pid AS projectId, p.name AS projectName, p.team AS teamId, t.name AS teamName, it.passed AS passed, it.failed AS failed, it.assertion AS assertion, it.skipped, it.branch AS branch, it.timestamp AS timestamp, it.branch, it.type FROM ( SELECT i.pid , substring_index(GROUP_CONCAT(i.tcid), ',', -1) AS last_commit_id FROM ( SELECT i.tcid, i.pid, i.timestamp FROM IT i WHERE timestamp > 0 AND branch = 'master' AND type = '" + oUrlParam.ptype + "' ORDER BY i.pid ASC, i.timestamp DESC ) i GROUP BY i.pid ) l LEFT JOIN IT it ON l.last_commit_id = it.tcid LEFT JOIN Project p ON it.pid = p.pid LEFT JOIN Team t ON p.team = t.tid ORDER BY p.team DESC, it.assertion DESC";
                    }else{
                        sQuery = "SELECT it.tcid AS tcid, it.pid AS projectId, p.name AS projectName, it.passed AS passed, it.failed AS failed, it.passed + it.failed AS allTestCases, it.assertion AS assertion, it.skipped, p.team AS teamId, t.name AS teamName, it.timestamp AS timestamp, it.branch, it.type FROM IT it LEFT JOIN Project p ON it.pid = p.pid LEFT JOIN Team t ON p.team = t.tid WHERE it.pid = '" + oUrlParam.pid + "' AND it.branch = 'master' AND it.type = '" + oUrlParam.ptype + "' ORDER BY timestamp DESC LIMIT 30";
                    }
                    break;

                case "F4ProjectSet":
                    sQuery = "SELECT p.pid projectId, p.name projectName, p.contact projectContact, p.team teamId, t.name teamName, t.contact teamContact FROM Project p LEFT JOIN Team t ON (p.team = t.tid)";
                    break;

                case "JobSet": 
                    sQuery = "SELECT name, lastbuild FROM Job WHERE pid = '" + oUrlParam.pid + "' AND ptype='" + oUrlParam.ptype + "' AND ttype='" + oUrlParam.ttype + "'";
                    break;

                case "TeamSet":
                    sQuery = "SELECT * FROM Team";
                    break;
            }

            if(sQuery){
                //var oDB = new DB({ name: "sccd" });
                sqlPromise(sQuery).then(function(aResult){
                    response.end(JSON.stringify(bEntity ? aResult[0] : aResult));
                }, function(sErrorMsg){
                    console.log(sErrorMsg); //write header instead
                });
            }
        }
    });
}).listen(1519);


function sqlPromise(sSql){
    return new Promise(function(resolve, reject){
        try{
            var oDB = MySql.createConnection({
                host: "localhost",
                user: "guest",
                password: "guest",
                port: "3306",
                database: "sccd"
            });

            oDB.connect();

            oDB.query(sSql, function(oError, aResult){
                if(oError){
                    reject("[Database error] - " + oError.message);
                }

                resolve(aResult);
            });

            oDB.end();

        }catch(error){
            reject(error);
        }
    });
}
