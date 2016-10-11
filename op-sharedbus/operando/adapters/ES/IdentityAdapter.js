/*
 * Copyright (c) 2016 ROMSOFT.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the The MIT License (MIT).
 * which accompanies this distribution, and is available at
 * http://opensource.org/licenses/MIT
 *
 * Contributors:
 *    RAFAEL MASTALERU (ROMSOFT)
 * Initially developed in the context of OPERANDO EU project www.operando.eu
 */

var core = require("swarmcore");
core.createAdapter("IdentityManager");

var apersistence = require('apersistence');
var container = require("safebox").container;

var flow = require("callflow");

apersistence.registerModel("Identity", "Redis", {
        userId: {
            type: "string",
            index: "true"
        },
        email: {
            type: "string",
            index: "true",
            pk: "true"
        },

    },
    function (err, model) {
        if (err) {
            console.log(err);
        }
    }
);

container.declareDependency("IdentityManager", ["redisPersistence"], function (outOfService, redisPersistence) {
    if (!outOfService) {
        console.log("Enabling persistence...", redisPersistence);
    } else {
        console.log("Disabling persistence...");
    }
})


createIdentity = function (identityData, callback) {
    flow.create("create identity", {
        begin: function () {
            redisPersistence.lookup.async("Identity", identityData.email, this.continue("createIdentity"));
        },
        createIdentity: function (err, identity) {

            if (err) {
                callback(err, null);
            }
            else {
                if (!redisPersistence.isFresh(identity)) {
                    callback(new Error("Identity email should be unique"), null);
                }
                else {
                    redisPersistence.externalUpdate(identity, identityData);
                    redisPersistence.saveObject(identity, callback);

                }
            }
        }
    })();
};

generateIdentity = function(callback){
    flow.create("generateIdentity",{
        begin:function(){
            var identity = generateString();
            console.log(identity);
            redisPersistence.lookup.async("Identity", identity, this.continue("generateIdentity"));
        },
        generateIdentity: function(err, identity){
            if(redisPersistence.isFresh(identity)){
                callback(null, identity);
            }
            else{
                this.begin();
            }
        }
    })();
};

deleteIdentity = function (identityData, callback) {
    flow.create("delete identity", {
        begin: function () {
            if (!identityData.email) {
                callback(new Error("empty email"), null);
            }
            else {

                redisPersistence.findById("Identity", identityData.email, this.continue("deleteIdentity"));
            }
        },

        deleteIdentity: function (err, identity) {
            if (err) {
                callback(err, null);
            }
            else if (identity != null) {
                redisPersistence.delete(identity, callback);
            }

        }
    })();
};

getIdentities = function (userId, callback) {
    if (!userId) {
        callback(new Error("userId is required"), null);
    }
    else {
        redisPersistence.filter("Identity", {"userId": userId}, callback);
    }
};

setDefaultIdentity = function(identity, callback){

    flow.create("set default identity",{
        begin:function(){
            if(!identity){
                callback(new Error("No identity provided"), null);
            }
            else {
                redisPersistence.filter("Identity", {isDefault:true, userId:identity.userId}, this.continue("clearCurrentDefaultIdentity"));
            }
        },

        clearCurrentDefaultIdentity:function(err, identities){
            if(identities){
                identities.forEach(function(identity){
                    identity.isDefault = false;
                    redisPersistence.saveObject(identity, function(){
                        //TODO refactor this
                    });
                });
            }

            this.next("retrieveCurrentIdentity");
        },

        retrieveCurrentIdentity:function(){
            redisPersistence.findById("Identity", identity.email, this.continue("updateNewIdentity"));
        },
        updateNewIdentity:function(err, identity){
            if(err){
                callback(err, null);
            }
            else{
                identity.isDefault = true;
                redisPersistence.saveObject(identity, callback);
            }
        }
    })();

}

getUserId = function(proxyEmail,callback){
    redisPersistence.findById("Identity",proxyEmail,function(err,result){
        if(err){
            callback(err);
            return;
        }
        if(result===null){
            callback(new Error("Proxy "+proxyEmail+" is not registered"));
            return;
        }
        callback(err,result.userId);
    })
};

function generateString(){
    return Math.floor((1 + Math.random()) * 0x100000000000000)
        .toString(36)
        .substring(1);
}


