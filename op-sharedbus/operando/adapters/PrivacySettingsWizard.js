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


/*
 'current_settings' is an object with question_id_s as keys and the chosen options as values. Virtually, the state of the wizard will be kept in this object .
 Example:
     {
         question14512:[option1243,option13243,option3243]
         question11434:[option1123,option13678,option3547]
         question14523:[option1347,option1373,option3243]
     }

 This means the following: to question14512 the user gave answer option1243 and he further confirmed or set himself option13243,option3243 as correct choices
 'provided_suggestions' is very simmilar to current_settings but, instead of the answers that the users sets himself, it will keep the suggestions that the wizard made;
 in this way we are able to compare what we suggest to what the user want and thus improve the algorithm

 the 'question' object returned by 'getNextQuestion' will contain the id of a question, the id-s of possible answers and the proposed suggestions for each of the possible answers
 Example:
     {
         question_id:question14512
         possible_choices_ids:[option1243,option1123,option1347]
         suggestions:{
                         option1243:[option13243,option3243,option3273,option3211]
                         option1123:[option132643,option23243,option31273,option37213]
                         option1347:[option132143,option32743,option36273,option32213]
                    }
    }
 */


getNextQuestion = function(current_settings){
    if (current_settings === undefined)
        current_settings={}
    return mockDetermineNextQuestion(current_settings);
};


function mockDetermineNextQuestion(current_settings){

    var fs = require('fs')
    var mock_settings = JSON.parse(fs.readFileSync("./mockWizardSettings.json"))

    var setting = "setting1";
    if (current_settings["setting4"]){
        setting = "setting7";
    }
    else{
        if(current_settings["setting1"]){
            setting = "setting4";
        }
    }


    var possible_choices = mock_settings[setting];
    var questionsWithSuggestions = attachSuggestionsToQuestion(
                        {
                            "question_id":setting,
                            "possible_choices_ids":possible_choices
                        },
                            current_settings);

    return questionsWithSuggestions;

    function attachSuggestionsToQuestion(question){


        question.suggestions = {}
        question.possible_choices_ids.forEach(function(possible_choice){
            question.suggestions[possible_choice] = mockDetermineSuggestionForChoice(possible_choice,current_settings)
        })
        return question


        function mockDetermineSuggestionForChoice(choice,current_settings){
            var question_id = parseInt(choice[choice.length-2])+1;
            var parity = choice[choice.length-1];
            var suggestions = [];
            suggestions.push(("option"+question_id+parity));
            question_id++;
            suggestions.push(("option"+question_id+parity));
            return suggestions;
        }
    }
}



adjustAlgorithm = function(current_settings,provided_suggestions){
    console.log("Adjusting algorithm with ",current_settings," and ",provided_suggestions)
    //TODO: Based on the responses adjust the parameters of the algoritm that does the suggestion thing
}
