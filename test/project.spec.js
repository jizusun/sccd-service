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
    	oProject.getProjectId().then((sId) => {
    		expect(sId).toEqual("sap.b1.smp.pum");
    	});
    	/*const sId = await oProject.getProjectId();
    	expect(sId).toEqual("sap.b1.smp.pum");*/
    });
});

describe("ABAP Project Testing", () => {

	beforeAll(() => {
		oProject = new Project({
			workSpace: "./data/workspace/BCD_ABAP_UT",
			projectId: "sap.b1.smp.pum"
		});
	});

    it("should get the project type as ABAP", () => {
        expect(oProject.getProjectType()).toEqual(Project.Type.BackEnd);
    });

});