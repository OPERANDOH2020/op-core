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
core.createAdapter("SessionManager");

var container = require("safebox").container;
var myCfg = getMyConfig("SessionManager");

var sessionMaxIdleTime = 94608000;//one year
var sessionMinIdleTime = 8640;//one day
var persistence = undefined;
var flow = require("callflow");

if (myCfg.sessionTime != undefined) {
    sessionMaxIdleTime = myCfg.sessionTime;
}

function registerModels(callback){
    var models = [
        {
            modelName:"DefaultSession",
            dataModel : {
                userId: {
                    type: "string",
                    index :true,
                    length:254
                },
                sessionId: {
                    type: "string",
                    pk: true,
                    index: true,
                    length:254
                },
                expirationDate: {
                    type: "string",
                    length:254
                },
                ipAddress:{
                    type:"string",
                    length:254
                }
            }
        }
    ];

    flow.create("registerModels",{
        begin:function(){
            this.errs = [];
            var self = this;
            models.forEach(function(model){
                persistence.registerModel(model.modelName,model.dataModel,self.continue("registerDone"));
            });

        },
        registerDone:function(err,result){
            if(err) {
                this.errs.push(err);
            }
        },
        end:{
            join:"registerDone",
            code:function(){
                if(callback && this.errs.length>0){
                    callback(this.errs);
                }else{
                    callback(null);
                }
            }
        }
    })();
}

container.declareDependency("SessionManagerAdapter", ["redisPersistence"], function (outOfService, mysqlPersistence) {
    if (!outOfService) {
        persistence = mysqlPersistence;
        registerModels(function(errs){
            if(errs){
                console.error(errs);
            }
        });

    } else {
        console.log("Disabling persistence...");
    }
});


createOrUpdateSession = function(sessionData, callback){
    flow.create("create or update Session", {
        begin: function () {
            if (!sessionData.userId) {
                callback(new Error('Empty userId'), null);
            }
            else {
                persistence.lookup.async("DefaultSession", sessionData.sessionId, this.continue("createSession"));
            }
        },
        createSession: function (err, session) {
            console.log(session);
            sessionData.expirationDate = parseInt(Date.now()) + parseInt(sessionMaxIdleTime);
            persistence.externalUpdate(session, sessionData);
            persistence.saveObject(session, callback);
        }
    })();
}

deleteSession = function (sessionId, userId, callback) {
    flow.create("delete session", {
        begin: function () {
            persistence.findById("DefaultSession", sessionId, this.continue("deleteSession"));
        },
        deleteSession: function (err, session) {
            if (err) {
                callback(err, null);
            }
            else {
                if (persistence.isFresh(session)) {
                    callback(new Error("Could not find a session with id " + sessionId), null);
                }
                else {
                    persistence.deleteById("DefaultSession", sessionId, callback);
                }
            }
        }
    })();
}

getUserBySession = function (sessionId, callback) {

    flow.create("delete all user sessions", {
        begin: function () {
            if (!sessionId) {
                callback(new Error("sessionId is required"), null);
            }
            else {
                persistence.findById("DefaultSession", sessionId, this.continue("getUser"));
            }
        },
        getUser:function(err, session){
            if(err){
                callback(err, null);
            }
            else{
                callback(null, session.userId);
            }
        }
    })();
}

deleteUserSessions = function(sessionId,callback){
    var f = flow.create("delete all user sessions", {
        begin:function(sessionId, callback){
            this.callback = callback;
            if (!sessionId) {
                callback(new Error("sessionId is required"), null);
            }
            else{
                persistence.findById("DefaultSession", sessionId, this.continue("findSessions"));
            }
        },
        findSessions: function(err, session) {
            if (err) {
                this.callback(err, null);
            }
            else if(session != null && session.userId) {
                persistence.filter("DefaultSession", {"userId": session.userId}, this.continue("deleteUserSessions"));
            }
            else{
                //do not change this err key
                callback(new Error("session_not_found"));
            }
        },
        deleteUserSessions: function (err, sessions) {
            if (err) {
                this.callback(err, null);
            } else {
                var self = this;
                sessions.forEach(function(session){
                    self.deleteSingleSession(session);
                });
            }
        },
        deleteSingleSession:function(session){
            console.log(session);
            persistence.delete(session);
        },

        end:{
            join:"deleteSingleSession",
            code:function(err, response){
                this.callback(err, response);
            }
        }
    });
    try{f(sessionId,callback)}catch(e){console.log(e);}

}

sessionIsValid = function (newSession, sessionId, userId, callback) {

    flow.create("validate session", {
        begin: function () {

            if (!sessionId) {
                callback(new Error("sessionId is required to validate session"), null);
                return;
            }

            if (!userId) {
                callback(new Error("userId is required to validate session"), null);
                return;
            }

            persistence.findById("DefaultSession", sessionId, this.continue("validateSession"));

        },
        validateSession: function (err, session) {
            if (err) {
                callback(err, session);
            }
            else if (!session || persistence.isFresh(session)) {
                callback(new Error("Session not found"), false);
            }
            else {
                if (parseInt(session.expirationDate) < parseInt(Date.now())) {
                    callback(new Error("Session is expired"), false);
                }
                else {
                    session.expirationDate = parseInt(Date.now()) + parseInt(sessionMaxIdleTime);
                    session.sessionId = newSession;
                    persistence.saveObject(session, callback);
                }
            }
        }
    })();

}



