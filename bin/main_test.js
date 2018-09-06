#! /usr/bin/env node

var Project = require("../lib/project");
var Job = require("../lib/job");
var hanaDb = require("../lib/hanadb");
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
if(Argv.h || Argv.help){
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

// var sWorkSpace = Argv.p || aArgv[0] || "./";
var sWorkSpace = "../data/workspace/B1_SMP_PUM"; //Use "../data/B1_SMP_PUM" for UI5 code debug purpose
// var sWorkSpace = "../data/workspace/BCD_ABAP_UT"; //Use "../data/BCD_ABAP_UT" for ABAP code debug purpose

var oProject = new Project({
	workSpace: sWorkSpace,
	projectId: Argv.i
});

var sProjectType = oProject.getProjectType();
//Need to specify project id when run sccd command for an back-end jenkins job
if(sProjectType === Project.Type.BackEnd && !Argv.i){
	console.log("Project id is mandatory when it is a back-end service job for UI5 app.");
	process.exit();
}

//${GIT_BRANCH} has a prefix "origin/", while ${GERRIT_BRANCH} does not
var sBranch = "master";
if(Argv.b && Argv.b.match(/^.*\/(\w+)$/)){
	sBranch = (Argv.b.match(/^.*\/(\w+)$/))[1];
}else if(Argv.b){
	sBranch = Argv.b;
}else if(aArgv[1] && aArgv[1].match(/^.*\/(\w+)$/)){
	sBranch = (aArgv[1].match(/^.*\/(\w+)$/))[1];
}else if(aArgv[1]){
	sBranch = aArgv[1];
}
console.log("Get branch name: " + sBranch);

//Save project information
oProject.getProjectId().then(function(sProjectId){

	var oHana = new hanaDb();
	var oContent = {
		"ProjectId": sProjectId,
		"Type": sProjectType,
		"Name": sProjectName
	}
	// console.log(oContent);
	oHana.post("ProjectSet", oContent);

}).catch(function(sReason){
	console.log("Save project information failed: " + sReason);
});


//Save project test KPI
Promise.all([oProject.getProjectId(), oProject.getTestKpi(), oProject.getUTCoverage()]).then(function(aResult){
	var sProjectId = aResult[0], oKpi = aResult[1], oCoverage = aResult[2];
	var sTimestamp = ToolKit.getTimestamp(new Date());

	(Object.keys(Project.TestType)).forEach(function(sTestTypeKey){
		var sTestType = Project.TestType[sTestTypeKey];

		if(oKpi[sTestType].assertion){
			var oHana = new hanaDb();
			var oContent = Object.assign({
				"Guid": undefined,
				"Projectid": sProjectId,
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
			// console.log(sTestType + ":\n");
			if (sTestType == "UT") {
				// console.log(oContent);
				oHana.post("UTSet", oContent);
			} else if (sTestType == "IT") {
				// console.log(oContent);
				oHana.post("ITSet", oContent);
			}
		}
	});
}).catch(function(sReason){
	console.log("Save test kpi failed: " + sReason);
});



var oJob = new Job({
	workSpace: sWorkSpace
});

oProject.getProjectId().then(function(sProjectId){
	var sTestType = null;
	(Object.keys(Project.TestType)).every(function(sTestTypeKey){
		if(!!oProject.getTestReportPath(Project.TestType[sTestTypeKey])){
			sTestType = Project.TestType[sTestTypeKey];
			return false;
		}
	});
	if(!sTestType){
		console.log("Cannot determine test type of the job.");
		return;
	}

	var sJobName = oJob.getJobBaseName();

	var oHana = new hanaDb();
	var oContent = {
		"ProjectId": sProjectId,
		"ProjectType": sProjectType,
		"TestType": sTestType,
		"Name": sJobName,
		"LastBuild": oJob.getLastBuildNumber()
	}
	oHana.post("JobSet", oContent);


}).catch(function(sReason){
	console.log("Save job information failed: " + sReason);
});

// if(!Argv.k && !Argv.keep){
// 	// Job cleanup because of jenkins memory space limitation
// 	if(sProjectType === Project.Type.FrontEnd){ //ABAP UT does not consume too much space. So we not do cleanup here. However daily job wil still cleanup its data
// 		oJob.deleteJobNoKeepFiles();
// 	}
// 	oJob.deleteUIArtifact();
// }