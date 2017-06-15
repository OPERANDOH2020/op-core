var core = require("swarmcore");
thisAdapter = core.createAdapter("CreditAdapter");
var container = require('safebox').container;
var uuid = require('node-uuid');
var persistence = undefined;
var flow = require('callflow');

function registerModels(callback){
    var models = [{
        modelName:"SocialPreferences",
        dataModel:{
            id:{
                type:"string",
                pk:true,
                length:255
            },
            userId: {
                type: "string",
                index: true,
                length:254
            },
            social_network:{
                type:"string",
                index:true,
                length:64
            },
            preferences:{
                type:"string",
                length:8096
            }
        }
    }];

    flow.create("registerModels",{
        begin:function(){
            this.errs = [];
            var self = this;
            models.forEach(function(model){
                persistence.registerModel(model.modelName,model.dataModel,self.continue("registerDone"));
            });

        },
        registerDone:function(err,result){
            if(err) {
                this.errs.push(err);
            }
        },
        end:{
            join:"registerDone",
            code:function(){
                if(callback && this.errs.length>0){
                    callback(this.errs);
                }else{
                    callback(null);
                }
            }
        }
    })();

}

container.declareDependency("SocialPreferences", ["mysqlPersistence"], function (outOfService, mysqlPersistence) {
    if (!outOfService) {
        persistence = mysqlPersistence;
        registerModels(function(errs){
            if(errs){
                console.error(errs);
            }
        })

    } else {
        console.log("Disabling persistence...");
    }
});


addOrUpdateSocialPreferences = function(userId, social_network, preferences, callback){

    flow.create("addOrUpdateSocialPreferences",{
        begin:function(){
            persistence.filter("SocialPreferences",{userId:userId, social_network:social_network}, this.continue("checkPreferences"));
        },
        checkPreferences:function(err, sn_preferences){
            if(err){
                callback(err);
            }else{
                if(sn_preferences.length>0){
                    var prefs = sn_preferences[0];
                    prefs['preferences'] = preferences;
                    persistence.saveObject(prefs, callback);
                }else{
                    persistence.lookup("SocialPreferences",uuid.v1(), this.continue("savePreferences"));
                }
            }
        },
        savePreferences:function(err, preferences){
            preferences['userId'] = userId;
            preferences['social_network'] = social_network;
            preferences['preferences'] = preferences;
            persistence.saveObject(preferences,callback);
        }
    })();

};

getPreferences = function(userId,social_network,callback){
    flow.create("addOrUpdateSocialPreferences",{
        begin:function(){
            persistence.filter("SocialPreferences",{userId:userId, social_network:social_network}, callback);
        }

    })();
};



