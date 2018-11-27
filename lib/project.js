var FS = require("fs");
var Path = require("path");
var File = require("./file");


module.exports = Project;

/**
  	Project Type: 
		(1) FrontEnd: Front-end project, such as UI5 code of Fiori app
		(2) BackEnd: Back-end project, such as ABAP gateway service of Fiori app
*/
Project.Type = {
	FrontEnd: "UI5",
	BackEnd: "ABA"
};
/**
  	Test Type: 
		(1) Unit: Unit Test
		(2) Integration: Integration Test
		(3) System: System Test
	Values of test types here are the same with names of the corresponding database tables 
*/
Project.TestType = {
	Unit: "UT",
	Integration: "IT",
	System: "ST"
};
/**
  	Unit Test Coverage Type: 
		(1) Included: Only calculate files which included in unit test 
		(2) All: Calculate all valid files
*/
Project.UTCoverageType = {
	Included: "Included",
	All: "All"
};


function Project(options){
	this._sReportPath = this._sCoveragePath = this._sWorkSpacePath = options.workSpace;
	this._sProjectId = options.projectId;

	if(this.getProjectType() === Project.Type.FrontEnd){
		this._sReportPath = Path.join(this._sWorkSpacePath, "/target/surefire-reports");
		this._sCoveragePath = Path.join(this._sWorkSpacePath, "/target/jscoverage");
	}

	this.oKpi = {};
	Object.keys(Project.TestType).forEach(function(sTestType){
		this.oKpi[Project.TestType[sTestType]] = { passed: 0, failed: 0, skipped: 0, assertion: 0 };
	}.bind(this));

	this.oUTCoverage = {};
	Object.keys(Project.UTCoverageType).forEach(function(sType){
		this.oUTCoverage[Project.UTCoverageType[sType]] = { validLines: 0, lineRate: 0 };
	}.bind(this));
};

Project.prototype.getProjectType = function(){
	if(this._sProjectType){
		return this._sProjectType;
	}
	// Here, we determine project type based on jenkins workspace folder structure
	var oFile = new File({
		path: Path.join(this._sWorkSpacePath, "/webapp")
	});
	if(oFile.isExist()){
		return this._sProjectType = Project.Type.FrontEnd;
	}else{
		return this._sProjectType = Project.Type.BackEnd;
	}
};

Project.prototype.getProjectId = function(){
	if(this._sProjectId){
		return Promise.resolve(this._sProjectId);
	}else if(this.getProjectType() === Project.Type.FrontEnd){
		return this.getProjectIdFromUI5Manifest().then(function(sProjectId){
			if(sProjectId){
				return Promise.resolve(sProjectId);
			}else{
				return this.getProjectIdFromUI5Pom.call(this);
			}
		}.bind(this));
	}else{
		Promise.reject(null);
	}
};

Project.prototype.getProjectIdFromUI5Manifest = function(){
	return new Promise(function(resolve, reject){
		var sManiPath = Path.join(this._sWorkSpacePath, "webapp", "manifest.json");
		var oFileMani = new File({
			path: sManiPath
		});

		if(oFileMani.isExist()){
			var oRegExp = new RegExp(/^\s*"?id"?\s*:\s*"?(sap[\w\.]+)"?/);
			oFileMani.readLineByLine(function(sLine){
				if(oRegExp.test(sLine)){
					this._sProjectId = oRegExp.exec(sLine)[1];
					resolve(this._sProjectId);
				}
			}, function(){
				console.log("Read file " + sManiPath + " finished.");
				resolve(null);
			});
		}else{
			resolve(null);
		}
	}.bind(this));	
};

Project.prototype.getProjectIdFromUI5Pom = function(){
	return new Promise(function(resolve, reject){
		var sPomPath = Path.join(this._sWorkSpacePath, "pom.xml");
		var oFilePom = new File({
			path: sPomPath
		});

		if(oFilePom.isExist()){
			var bParentGroup = false;
			var oRegExp = new RegExp(/^\s*<groupId>(sap[\w\.]+)<\/groupId>\s*$/);
			oFilePom.readLineByLine(function(sLine){
				if(sLine.match(/^\s*<parent>/)){
					bParentGroup = true;
				}else if(sLine.match(/^\s*<\/parent>/)){
					bParentGroup = false;
				}else if(!bParentGroup && oRegExp.test(sLine)){
					this._sProjectId = oRegExp.exec(sLine)[1];
					resolve(this._sProjectId);
				}
			}, function(){
				console.log("Read file " + sPomPath + " finished.");
				resolve(null);
			});
		}else{
			resolve(null);
		}
	}.bind(this));
};

Project.prototype.getTestReportPath = function(sTestType){
	var sTestReportPath, aReportPattern;
	switch(sTestType){
		case Project.TestType.Unit: aReportPattern = [new RegExp(/(^|\.)unitTests?\./i), new RegExp(/\.indexUnit\./i)];
				break;
		case Project.TestType.Integration: aReportPattern = [new RegExp(/\.opa5?Tests?\./i), new RegExp(/\.indexOpa5?\./i)];
		        break;
		case Project.TestType.System: aReportPattern = [new RegExp(/\.systemTests?\./i), new RegExp(/\.indexSystem\./i)];
		        break;
		default: return null;
	}
	FS.readdirSync(this._sReportPath).forEach(function(sFileName){
		var sFile = Path.join(this._sReportPath, sFileName);
		if(FS.statSync(sFile).isFile() && aReportPattern.some(function(oPattern){
			return oPattern.test(sFileName);
		})){
			sTestReportPath = sFile;
			return false;
		}
	}.bind(this));
	return sTestReportPath;
};

Project.prototype.getTestKpiByType = function(sTestType){
	return new Promise(function(resolve, reject){
		Object.keys(this.oKpi[sTestType]).forEach(function(sKey){
			this.oKpi[sTestType][sKey] = 0;
		}.bind(this));

		var sReportPath = this.getTestReportPath(sTestType);

		if(sReportPath){
			var oFile = new File({
				path: sReportPath
			});

			if(oFile.isExist()){
				oFile.beautifyXML(); //Need to reject the promise here if beautify failed
				/**
				  For front-end UI5 code, we match:
					oRegExp = new RegExp(/^\s*<testcase name=".*" tests="(\d+)" failures="(\d+)" errors="(\d+)"/i);
				  For back-end ABAP code, we match:  
					oRegExp = new RegExp(/^\s*<testsuite name=".*" tests="(\d+)" failures="(\d+)" errors="(\d+)" skipped="(\d+)"/i);
				*/
				var oRegExp = new RegExp(/^\s*<(testsuite|testcase|testsuites) name=".*" tests="(\d+)" failures="(\d+)" errors="(\d+)" (?:skipped="(\d+)")?/i);
				oFile.readLineByLine(function(sLine){
					var aMatchKpi = oRegExp.exec(sLine);
					if(aMatchKpi){
						if(aMatchKpi[1] === "testcase"){ //UI5 test report
							if(parseInt(aMatchKpi[2]) > 0){
								this.oKpi[sTestType].assertion += parseInt(aMatchKpi[2]);
								if(parseInt(aMatchKpi[3]) === 0 && parseInt(aMatchKpi[4]) === 0){
									this.oKpi[sTestType].passed++;
								}else{
									this.oKpi[sTestType].failed++;
								}
							}else{
								this.oKpi[sTestType].skipped++;
							}
						}else{ //ABAP test report
							if(parseInt(aMatchKpi[2]) > 0){
								this.oKpi[sTestType].assertion += (parseInt(aMatchKpi[2]) - parseInt(aMatchKpi[5]));
								this.oKpi[sTestType].passed += (parseInt(aMatchKpi[2]) - parseInt(aMatchKpi[3]) - parseInt(aMatchKpi[4]) - parseInt(aMatchKpi[5]));
								this.oKpi[sTestType].failed += (parseInt(aMatchKpi[3]) + parseInt(aMatchKpi[4]));
								this.oKpi[sTestType].skipped += parseInt(aMatchKpi[5]);
							}
						}
					}
				}.bind(this), function(){
					resolve(this.oKpi[sTestType]);
				}.bind(this));

			}else{
				resolve(this.oKpi[sTestType]);
			}
		}else{
			resolve(this.oKpi[sTestType]);
		}
	}.bind(this));
};

Project.prototype.getTestKpi = function(){
	if((Object.keys(this.oKpi)).every(function(sKpiType){
		return this.oKpi[sKpiType].assertion !== 0;
	}.bind(this))){
		return Promise.resolve(this.oKpi);
	}else{
		return Promise.all(Object.keys(this.oKpi).map(function(sKpiType){
			return this.getTestKpiByType(sKpiType);
		}.bind(this))).then(function(aKpi){
			return Promise.resolve(this.oKpi);
		}.bind(this));
	}
};


Project.prototype.getUTCoverageReportPath = function(sType){
	var sCoverageReportPath, aReportPattern;
	switch(sType){
		case Project.UTCoverageType.Included: aReportPattern = [new RegExp(/^coverage\.xml$/i), new RegExp(/^jscoverage\.xml_ORIGINAL$/i)];
				break;
		case Project.UTCoverageType.All: aReportPattern = [new RegExp(/^cobertura\.xml$/i), new RegExp(/^jscoverage\.xml$/i)];
		        break;
		default: return null;
	}
	FS.readdirSync(this._sCoveragePath).forEach(function(sFileName){
		var sFile = Path.join(this._sCoveragePath, sFileName);
		if(FS.statSync(sFile).isFile() && aReportPattern.some(function(oPattern){
			return oPattern.test(sFileName);
		})){
			sCoverageReportPath = sFile;
			return false;
		}
	}.bind(this));
	return sCoverageReportPath;
};

Project.prototype.getUTCoverageByType = function(sType){
	return new Promise(function(resolve, reject){
		Object.keys(this.oUTCoverage[sType]).forEach(function(sKey){
			this.oUTCoverage[sType][sKey] = 0;
		}.bind(this));

		var sReportPath = this.getUTCoverageReportPath(sType);

		if(sReportPath){
			var oFile = new File({
				path: sReportPath
			});

			if(oFile.isExist()){
				oFile.beautifyXML(); //Need to reject the promise here if beautify failed
				/**
				  For front-end UI5 code, we match:
					oRegExp = new RegExp(/^\s*<coverage files="\d+" line-rate="([0-1]\.\d+)" lines-covered="\d+" lines-valid="(\d+)"/i);
				  For back-end ABAP code, we match:  
					oRegExp = new RegExp(/^\s*<coverage line-rate="([0-1]\.\d+)" branch-rate="[0-1]\.\d+" lines-covered="\d+" lines-valid="(\d+)"/i);
				*/
				var oRegExp = new RegExp(/^\s*<coverage (?:files="\d+"\s{1})?line-rate="([0-1]\.\d+)" (?:branch-rate="[0-1]\.\d+"\s{1})?lines-covered="\d+" lines-valid="(\d+)"/i);
				oFile.readLineByLine(function(sLine){
					var aMatchKpi = oRegExp.exec(sLine);
					if(aMatchKpi){
						this.oUTCoverage[sType].lineRate = parseFloat(aMatchKpi[1]).toFixed(2);
						this.oUTCoverage[sType].validLines = parseInt(aMatchKpi[2]);
					}
				}.bind(this), function(){
					resolve(this.oUTCoverage[sType]);
				}.bind(this));
			}else{
				resolve(this.oUTCoverage[sType]);
			}
		}else{
			resolve(this.oUTCoverage[sType]);
		}
	}.bind(this));
};

Project.prototype.getUTCoverage = function(){
	if((Object.keys(this.oUTCoverage)).every(function(sType){
		return this.oUTCoverage[sType].lineRate !== 0;
	}.bind(this))){
		return Promise.resolve(this.oUTCoverage);
	}else{
		return Promise.all(Object.keys(this.oUTCoverage).map(function(sType){
			return this.getUTCoverageByType(sType);
		}.bind(this))).then(function(aKpi){
			return Promise.resolve(this.oUTCoverage);
		}.bind(this));
	}
};
