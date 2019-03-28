const Job = require("../lib/job");


describe("Jenkins Job Testing", () => {

    let oJob;

	beforeAll(() => {
		oJob = new Job({
			workSpace: "./data/job/B1_SMP_PUM"
		});
	});

    it("should get the job name as 'B1_SMP_PUM'", () => {
        expect(oJob.getJobBaseName()).toEqual("B1_SMP_PUM");
    });

    it("should get the job last number as 79", () => {
        expect(oJob.getLastBuildNumber()).toEqual(79);
    });

});