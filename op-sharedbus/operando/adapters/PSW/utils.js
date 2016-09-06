/**
 * Created by ciprian on 16.08.2016.
 */


var fs = require('fs')

exports.indexOSPSettings = function(){
    var settings;

    try{
        settings = JSON.parse(fs.readFileSync("./resources/OSP.settings.json"));
    }catch(e){
        throw new Error("You must put the settings file in resources using the JSON format. ");
    }

    var max_index = -1;

    function forEachOption(funcOnOption){
        for(var network in settings){
            var available_settings = settings[network];
            for(var setting in available_settings){
                var current_setting = available_settings[setting];
                if(current_setting['read']['availableSettings']){
                    var availableOptions = current_setting['read']['availableSettings'];
                    for(var option in availableOptions){
                        funcOnOption.apply(undefined,[availableOptions[option],option])
                    }
                }
            }
        }
    }

    forEachOption(function(optionObject){
        if(optionObject.index>max_index){
            max_index = optionObject.index;
        }
    })

    forEachOption(function(optionObject){
        if(optionObject.index===undefined)
            optionObject.index=++max_index
    })

    fs.writeFileSync("./resources/OSP.settings.json",JSON.stringify(settings,null,4))
}

exports.indexOSPSettings();


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