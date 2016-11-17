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
        node: "WatchDogAdapter",

        code: function () {
               // x = x.indexOf("\\uE2809C",0);
               //x = x.replace("\uE2809D","");
               this.ospSettings = getOspSettings();
               //console.log(x);
               this.home("gotOSPSettings");
        }
    },

    updateOSPSettings:function(newOspSettings){
        this.ospSettings = newOspSettings;
        this.swarm("updateOSPSettingsOnServer")
    },

    updateOSPSettingsOnServer:{
        node: "WatchDogAdapter",
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

    fetchRecommenderParams: function(){
        this.swarm("getRecommenderParams");
    },

    getRecommenderParams:{
        node: "WatchDogAdapter",
        code: function () {
            this.recommenderParameters = getRecommenderParams();
            this.home("gotRecommenderParams");
        }
    },

    completeWizard:function(current_settings){
        this.current_settings = current_settings;
        this.swarm("provideFeedback");
    },

    provideFeedback:{
        node: "WatchDogAdapter",
        code: function () {
            addFeedback(this.current_settings);
            this.home("wizardCompleted");
            this.swarm("updateNotifications");
        }
    },

    dismissPrivacyNotifications:function(){
        this.swarm("updateNotifications");
    },

    updateNotifications:{
        node:"NotificationUAM",
        code:function(){
            clearSocialNetwork(this.meta.userId);
        }
    }
};

privacyWizardSwarm;
