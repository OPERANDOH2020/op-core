/**
 * Created by Rafa on 6/15/2017.
 */

var socialPreferences = {
    meta:{
        name:"SocialPreferences.js"
    },
    getPreferences:function(socialNetwork){
        this.socialNetwork = socialNetwork;
        this.swarm("getUserPreferences");
    },
    saveOrUpdatePreferences:function(socialNetwork, preferences){
        this.socialNetwork = socialNetwork;
        this.preferences = JSON.stringify(preferences);
        this.swarm("saveUserPreferences");
    },

    removePreferences:function(preferenceKey){
        this.preferenceKey = preferenceKey;
        this.swarm("removeUserPreferences");
    },

    getUserPreferences:{
        node:"SocialPreferencesAdapter",
        code:function(){
            var self = this;
            getPreferences(this.meta.userId,this.socialNetwork,S(function(err, preferences){
                if(err){
                    self.error = err.message;
                    self.home("failed");
                }else{
                    if(preferences.length>0){
                        self.preferences = JSON.parse(preferences[0].preferences);
                    }
                    else{
                        self.preferences = [];
                    }
                    self.home("success");
                }
            }));
        }
    },
    saveUserPreferences:{
        node:"SocialPreferencesAdapter",
        code:function(){
            var self = this;
            addOrUpdateSocialPreferences(this.meta.userId, this.socialNetwork, this.preferences,S(function(err, preferences){
                if (err) {
                    self.error = err.message;
                    self.home("failed");
                } else {
                    self.preferences = preferences;
                    self.home("success");
                }
                }));
            }
        },
    removeUserPreferences:{
        node:"SocialPreferencesAdapter",
        code:function(){
            var self = this;
            deletePreferences(this.meta.userId, this.preferenceKey, S(function(err, result){
                if (err) {
                    self.error = err.message;
                    self.home("failed");
                } else {
                    self.home("success");
                }
            }));
        }
    }

}
socialPreferences;