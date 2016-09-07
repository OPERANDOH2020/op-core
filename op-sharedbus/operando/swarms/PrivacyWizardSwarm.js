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


var privacyWizardSwarm = {

    getOSPSettings : function(){
        this.swarm("retrieveOSPSettingsFromServer");
    },

    retrieveOSPSettingsFromServer:{
        node: "PrivacySettingsWizzard",
        code: function () {
            this.OSPSettings = getOspSettings();
            this.home("gotOSPSettings")
        }
    },

    updateOSPSettings:function(newOspSettings){
        this.ospSettings = newOspSettings;
        this.swarm("updateOSPSettingsOnServer")
    },

    updateOSPSettingsOnServer:{
        node: "PrivacySettingsWizzard",
        code: function () {
            var result = updateOspSettings(this.ospSettings);
            if (result==="success"){
                this.home("ospSettingsUpdated")
            }else{
                this.updateError = result;
                this.home("ospSettingsUpdateFailed");
            }
        }
    },

    fetchReccomenderParams: function(){
        this.swarm("getReccomenderParams");
    },

    getReccomenderParams:{
        node: "PrivacySettingsWizzard",
        code: function () {
            this.reccomenderParameters = getReccomenderParams();
            this.home("gotReccomenderParams");
        }
    },

    completeWizard:function(current_settings){
        this.current_settings = current_settings;
        this.swarm("provideFeedback")
    },

    provideFeedback:{
        node: "PrivacySettingsWizzard",
        code: function () {
            addFeedback(this.current_settings);
            this.home("wizardCompleted");
        }
    }
};

privacyWizardSwarm;
