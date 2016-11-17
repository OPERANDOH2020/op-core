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
var apersistence = require('apersistence');
var  container = require("safebox").container;
var flow = require("callflow");
var uuid = require('uuid');


var signupNotifications = {

    privacy_questionnaire: {
        sender: "WatchDog",
        title: "Privacy Questionnaire",
        description: "You have not filled all your social network privacy settings yet. Doing so will tailor your social network privacy settings to your preferences. You can also optimize your social network privacy settings in a single click, using settings recommended by PrivacyPlus.<br/> <a href='#'>Optimize my social network privacy</a>",
        actions: ["social-network-privacy"],
        type: "info-notification",
        category:"privacy-questionnaire"

    },
    identity: {
        sender: "WatchDog",
        title: "Add identity",
        description: "You have not yet generated alternative email identities. Doing so will enable you to sign up on websites without disclosing your real email.<br/> <a href='#'>Go to email identities</a>",
        action: ["identity"],
        type: "info-notification",
        category:"identity"
    },

    deals: {
        sender: "WatchDog",
        title: "Privacy deals",
        description: "You have not yet accepted any privacy deals. Privacy deals enable you to trade some of your privacy for valuable benefits.<br/> <a href='#'> Go to deals</a>",
        action: ["privacy-for-benefits"],
        type: "info-notification",
        category:"deals"
    }

}

apersistence.registerModel("Notification","Redis",{
    notificationId:{
        type:"string",
        pk:true,
        index:true
    },
    sender:{
        type: "string"
    },
    forUser:{
        type: "string",
        index: true
    },
    type:{
        type: "string",
        index: true
    },
    category:{
        type:"string"
    },
    action:{
        type:"string"
    },
    title:{
        type: "string"
    },
    description:{
        type: "string"
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
    },
    ctor: function(){

    }

}, function (err, model) {
    if (err) {
        console.log(err);
    }
});


getNotifications = function (userId, callback) {
    flow.create("Get notifications for user", {
        begin: function () {
            redisPersistence.filter("Notification", {forUser: userId, dismissed:false}, this.continue("getNotifications"));
        },
        getNotifications: function (err, notifications) {
            callback(err, notifications);
        }
    })();
}


deleteNotification = function (notificationId, callback) {
    flow.create("Delete Notification", {
        begin: function () {
            redisPersistence.deleteById("Notification", notificationId, this.continue("deleteReport"));
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
                redisPersistence.lookup("Notification", uuid.v1(), this.continue("createNotification"));
            }
        },
        createNotification: function (err, notification) {
            if (!redisPersistence.isFresh(notification)) {
                callback(new Error("notification with identical id " + notification.notificationId + " already exists"), null);
            } else {
                notification.sender        = sender;
                notification.forUser        = userId;
                notification.title          = title;
                notification.description    = description;
                notification.type           = type;
                notification.action         = action;
                notification.category       = category;

                redisPersistence.save(notification, callback);
            }
        }
    })();
}


updateNotification = function (notificationDump, callback) {
    flow.create("Update notification", {
        begin: function () {
            redisPersistence.lookup("Notification", notificationDump.notificationId, this.continue("updateNotification"));
        },

        updateNotification: function (err, notification) {
            if (err) {
                callback(err, null);
            }

            else if (redisPersistence.isFresh(notification)) {
                callback(new Error("Notification with id " + notification.notificationId + " was not found"), null);
            }
            else {
                redisPersistence.externalUpdate(notification, notificationDump);
                redisPersistence.saveObject(notification, callback);
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
            redisPersistence.lookup("Notification", uuid.v1(), function (err, notification) {

                if (err) {
                    callback(err, null);
                }
                else {
                    for (var i in signupNotifications[key]) {
                        notification[i] = signupNotifications[key][i];
                    }
                    notification.forUser = userId;
                    redisPersistence.save(notification, self.continue("notificationCreated"));
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
                redisPersistence.filter("Notification", {forUser: userId, category:category}, this.continue("dismissNotifications"));
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
}


container.declareDependency("NotificationUAMAdapter", ["redisPersistence"], function (outOfService, redisPersistence) {
    if (!outOfService) {
        console.log("Enabling persistence...", redisPersistence);
    } else {
        console.log("Disabling persistence...");
    }
})