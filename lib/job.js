var FS = require("fs");
var PATH = require("path");
var Project = require("./project");
var ToolKit = require("./toolkit");


module.exports = Job;

Job.Config = {
	MaxKeepBuilds: 3
};

function Job(options){
	this._sWorkSpacePath = options.workSpace;
};

Job.prototype.getJobBaseName = function(){
	if(this._sWorkSpacePath){
		return this._sWorkSpacePath.substr(this._sWorkSpacePath.lastIndexOf("/") + 1);
	}
};

Job.prototype.getJobPath = function(){
	var sPath = PATH.join(this._sWorkSpacePath, "/../../jobs/" + this.getJobBaseName());
	return FS.existsSync(sPath) ? sPath:null;
};

Job.prototype.getLastBuildNumber = function(){
	var iBuild = 0;
	var sDir = this.getJobPath();
	if(sDir){
		FS.readdirSync(sDir).forEach(function(sFile){
			var sPath = PATH.join(sDir, sFile);
			if(sFile === "builds" && FS.existsSync(sPath) && FS.statSync(sPath).isDirectory()){
				FS.readdirSync(sPath).forEach(function(sBuild){
					if(sBuild.match(/^\d+$/) && parseInt(sBuild) > iBuild){
						iBuild = parseInt(sBuild);
					}
				});
			}
		});
	}
	return iBuild;
};

Job.prototype.travelJobNoKeepFiles = function(fnCallback){
	var sDir = this.getJobPath();
	if(sDir){
		var fnCallbackOnFile = fnCallback;
		FS.readdirSync(sDir).forEach(function(sFile){
			var sPath = PATH.join(sDir, sFile);
			if(FS.existsSync(sPath) && FS.statSync(sPath).isDirectory()){
				if(sFile === "builds"){
					//Only reserve the final 3 builds
					var aBuild = [], sBuildPath;
					FS.readdirSync(sPath).forEach(function(sBuild){
						sBuildPath = PATH.join(sPath, sBuild);
						if(sBuild.match(/^\d+$/)){
							aBuild.push(sBuildPath);
						}else{
							fnCallbackOnFile(sBuildPath);
						}
					}.bind(this));

					aBuild.sort().forEach(function(sBuildPath, index, aSortedBuild){
						if(index < aSortedBuild.length - Job.Config.MaxKeepBuilds){
							fnCallbackOnFile(sBuildPath);
						}
					});
				}else if(sFile === "modules" || sFile === "cobertura"){
					fnCallbackOnFile(sPath);
				}else{
					this.travelJobNoKeepFiles(sPath, fnCallbackOnFile);
				}
			}
		}.bind(this));
	}
};

Job.prototype.deleteJobNoKeepFiles = function(){
	this.travelJobNoKeepFiles(function(sPath){
		//console.log("Job No Keep File: " + sPath);
		ToolKit.deleteFile(sPath, function(){
			console.log("Delete Job No Keep File: " + sPath);
		});
	});
};

Job.prototype.deleteUIArtifact = function(){
	if(!this._oProject){
		this._oProject = new Project({
			workSpace: this._sWorkSpacePath
		});
	}

	if(this._oProject.getProjectType() === Project.Type.FrontEnd){
		this._oProject.getProjectId().then(function(sProjectId){
			var sPath = PATH.join(this._sWorkSpacePath, "/../../.m2/repository/" + sProjectId.replace(/\./g, "/"));
			//console.log("Job UI Artifact Folder: " + sPath);
			ToolKit.deleteFile(sPath, function(){
				console.log("Delete Job Artifact Folder: " + sPath);
			});
		}.bind(this)).catch(function(sReason){
			console.log("Get project id while delete UI artifact failed: " + sReason);
		});
	}
}; 