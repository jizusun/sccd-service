const Toolkit = require("../lib/toolkit");
const FS = require("fs");
const CHILD_PROC = require("child_process");
const UTIL = require("util");

describe("Toolkit Testing", () => {

    /*it("'deleteFile' should delete file if it exist", () => {
        let sPath = "./ThisIsJustDummyFile.19";
        CHILD_PROC.exec(UTIL.format('touch %s', sPath));

        expect(FS.existsSync(sPath)).toBe(true);

        Toolkit.deleteFile(sPath, () => {
            expect(FS.existsSync(sPath)).toBe(false);
        });
    });*/

    it("'getTimestamp' should format Date(Thu Mar 28 2019 15:20:36) as 20190328152036", () => {
        expect(Toolkit.getTimestamp(new Date("Thu Mar 28 2019 15:20:36"))).toEqual("20190328152036");
    });

});