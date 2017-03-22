/*
 * Copyright (c) 2016 ROMSOFT.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the The MIT License (MIT).
 * which accompanies this distribution, and is available at
 * http://opensource.org/licenses/MIT
 *
 * Contributors:
 *    RAFAEL MASTALERU (ROMSOFT)
 * Initially developed in the context of OPERANDO EU project www.operando.eu
 */


var core = require ("swarmcore");
core.createAdapter("NotificationUAM");
var persistence = undefined;
var  container = require("safebox").container;
var flow = require("callflow");
var uuid = require('uuid');


var signupNotifications = {

    privacy_questionnaire: {
        sender: "WatchDog",
        title: "Privacy Questionnaire",
        description: "You have not filled all your social network privacy settings yet. Doing so will tailor your social network privacy settings to your preferences. You can also optimize your social network privacy settings in a single click, using settings recommended by PrivacyPlus.",
        actions: [
            {
                key: "social-network-privacy",
                title: "Optimize my social network privacy"
            }
        ],
        type: "info-notification",
        category: "privacy-questionnaire"

    },
    identity: {
        sender: "WatchDog",
        title: "Add identity",
        description: "You have not yet generated alternative email identities. Doing so will enable you to sign up on websites without disclosing your real email.",
        actions: [{
            key: "identity",
            title: "Go to email identities"
        }],
        type: "info-notification",
        category: "identity"
    },

    deals: {
        sender: "WatchDog",
        title: "Privacy deals",
        description: "You have not yet accepted any privacy deals. Privacy deals enable you to trade some of your privacy for valuable benefits.",
        actions: [{key: "privacy-for-benefits", title: "Go to deals"}],
        type: "info-notification",
        category: "deals"
    }

};

function registerModels(callback){

    var models = [
    {
        modelName:"Notification",
        dataModel : {
            notificationId:{
                type:"string",
                pk:true,
                index:true,
                length:254
            },
            sender:{
                type: "string",
                length:254
            },
            forUser:{
                type: "string",
                index: true,
                length:254
            },
            type:{
                type: "string",
                index: true,
                length:254
            },
            category:{
                type:"string",
                length:254
            },
            actions:{
                type:"string",
                length:1024
            },
            title:{
                type: "string",
                length:254
            },
            description:{
                type: "string",
                length:1024
            },
            delivered:{
                type: "boolean"
            },
            date:{
                type: "date"
            },
            dismissed:{
                type: "boolean",
                default: false
            }
        }
    }
];

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
})()
}

container.declareDependency("NotificationUAMAdapter", ["mysqlPersistence"], function (outOfService, mysqlPersistence) {
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



getNotifications = function (userId, callback) {
    flow.create("Get notifications for user", {
        begin: function () {
            persistence.filter("Notification", {forUser: userId, dismissed:false}, this.continue("getNotifications"));
        },
        getNotifications: function (err, notifications) {
            callback(err, notifications);
        }
    })();
};


deleteNotification = function (notificationId, callback) {
    flow.create("Delete Notification", {
        begin: function () {
            persistence.deleteById("Notification", notificationId, this.continue("deleteReport"));
        },
        deleteReport: function (err, obj) {
            callback(err, obj);
        }
    })();
};



createNotification = function (sender, userId, type, category, title, description, callback) {

    flow.create("Create Notification", {
        begin: function () {
            if (!userData.userId) {
                callback(new Error('Empty userId'), null);
            }
            else {
                persistence.lookup("Notification", uuid.v1(), this.continue("createNotification"));
            }
        },
        createNotification: function (err, notification) {
            if (!persistence.isFresh(notification)) {
                callback(new Error("notification with identical id " + notification.notificationId + " already exists"), null);
            } else {
                notification.sender        = sender;
                notification.forUser        = userId;
                notification.title          = title;
                notification.description    = description;
                notification.type           = type;
                notification.action         = action;
                notification.category       = category;

                persistence.save(notification, callback);
            }
        }
    })();
}


updateNotification = function (notificationDump, callback) {
    flow.create("Update notification", {
        begin: function () {
            persistence.lookup("Notification", notificationDump.notificationId, this.continue("updateNotification"));
        },

        updateNotification: function (err, notification) {
            if (err) {
                callback(err, null);
            }

            else if (persistence.isFresh(notification)) {
                callback(new Error("Notification with id " + notification.notificationId + " was not found"), null);
            }
            else {
                persistence.externalUpdate(notification, notificationDump);
                persistence.saveObject(notification, callback);
            }
        }
    })();
};

generateSignupNotifications = function (userId, callback) {
    flow.create("createSignupNotifications", {
        begin: function () {
            this.notifications = [];
            this.next("iterateNotifications");
        },

        iterateNotifications: function () {
            //this does not work
            //Object.keys(signupNotifications).forEach(this.continue("createNotification"));

            var self = this;
            Object.keys(signupNotifications).forEach(function(key, index){
                self.next("createNotification",undefined,key, index);
            });

        },
        createNotification: function (key, index) {
            var self = this;
            persistence.lookup("Notification", uuid.v1(), function (err, notification) {

                if (err) {
                    callback(err, null);
                }
                else {
                    for (var i in signupNotifications[key]) {
                        notification[i] = signupNotifications[key][i];
                    }
                    notification.forUser = userId;
                    persistence.save(notification, self.continue("notificationCreated"));
                }

            });
        },
        notificationCreated:function(err, notification){
            console.log(uuid.v1());
            this.notifications.push(notification);
        },
        end:{
            join:"notificationCreated",
            code:function(){
                console.log("Generated ", this.notifications.length, " notifications");
                callback(null, this.notifications);
            }
        }

    })();
};


clearIdentityNotifications = function(userId){
    clearNotification(userId,signupNotifications.identity.category);
}

clearDealsNotifications = function(userId){
    clearNotification(userId,signupNotifications.deals.category);
}

clearSocialNetwork = function(userId){
    clearNotification(userId,signupNotifications.privacy_questionnaire.category);
}


clearNotification = function(userId, category){
    flow.create("dismissIdentitiesNotifications", {

        begin:function(){
            if(!userId){
                console.log(new Error("userId is invalid"));
            }
            else{
                console.log(userId);
                console.log(signupNotifications.identity.category);
                persistence.filter("Notification", {forUser: userId, category:category}, this.continue("dismissNotifications"));
            }
        },
        dismissNotifications:function(err,notifications){
            if(err){
                console.log(err);
            }
            else{
                var self = this;
                notifications.forEach(function(notification){

                    var notificationDump = {
                        notificationId:notification.notificationId,
                        dismissed:true
                    }
                    updateNotification(notificationDump, self.continue("notificationUpdated"));
                })
            }
        },

        notificationUpdated:function(){

        },

        end:{
            join:"notificationUpdated",
            code:function(){
                console.log(category +" notifications dismissed");
            }
        }

    })();
};


