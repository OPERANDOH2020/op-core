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

var core = require ("swarmcore");
core.createAdapter("RegisterUAM");
var apersistence = require('apersistence');
var  container = require("safebox").container;
var flow = require("callflow");

var saveCallbackFn = function(err, obj){
    if(err){
        console.log(err);
    }
}


apersistence.registerModel("RegistrationConfirmation","Redis", {
    userId: {
        type: "string",
        pk: true,
        index:true
    },
    registration_code: {
        type: "string"
    }
}, function(err, model){
    if (err) {
        console.log(err);
    }
});


generateRegistrationCode = function(){

}

checkRegistrationcode = function(){

}


container.declareDependency("RegisterUAM", ["redisPersistence"], function(outOfService, redisPersistence){
    if(!outOfService){
        console.log("Enabling persistence...", redisPersistence);
        bootSystem();
    } else {
        console.log("Disabling persistence...");
    }
})