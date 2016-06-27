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

var identitySwarming = {

    meta: {
        name: "identity.js"
    },

    vars: {
        identity: null,
        userId: null,
        action: null,
        error:{}
    },

    start: function () {
        console.log("Swarm extension started");
    },

    generateIdentity: function(sessionId){
        if (sessionId) {
            this.setSessionId(sessionId);
            this.action = "generateIdentity";
            this.swarm("checkUser");
        }
    },

    createIdentity: function (sessionId, identity) {

        if (sessionId) {
            this.setSessionId(sessionId);
        }
        else {
            this.error = new Error("Session id was not provided for create");
            this.swarm("error");
        }
        if (identity) {
            this.identity = identity;
            this.action = "createIdentity";
            this.swarm("checkUser");
        }
        else {
            this.error = new Error("Identity data was not provided for create");
            this.swarm("error");
        }
    },

    getMyIdentities: function (sessionId) {
        if (sessionId) {
            this.setSessionId(sessionId);
            this.action = "getMyIdentities";
            this.swarm("checkUser");
        }
        else {
            this.error = new Error("Session id was not provided for create");
            this.swarm("error");
        }
    },

    removeIdentity: function(sessionId, identity){
        if (sessionId) {
            this.setSessionId(sessionId);
        }
        else {
            this.error = new Error("Session id was not provided for create");
            this.swarm("error");
        }
        if (identity) {
            this.identity = identity;
            this.action = "deleteIdentity";
            this.swarm("checkUser");
        }
        else {
            this.error = new Error("identity id (email) was not provided for create");
            this.swarm("error");
        }
    },

    checkUser: {
        node: "SessionManager", //TODO remove rthis and use getUserId
        code: function () {
            var self = this;
            getUserBySession(this.getSessionId(), S(function (err, userId) {

                if (err != null) {
                    self.error.message = err.message;
                    self.swarm("error");
                }
                else {
                    self.userId = userId;
                    switch (self.action) {
                        case "createIdentity":
                            self.swarm("addIdentity");
                            break;
                        case "deleteIdentity":
                            self.swarm("deleteIdentity");
                            break;
                        case "getMyIdentities":
                            self.swarm("getUserIdentities");
                            break;
                        case "generateIdentity":
                            self.swarm("generateIdentityPhase");
                            break;
                    }
                }
            }));
        }
    },

    addIdentity: {
        node: "IdentityManager",
        code: function () {
            var self = this;
            self.identity['userId'] = this.userId;
            createIdentity(self.identity, S(function (err, identity) {
                if (err) {
                    self.error.message = err.message;
                    self.swarm("error");
                }
                else {
                    self.identity = identity;
                    self.swarm("success");
                }
            }));

        }
    },

    deleteIdentity:{
        node: "IdentityManager",
        code: function () {
            var self = this;
            self.identity['userId'] = this.userId;
            deleteIdentity(self.identity, S(function (err, identity) {
                if (err) {
                    self.error.message = err.message;
                    self.swarm("error");
                }
                else {
                    self.identity = identity;
                    self.swarm("success");
                }
            }));
        }
    },

    getUserIdentities:{
        node:"IdentityManager",
        code: function(){
            var self = this;
            getIdentities(self.userId, S(function (err, identities) {
                    if (err) {
                        self.error.message = err.message;
                        self.swarm("error");
                    }
                    else {
                        self.identities = identities;
                        self.swarm("success");
                    }
                })
            );
        }
    },

    generateIdentityPhase: {
        node: "IdentityManager",
        code: function () {
            var self = this;
            generateIdentity(S(function(err, identity){
                if(err){
                    self.error.message = err.message;
                    self.swarm("error");
                }
                else{
                    self.generatedIdentity = identity;
                    self.swarm("success");
                }
            }));
        }
    },

    success: {
        node: "Core",
        code: function () {
            this.home(this.action + "_success");
        }
    },

    error:{
        node: "Core",
        code: function(){
            console.log("Identity swarm error", this.error);
            this.home(this.action + "_error");
        }
    }



}

identitySwarming;