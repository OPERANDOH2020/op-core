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

var registerSwarming = {

    meta: {
        name: "register.js"
    },

    vars: {
        newUser: null,
        erorr: null
    },

    registerNewUser: function (newUserData) {
        console.log(newUserData);
        console.log("New user register request", newUserData);
        this.newUser = newUserData;
        this.swarm("verifyUserData");
    },

    verifyUserData: {
        node: "UsersManager",
        code: function () {
            var self = this;
            newUserIsValid(self.newUser, S(function (err, user) {
                if (err) {
                    console.log(err);
                    self.status = "error";
                    self.error = err.message;
                    self.newUser = {};
                    self.home("error");
                } else {
                    self.user = user;

                    startSwarm("emails.js", "sendEmail", "operando@" + thisAdapter.config.Core.operandoHost,
                        user['email'],
                        "Activate account",
                        "Your account has been registered \nTo activate it, please access the following link:\n http://" + thisAdapter.config.Core.operandoHost + "/activate/?confirmation_code=" + user.activationCode);
                    self.swarm("setUserNotifications");
                }    
            }))
        }
    },

    setUserNotifications:{
          node:"NotificationUAM",
          code:function(){
              var self = this;
              generateSignupNotifications(this.user.userId, S(function(err, notifications){
                  if(err){
                      console.log(err);
                  }
                  self.swarm("setRealIdentity");
              }));
          }
    },

    setRealIdentity :{
        node:"IdentityManager",
        code:function(){
            var self = this;
            setRealIdentity(this.user, S(function(err, identity){
                if(err){
                    console.log(err);
                }
                else{
                    console.log("Real identity added", identity);
                    self.swarm("generateValidationCode");
                }
            }));
        }
    },

    verifyValidationCode: function (validationCode) {
        this.validationCode = validationCode;
        this.swarm("validateCode");
    },

    validateCode:{
        node:"UsersManager",
        code:function(){
            var self = this;
            activateUser(this.validationCode, S(function (err, result) {
                if (err) {
                    self.error = err;
                    self.home("failed");
                } else {
                    self.home("success");
                }
            }))
        }
    }
}

registerSwarming;