
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
    getNextQuestion: function (current_settings) {
        this.current_settings = current_settings;
        console.log(current_settings);
        this.swarm("getQuestion");
    },
    completeWizard:function(current_settings,provided_suggestions){
        this.current_settings = current_settings;
        this.provided_suggestions = provided_suggestions;
        this.swarm("provideFeedback")
    },

    getQuestion: {
        node: "PrivacySettingsWizzard",
        code: function () {
            this.question = getNextQuestion(this.current_settings)
            this.home("gotNewQuestion")
        }
    },
    provideFeedback:{
        node: "PrivacySettingsWizzard",
        code: function () {
            adjustAlgorithm(this.current_settings,this.provided_suggestions)
            this.home("wizardCompleted")
        }
    }
}

privacyWizardSwarm;
