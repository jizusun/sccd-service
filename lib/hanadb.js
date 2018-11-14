"use strict";

var HTTPS = require("https");

function DB(){}

function setOptions(sSystem, sMethod, sPath, oHeader) {
    return {
        hostname: (sSystem === "qual" ? "spwdfvm2367.dmzwdf.sap.corp":"i7d.wdf.sap.corp"),
        method: sMethod,
        auth: "SAL_DEV_OPS:Initial1",
        rejectUnauthorized: false,
        path: "/odata/sccd/" + sPath,
        headers: Object.assign({
            "X-Requested-With": "XMLHttpRequest",
            "Content-Type": "application/json; charset=utf-8",
            "DataServiceVersion": "2.0"
        }, oHeader)
    }
}

DB.prototype.post = function(sSys, sGWName, oDContent) {
    var options = setOptions(sSys, "GET", "$metadata", {"X-CSRF-Token": "Fetch"});
    var reqToken = HTTPS.request(options, function(response) {
        var body = [];
        response.on("data", function(chunk) {
            body.push(chunk);
        });

        response.on("end", function() {
            options = setOptions(sSys, 'POST', sGWName, {"Cookie": response.headers["set-cookie"],"X-CSRF-Token": response.headers["x-csrf-token"]});
            var reqPost = HTTPS.request(options, function(response) {
                body = [];
                response.on("data", function(chunk) {
                    body.push(chunk);
                });

                response.on("end", function() {
                    if (response.statusCode == 400) {
                        // console.log(response.socket._events.error);
                    } else {
                        console.log(sGWName + " Table has been modified successfully.\n");
                    }
                });

            });
            var oContent = {
                "d": oDContent
            };
            reqPost.write(JSON.stringify(oDContent));
            reqPost.end();
        });
    });
    reqToken.end();
}

module.exports = DB;