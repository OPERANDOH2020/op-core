var core = require("swarmcore");
core.createAdapter("OSPRequests");
var apersistence = require('apersistence');
var container = require("safebox").container;
var flow = require("callflow");

apersistence.registerModel("OspRequest", "Redis", {
        userId: {
            type: "string",
            index: true,
            pk: true
        },
        name: {
            type: "string"
        },
        phone: {
            type: "string"
        },
        website: {
            type: "string",
            index: true
        },
        deals_description: {
            type: "string"
        },
        request_time:{
            type:"string",
            default:"0"
        }
    },
    function (err, model) {
        if (err) {
            console.log(err);
        } else {
        }
    }
);

container.declareDependency("OSPRequestAdapter", ["redisPersistence"], function (outOfService, redisPersistence) {
    if (!outOfService) {
        console.log("Enabling persistence...", redisPersistence);
    } else {
        console.log("Disabling persistence...");
    }
});

registerNewOSPRequest = function (userId, ospDetailsData, callback) {
    flow.create("register new OSP request", {
        begin: function () {
            redisPersistence.lookup.async("OspRequest", userId, this.continue("createOSPRequest"));
        },
        createOSPRequest: function (err, ospRequestDetails) {
            if (err) {
                callback(err, null);
            }
            else if (!redisPersistence.isFresh(ospRequestDetails)) {
                callback(new Error("OspAlreadyRegistered"), null);
            }
            else {
                ospRequestDetails['request_time'] = Date.now();
                redisPersistence.externalUpdate(ospRequestDetails, ospDetailsData);
                redisPersistence.saveObject(ospRequestDetails, callback);
            }
        }
    })();
};

getOSPRequests = function (callback) {
    flow.create("getAllOSPRequests", {
        begin: function () {
            redisPersistence.filter("OspRequest", {}, callback);
        }
    })();
};

getOSPRequest = function(userId,callback){
    flow.create("getOspRequestData", {
        begin:function(){
            redisPersistence.filter("OspRequest", {userId:userId}, callback);
        }
    })();
};

removeOSPRequest = function (userId, callback) {
    flow.create("getUserRequest", {
        begin: function () {
            if (!userId) {
                callback(new Error("userIdRequired"));
            }
            else {
                redisPersistence.findById("OspRequest", userId, this.continue("deleteRequest"));
            }
        },

        deleteRequest: function (err, request) {
            if(request === null){
                callback(new Error("ospRequestNotFound"));
            }
            else{
                redisPersistence.deleteById("OspRequest", userId, callback);
            }
        }
    })();
};