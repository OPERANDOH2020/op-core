/**
 * Created by ciprian on 16.08.2016.
 */


var fs = require('fs')


var ospSettingsFile = "./resources/OSP.settings.json";
var ospSettings = JSON.parse(fs.readFileSync(ospSettingsFile));

exports.conditionalProbabilities = undefined;
exports.optionProbabilties = undefined;
exports.optionToSetting = undefined;
exports.settingToOptions = undefined;
exports.ospSettings = undefined;

exports.indexOSPSettings = function(){

    try{
        ospSettings = JSON.parse(fs.readFileSync(ospSettingsFile));
    }catch(e){
        throw new Error("You must put the settings file in resources using the JSON format. ");
    }

    var max_index = -1;

    forEachOption(function(optionObject){
        optionObject.index = ++max_index
    })


    var current_setting_id = 0;
    forEachSetting(function(settingObject){
        if(settingObject['read']['availableSettings']){
            if(Array.isArray(settingObject['read']['availableSettings'])!==true){
                settingObject.id = current_setting_id++
            }
        }
    });


    fs.writeFileSync("./resources/OSP.settings.json",JSON.stringify(ospSettings,null,4));
    init();

    function forEachSetting(applyOnSetting){
        for(var network in ospSettings){
            var settings = ospSettings[network];
            for(var setting in settings){
                applyOnSetting(settings[setting],setting);
            }
        }
    }

    function forEachOption(applyOnOption){
        forEachSetting(function(settingObj){
            var options = settingObj['read']['availableSettings'];
            if(Array.isArray(options)!==true){
                for(var option in options){
                    applyOnOption(options[option],option)
                }
            }
        })
    }
};


init();
function init(){
    exports.settingToOptions = extractOptionsForSettings();
    var numOptions = exports.settingToOptions.reduce(function(prev,options){return prev+options.length;},0);

    exports.optionToSetting = new Array(numOptions);
    exports.settingToOptions.forEach(function(options,setting){options.forEach(function(option){exports.optionToSetting[option] = setting;})});
    exports.conditionalProbabilities = computeConditionalProbabilities(exports.settingToOptions);
    exports.optionProbabilties = computeOptionProbabilities(exports.settingToOptions);
    exports.ospSettings = ospSettings;

    function extractOptionsForSettings(){
        var settingToOptions = [];
        for(var network in ospSettings){
            var settings = ospSettings[network];
            for (var setting in settings) {
                var settingObj = settings[setting];
                if (settingObj['read']['availableSettings']&& Array.isArray(settingObj['read']['availableSettings'])!==true) {
                    var arrayOfOptionIndexes = [];
                    for (var option in settingObj['read']['availableSettings']) {
                        arrayOfOptionIndexes.push(settingObj['read']['availableSettings'][option].index);
                    }
                    settingToOptions.push(arrayOfOptionIndexes)
                }
            }
        }
        return settingToOptions
    }

    function computeOptionProbabilities(settingsToOptions) {
        var optionProbabilities = new Array(numOptions);
        settingsToOptions.forEach(function(options){
            var normalizer = 0;
            options.forEach(function(option){
                optionProbabilities[option] = Math.random();
                normalizer+=optionProbabilities[option];
            })
            options.forEach(function(option){
                optionProbabilities[option]/=normalizer;
            })
        });
        return optionProbabilities;
    }

    function computeConditionalProbabilities(settingToOptions){
        var conditionalProbabilities = []
        var allNormalizers = []

        for(var option=0;option<numOptions;option++) {
            var rands = new Array(numOptions)
            var normalizers = new Array(settingToOptions.length).fill(0);
            for (var otherOption = 0; otherOption < numOptions; otherOption++) {
                rands[otherOption] = Math.random()
                normalizers[exports.optionToSetting[otherOption]]+=rands[otherOption]
            }
            conditionalProbabilities.push(rands);
            allNormalizers.push(normalizers);
        }

        for(var option=0;option<numOptions;option++) {
            for (var otherOption = 0; otherOption < numOptions; otherOption++) {
                if(option===otherOption){
                    conditionalProbabilities[option][otherOption]=1;
                    continue;
                }
                if(exports.optionToSetting[option]===exports.optionToSetting[otherOption]){
                    conditionalProbabilities[option][otherOption]=0;
                    continue;
                }
                conditionalProbabilities[option][otherOption]/=allNormalizers[option][exports.optionToSetting[otherOption]]
            }
        }
        return conditionalProbabilities;
    }
}





exports.vecMatrixMultiplication = function(vector,matrix){
    var result = new Array(matrix[0].length).fill(0);
    for(var i=0;i<vector.length;i++){
        for (var j=0;j<vector.length;j++){
                result[i]+=vector[j]*matrix[j][i]
            }
        }
    return result;
}


exports.argSort = function(array){
    //the equivalent of argsort from numpy
    var args = new Array(array.length);
    var argsOfValues = {}
    array.forEach(function (value,index) {
            if (argsOfValues[value]){
                argsOfValues[value].push(index);
            }else{
                argsOfValues[value] = [index]
            }
        }
    )
    array.sort();
    array.forEach(function(value,index){
        args[index] = argsOfValues[value].shift();
    });
    return args
}