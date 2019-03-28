const Project = require("../lib/project");

let oProject;

describe("UI5 Project Testing", () => {

	beforeAll(() => {
		oProject = new Project({
			workSpace: "./data/workspace/B1_SMP_PUM"
		});
	});

    it("should get the project type as UI5", () => {
        expect(oProject.getProjectType()).toEqual(Project.Type.FrontEnd);
    });

    it("should get the correct project id 'sap.b1.smp.pum'", () => {
    	return oProject.getProjectId().then((sId) => {
    		expect(sId).toEqual("sap.b1.smp.pum");
    	});
    });

    it("should get correct test kpi", () => {
    	return oProject.getTestKpi().then((oKpi) => {
    		expect(oKpi[Project.TestType.Unit]).toEqual({ passed: 226, failed: 0, skipped: 0, assertion: 544 });
    		expect(oKpi[Project.TestType.Integration]).toEqual({ passed: 32, failed: 2, skipped: 0, assertion: 39 });
    	});
    });

    it("should get correct unit test coverage", () => {
        return oProject.getUTCoverage().then((oCoverage) => {
            expect(oCoverage).toEqual({
                Included: { validLines: 858, lineRate: '0.92' },
                All: { validLines: 2819, lineRate: '0.28' }
            });
        });
    });
});

describe("ABAP Project Testing", () => {

	beforeAll(() => {
		oProject = new Project({
			workSpace: "./data/workspace/BCD_ABAP_UT",
			projectId: "sap.support.bcd"
		});
	});

    it("should get the project type as ABAP", () => {
        expect(oProject.getProjectType()).toEqual(Project.Type.BackEnd);
    });

    it("should get the correct project id 'sap.b1.smp.pum'", () => {
        return oProject.getProjectId().then((sId) => {
            expect(sId).toEqual("sap.support.bcd");
        });
    });

    it("should get correct test kpi", () => {
        return oProject.getTestKpi().then((oKpi) => {
            expect(oKpi[Project.TestType.Unit]).toEqual({ passed: 1034, failed: 3, skipped: 0, assertion: 1037 });
        });
    });

    it("should get correct unit test coverage", () => {
        return oProject.getUTCoverage().then((oCoverage) => {
            expect(oCoverage).toEqual({
                Included: { validLines: 12753, lineRate: '0.61' },
                All: { validLines: 0, lineRate: 0 }
            });
        });
    });
});