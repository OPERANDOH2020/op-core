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
        newUser:null,
        erorr:null
    },


    start: function () {
        console.log("Swarm extension started");
    },

    registeNewUser:function(newUserData){
        this.newUser = newUserData;
    },

    saveNewUser:{
        node:"UserManager",
        code: function () {
            var self = this;
            createUser(this.newUser, S(function(err, user){
                if(err){
                    self.error = self.err,
                    self.swarm("error");
                }else{
                    self.swarm("generateValidationCode");
                }
            }));
        }
    },

    generateValidationCode:{
        node : "RegisterUAM",
        code:function(){
            this.swarm("success");
        }
    },

    success: {
        node: "Core",
        code: function () {
            this.home("success");
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

registerSwarming;