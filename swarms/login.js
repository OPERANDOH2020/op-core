/**
 *
 * Login swarm, Specific for USMED project
 */

/*
TODO: check to be clean in production, this is an ideal place where you can put a backdoor for your authentication
 */

sessionsRegistry  = require("../lib/SessionRegistry.js").getRegistry();

var loginSwarming = {
    meta:{
        debug: false
    },
    vars:{
        authenticated:false,
        sessionId:null,
        userId:null
    },
    userLogin:function(clientSessionId, userId, authorisationToken){
        this.authenticated = false;
        this.userId = userId;
        if(!userId){
            this.swarm('failed');
            return ;
        }
        this.authorisationToken = authorisationToken;
        this.clientAdapter = thisAdapter.nodeName;
        this.swarm('checkPassword');

    },
    checkPassword:{
      node:"UsersManager",
      code:function(){
          var valid = validPassword.async(this.userId, this.authorisationToken);
          var self = this;
          (function(valid){
              if (valid) {
                  self.authenticated = true;
                  self.swarm("createOrUpdateSession");

              } else {
                  self.swarm("failed", self.getEntryAdapter());
              }
          }).swait(valid);
        }
    },

    logout:function(clientSessionId, userId){
        this.setSessionId(clientSessionId);
        this.userId = userId;
        this.swarm("userLogout");
    },

    userLogout:{
        node:"SessionManager",
        code: function () {
            var self = this;
            console.log("SWARM DELETE SESSIONS");
            deleteUserSessions(this.getSessionId(), S(function (err, result) {
                if (err) {
                    console.log(err);
                }
                else {
                    self.home("logoutSucceed");
                }
            }));

        }
    },

    restoreSession:function(clientSessionId, userId ){
        this.sessionId = clientSessionId;
        this.userId = userId;
        this.swarm("validateSession");

    },

    validateSession: {
        node: "SessionManager",
        code: function () {
            var self = this;
            sessionIsValid(self.sessionId, self.userId, S(function (err, isValid) {

                if (err) {
                    console.log(err);
                    self.home("restoreFailed");
                }
                else {
                    if (isValid) {
                        console.log("Session is valid");
                        self.authenticated = true;
                        self.swarm("restoreSwarms", self.getEntryAdapter());
                    }
                    else {
                        self.home("restoreFailed");
                    }
                }
            }));
        }
    }
,

    //It is not used anywhere
    reconnectInSession:function(clientSessionId, userId, secretToken){
        this.authenticated              = false;
        this.setSessionId(clientSessionId);
        this.userId                     = userId;
        this.secretToken                = secretToken;
        this.swarm("reconnect");
    },
    testCtor:function(clientSessionId, userId, authorisationToken) {
        this.authenticated = false;
        this.userId = userId;
        this.authorisationToken = authorisationToken;
        this.clientAdapter = thisAdapter.nodeName;
        console.log("Password: ", authorisationToken);
        if (authorisationToken == "ok") {
            this.authenticated = true;
            cprint("enabling... " + this.clientAdapter);
            this.swarm("enableSwarms", this.getEntryAdapter());
        } else {
            this.swarm("failed", this.getEntryAdapter());
            cprint("disabling... " + this.clientAdapter);
        }
    },
    reconnect:{   //add this new outlet in sessions
        node:"EntryPoint",
        code : function (){
            var outlets = sessionsRegistry.findOutletsForSession(this.getSessionId());
            for(var v in outlets){
                if(outlets[v].getSecret() == this.secretToken){
                    this.swarm("enableSwarm", this.getEntryAdapter());
                    return ;
                }
            }
            this.home("failed");
        }
    },
    failed:{   //phase
        node:"EntryPoint",
        code : function (){
            sessionsRegistry.disableOutlet(this.meta.outletId);
            logger.info("Failed login for " + this.userId );
            this.home("failed");
        }
    }, 
    enableSwarms: {   //phase that is never executed... given as documentation
        node:"EntryPoint",
        code : function (){
            var outlet = sessionsRegistry.getTemporarily(this.meta.outletId);
            sessionsRegistry.registerOutlet(outlet);
            enableOutlet(this);
            console.log("Success !");
            this.home("success");
        }
    },
    restoreSwarms: {   //phase that is never executed... given as documentation
        node:"EntryPoint",
        code : function (){
            var outlet = sessionsRegistry.getTemporarily(this.meta.outletId);
            sessionsRegistry.registerOutlet(outlet);
            enableOutlet(this);
            console.log("Session restored for ", this.userId,"!");
            this.home("restoreSucceed");
        }
    },

    createOrUpdateSession:{
        node: "SessionManager",
        code:function(){
            var self = this;
            var sessionData = {
                userId: self.userId,
                sessionId: self.meta.sessionId
            };

            createOrUpdateSession(sessionData, S(function(error, session){
                if(error){
                    console.log(error);
                }
                else{
                    console.log("Current session", session);
                    self.swarm("enableSwarms", self.getEntryAdapter());
                }
            }));

        }
    }
};

loginSwarming;