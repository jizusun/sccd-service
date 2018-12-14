#! /usr/bin/env node

var Project = require("../lib/project");
var Job = require("../lib/job");
var DB = require("../lib/db");
var HanaDB = require("../lib/hanadb");
var ToolKit = require("../lib/toolkit");
var Argv = require("optimist").boolean("cors").argv;

/***
	Run the program in CLI like:
	  node main.js -p <jenkins_job_workspace_path> -b <branch_name>
	Or
	  node main.js <jenkins_job_workspace_path> <branch_name>
	Or
	  sccd <jenkins_job_workspace_path> <branch_name> //on jenkins server
	Or
	  sccd -p <jenkins_job_workspace_path> -b <branch_name> -i <project_id> //on jenkins server
	You can get the first CLI parameter through process.argv[2]
*/
if (Argv.h || Argv.help) {
	console.log([
		'',
		'usage: node main.js [options]',
		'',
		'options:',
		'  -p           Jenkins job workspace path. e.g.:/var/lib/jenkins/workspace/B1_SMP_PUM',
		'  -i           Project id. This is mandatory when it is a back-end service job for UI5 app',
		'  -b           Branch which test case run on [master]',
		'  -k --keep    Keep jenkins job log files: artifact, builds, maven repository...',
		'  -h --help    Print this list and exit.'
	].join('\n'));
	process.exit();
}


var aArgv = process.argv.slice(2);

var sWorkSpace = Argv.p || aArgv[0] || "./";
// var sWorkSpace = "../data/workspace/B1_SMP_PUM"; //Use "../data/B1_SMP_PUM" for UI5 code debug purpose
//var sWorkSpace = "../data/workspace/BCD_ABAP_UT"; //Use "../data/BCD_ABAP_UT" for ABAP code debug purpose
//var sWorkSpace = "../data/workspace/systemdata";

var oProject = new Project({
	workSpace: sWorkSpace,
	projectId: Argv.i
});

var sProjectType = oProject.getProjectType();
//Need to specify project id when run sccd command for an back-end jenkins job
if (sProjectType === Project.Type.BackEnd && !Argv.i) {
	console.log("Project id is mandatory when it is a back-end service job for UI5 app.");
	process.exit();
}

//${GIT_BRANCH} has a prefix "origin/", while ${GERRIT_BRANCH} does not
var sBranch = "master";
if (Argv.b && Argv.b.match(/^.*\/(\w+)$/)) {
	sBranch = (Argv.b.match(/^.*\/(\w+)$/))[1];
} else if (Argv.b) {
	sBranch = Argv.b;
} else if (aArgv[1] && aArgv[1].match(/^.*\/(\w+)$/)) {
	sBranch = (aArgv[1].match(/^.*\/(\w+)$/))[1];
} else if (aArgv[1]) {
	sBranch = aArgv[1];
}

//Save project information
oProject.getProjectId().then(function(sProjectId) {
	var sProjectName = sProjectId.substr(sProjectId.lastIndexOf(".") + 1);

	/*  MySQL DB  */
	var oDB = new DB({
		name: "sccd"
	});
	oDB.insertNewUpdateExistDBItem(false, {
		table: "Project",
		keys: {
			pid: sProjectId,
			type: sProjectType
		},
		values: {
			name: sProjectName
		}
	});

	/*  Hana DB  */
	var oHana = new HanaDB();
	var oContent = {
		"ProjectId": sProjectId,
		"ProjectType": sProjectType,
		"ProjectName": sProjectName
	}
	oHana.post("dev", "ProjectSet", oContent);
	oHana.post("qual", "ProjectSet", oContent);

}).catch(function(sReason) {
	console.log("Save project information failed: " + sReason);
});


//Save project test KPI
Promise.all([oProject.getProjectId(), oProject.getTestKpi(), oProject.getUTCoverage()]).then(function(aResult) {
	var sProjectId = aResult[0],
		oKpi = aResult[1],
		oCoverage = aResult[2];
	var sTimestamp = ToolKit.getTimestamp(new Date());

	(Object.keys(Project.TestType)).forEach(function(sTestTypeKey) {
		var sTestType = Project.TestType[sTestTypeKey];

		if (oKpi[sTestType].assertion) {
			console.log("Get " + sTestType + " test kpi: passed-" + oKpi[sTestType].passed + ", " +
														"failed-" + oKpi[sTestType].failed + ", " +
														"skipped-" + oKpi[sTestType].skipped + ", " +
														"assertion-" + oKpi[sTestType].assertion +
														(sTestType === Project.TestType.Unit ?
															(", included stmt lines-" + oCoverage.Included.validLines + ", " + 
															"included stmt coverage-" + oCoverage.Included.lineRate + ", " + 
															"all stmt lines-" + oCoverage.All.validLines + ", " + 
															"all stmt coverage-" + oCoverage.All.lineRate):"")
														);

			/*  MySQL DB  */
			var oDB = new DB({
				name: "sccd"
			});
			oDB.insertNewUpdateExistDBItem(true, {
				table: sTestType,
				keys: {
					pid: sProjectId,
					type: sProjectType,
					tcid: undefined //auto_increment DB field MUST BE passed as undefined
				},
				specialCondition: "timestamp LIKE '" + sTimestamp.slice(0, 8) + "%'",
				values: Object.assign({
					branch: sBranch,
					passed: oKpi[sTestType].passed,
					failed: oKpi[sTestType].failed,
					skipped: oKpi[sTestType].skipped,
					assertion: oKpi[sTestType].assertion,
					timestamp: sTimestamp
				}, (sTestType === Project.TestType.Unit ? {
					inclstmtlines: oCoverage.Included.validLines,
					inclstmtcover: oCoverage.Included.lineRate,
					allstmtlines: oCoverage.All.validLines,
					allstmtcover: oCoverage.All.lineRate
				} : {}))
			});

			/*  Hana DB  */
			var oHana = new HanaDB();
			var oContent = Object.assign({
				"ProjectId": sProjectId,
				"Type": sProjectType,
				"Branch": sBranch,
				"Passed": oKpi[sTestType].passed,
				"Failed": oKpi[sTestType].failed,
				"Skipped": oKpi[sTestType].skipped,
				"Assertion": oKpi[sTestType].assertion,
				"Timestamp": sTimestamp
			}, (sTestType === Project.TestType.Unit ? {
				"Inclstmtlines": oCoverage.Included.validLines,
				"Inclstmtcover": oCoverage.Included.lineRate,
				"Allstmtlines": oCoverage.All.validLines,
				"Allstmtcover": oCoverage.All.lineRate
			}:{}));

			if (sTestType == "UT") {
				oHana.post("dev", "UTSet", oContent);
				oHana.post("qual", "UTSet", oContent);
			} else if (sTestType == "IT") {
				oHana.post("dev", "ITSet", oContent);
				oHana.post("qual", "ITSet", oContent);
			}

		}
	});
}).catch(function(sReason) {
	console.log("Save test kpi failed: " + sReason);
});



var oJob = new Job({
	workSpace: sWorkSpace
});

oProject.getProjectId().then(function(sProjectId) {
	var sTestType = null;
	/*(Object.keys(Project.TestType)).every(function(sTestTypeKey) {
		if (!!oProject.getTestReportPath(Project.TestType[sTestTypeKey])) {
			sTestType = Project.TestType[sTestTypeKey];
			return false;
		}
	});*/
	
	var aTestType = Object.keys(Project.TestType);
	for(var i=0; i<aTestType.length; i++){
		if (!!oProject.getTestReportPath(Project.TestType[aTestType[i]])) {
			sTestType = Project.TestType[aTestType[i]];
			break;
		}
	}
	
	if (!sTestType) {
		console.log("Cannot determine test type of the job.");
		return;
	}

	var sJobName = oJob.getJobBaseName();

	/*  MySQL DB  */
	var oDB = new DB({
		name: "sccd"
	});
	oDB.insertNewUpdateExistDBItem(true, {
		table: "Job",
		keys: {
			pid: sProjectId,
			ptype: sProjectType,
			ttype: sTestType
		},
		values: {
			name: sJobName,
			lastbuild: oJob.getLastBuildNumber()
		}
	});

	/*  Hana DB  */
	var oHana = new HanaDB();
	var oContent = {
		"ProjectId": sProjectId,
		"ProjectType": sProjectType,
		"TestType": sTestType,
		"Name": sJobName,
		"LastBuild": oJob.getLastBuildNumber()
	}
	oHana.post("dev", "JobSet", oContent);
	oHana.post("qual", "JobSet", oContent);

}).catch(function(sReason) {
	console.log("Save job information failed: " + sReason);
});

if (!Argv.k && !Argv.keep) {
	// Job cleanup because of jenkins memory space limitation
	if (sProjectType === Project.Type.FrontEnd) { //ABAP UT does not consume too much space. So we not do cleanup here. However daily job wil still cleanup its data
		oJob.deleteJobNoKeepFiles();
	}
	oJob.deleteUIArtifact();
}
