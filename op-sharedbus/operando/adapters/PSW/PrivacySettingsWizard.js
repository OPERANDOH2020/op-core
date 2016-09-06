/*
 * Copyright (c) 2016 ROMSOFT.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the The MIT License (MIT).
 * which accompanies this distribution, and is available at
 * http://opensource.org/licenses/MIT
 *
 * Contributors:
 *    CIPRIAN TALMACEL (ROMSOFT)
 * Initially developed in the context of OPERANDO EU project www.operando.eu
 */

var core = require("swarmcore");

thisAdapter = core.createAdapter("PrivacySettingsWizzard");

var fs = require('fs');
var storageFile = "./resources/userAnswers";

var ospSettings = JSON.parse(fs.readFileSync("./resources/OSP.settings.json"));


getOspSettings = function(){
    return ospSettings;
};

updateOspSettings = function(newOspSettingsObject){
    //check stuff here maybe and throw errors accordingly
    try {
        fs.writeFileSync("./resources/OSP.settings.json", JSON.stringify(newOspSettingsObject, null, 4));
        ospSettings = newOspSettingsObject;
    }catch(error){
        console.log("Update unsuccessful");
        return error;
    }
    return "success";
};

getReccomenderParams = function(){
    //return the params used by the reccomender system
}

addFeedback = function(current_settings){
    fs.appendFile(storageFile,current_settings,function(err){
        console.log("Error: "+err+" occured for user choices: "+current_settings);
    });
};