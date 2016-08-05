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
 the 'question' object will contain the id of a question, the id-s of possible answers and the proposed suggestions for each of the possible answers
 Example:
 {
 id:question14512
 possible_answers:[option1243,option1123,option1347]
 suggestions:{
 option1243:[option13243,option3243,option3273,option3211]
 option1123:[option132643,option23243,option31273,option37213]
 option1347:[option132143,option32743,option36273,option32213]
 }
 }
 */


getNextQuestion = function(current_settings){
    return determineNextQuestion(current_settings);
};


function determineNextQuestion(current_settings){
    var question_id = 1234234;//dummy for now
    var possible_answers_ids = [123,1324,234135]; //dummy for now

    return attachSuggestionsToQuestion({id:question_id, possible_answers:possible_answers_ids},
        current_settings)

    function attachSuggestionsToQuestion(question,current_settings){
        question.suggestions = {}
        question.possible_answers.forEach(function(possible_answer){
            question.suggestions[possible_answer] = determineSuggestionForAnswer(possible_answer,current_settings)
        })
        return question


        function determineSuggestionForAnswer(answer_id,current_settings){
            var suggestion_answers_ids = [1234,23454,235,213454]; //dummy for now
            return suggestion_answers_ids
        }
    }
}



adjustAlgorithm = function(current_settings){
    //TODO: Based on the responses adjust the parameters of the algoritm that does the suggestion thing
}
