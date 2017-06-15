/**
 * Created by Rafa on 6/15/2017.
 */

var socialPreferences = {
    meta:{
        name:"SocialPreferences"
    },
    getPreferences:function(socialNetwork){
        this.socialNetwork = socialNetwork;
        this.swarm("getUserPreferences");
    },
    saveOrUpdatePreferences:function(socialNetwork, preferences){
        this.socialNetwork = socialNetwork;
        this.preferences = preferences;
        this.swarm("saveUserPreferences");
    },

    getUserPreferences:{
        node:"SocialPreferencesAdapter",
        code:function(){
            var self = this;
            getPreferences(this.meta.userId,this.socialNetwork,function(err, preferences){
                if(err){
                    self.error = err.message;
                    self.home("failed");
                }else{
                    self.preferences = preferences;
                    self.home("success");
                }
            });
        }
    },
    saveUserPreferences:{
        node:"SocialPreferencesAdapter",
        code:function(){
            var self = this;
            addOrUpdateSocialPreferences(this.meta.userId, this.socialNetwork, this.preferences,function(err, preferences){
                if (err) {
                    self.error = err.message;
                    self.home("failed");
                } else {
                    self.preferences = preferences;
                    self.home("success");
                }
                });
            }
        }

}
socialPreferences;