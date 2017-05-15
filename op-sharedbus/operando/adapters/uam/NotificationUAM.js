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
var apersistence = require('apersistence');
var signupNotifications = {
    privacy_questionnaire: {
        sender: "WatchDog",
        title: "Privacy Questionnaire",
        description: "You have not filled all your social network privacy settings yet. Doing so will tailor your social network privacy settings to your preferences. You can also optimize your social network privacy settings in a single click, using settings recommended by PrivacyPlus.",
        action_name:"social-network-privacy",
        zone:"ALL_USERS"
    },
    identity: {
        sender: "WatchDog",
        title: "Add identity",
        description: "You have not yet generated alternative email identities. Doing so will enable you to sign up on websites without disclosing your real email.",
        action_name:"identity",
        zone:"ALL_USERS"
    },
    deals: {
        sender: "WatchDog",
        title: "Privacy deals",
        description: "You have not yet accepted any privacy deals. Privacy deals enable you to trade some of your privacy for valuable benefits.",
        action_name:"privacy-for-benefits",
        zone:"ALL_USERS"
    },
    private_browsing_ios: {
        sender: "WatchDog",
        title: "PrivateBrowsing",
        description: "Check the new feature: private browsing for iOS",
        action_name:"private_browsing",
        zone:"iOS"
    },
    private_browsing_android: {
        sender: "WatchDog",
        title: "PrivateBrowsing",
        description: "Check the new feature: private browsing for Android",
        action_name:"private_browsing",
        zone:"Android"
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
                length:255
            },
            sender:{
                type: "string",
                length:255
            },
            zone:{
                type: "string",
                index: true,
                length:255
            },
            action_argument:{
                type:"string",
                length:255
            },
            action_name:{
                type:"string",
                length:255
            },
            title:{
                type: "string",
                length:255
            },
            description:{
                type: "string",
                length:1024
            },
            expirationDate:{
                type: "date"
            },
            creationDate:{
                type:"date"
            }
        }
    },
        {

            modelName:"DismissedNotifications",
            dataModel:{
                "id":{
                    type:"string",
                    length:255,
                    pk:true
                },
                "userId":{
                    type:"string",
                    length:255,
                    index:true
                },
                "notificationId":{
                    type:"string",
                    length:255
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

createNotification = function (rawNotificationData, callback) {
    var notification = apersistence.createRawObject('Notification',uuid.v1());
    rawNotificationData.expirationDate = new Date(rawNotificationData.expirationDate);
    persistence.externalUpdate(notification,rawNotificationData);
    notification.creationDate = new Date();
    persistence.save(notification, callback);
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

getNotifications = function (userId, userZones, callback) {

    flow.create("Get notifications for user", {
        begin: function () {
            this.notifications = [];
            this.isDissmissed = {};
            this.errs = [];
            persistence.filter('DismissedNotifications',{"userId":userId},this.continue("gotDismissedNotifications"))
        },

        gotDismissedNotifications:function(err,dismissedNotifications){
            if(err){
                this.errs.push(err);
            }else{
                var self = this;
                dismissedNotifications.forEach(function(dismissedNotification){
                    self.isDissmissed[dismissedNotification.notificationId] = true;
                })
            }
            persistence.filter("Notification", {zone: userId}, this.continue("gotNotifications"));
            userZones.forEach(function(zone){
                    persistence.filter("Notification",{zone:zone},self.continue("gotNotifications"));
            })
        },

        gotNotifications:function(err,notifications){
            if(err){
                this.errs.push(err);
            }else{
                var self = this;
                notifications.forEach(function(notification){
                    if(!self.isDissmissed[notification.notificationId]){
                        self.notifications.push(notification);
                    }
                })
            }
        },

        deliverNotifications: {
            join:"gotNotifications",
            code:function () {
                if(this.errs.length>0){
                    callback(this.errs, this.notifications);
                }else{
                    callback(undefined,this.notifications)
                }
            }
        }
    })();
};

dismissNotification = function(userIdOrZone, notificationId, callback){
    var dismissedNotification = apersistence.createRawObject("DismissedNotifications",uuid.v1());
    dismissedNotification.userId = userIdOrZone;
    dismissedNotification.notificationId = notificationId;
    persistence.save(dismissedNotification,callback);
};

filterNotifications = function(filter,callback){
    persistence.filter("Notification",filter,callback);
}

generateSignupNotifications = function (callback) {
    flow.create("createSignupNotifications", {
        begin: function () {
            this.notifications = [];
            this.next("getNotificationsFromSystem");
        },

        getNotificationsFromSystem : function(){
            persistence.filter("Notification", {},this.continue("checkNotificationsFromSystem"));
        },
        checkNotificationsFromSystem: function(err, notifications){
            if(err){
                console.log(err.message)
            }
            else{
                if(notifications.length === 0){
                    this.next("iterateNotifications");
                }
                else{
                    this.next("noNotificationCreated");
                }
            }
        },

        iterateNotifications: function () {

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
                    persistence.save(notification, self.continue("notificationCreated"));
                }
            });
        },
        notificationCreated:function(err, notification){
            console.log(uuid.v1());
            this.notifications.push(notification);
        },
        noNotificationCreated: function () {
            callback(undefined,[]);
        },
        end:{
            join:"notificationCreated",
            code:function(){
                callback(null, this.notifications);
            }
        }

    })();
};

clearIdentityNotifications = function(userId){
    clearNotification(userId,signupNotifications.identity.action_name);
}

clearDealsNotifications = function(userId){
    clearNotification(userId,signupNotifications.deals.action_name);
}

clearSocialNetwork = function(userId){
    clearNotification(userId,signupNotifications.privacy_questionnaire.action_name);
}

clearNotification = function(userId, action_name){
    var self = this;
    flow.create("dismissIdentitiesNotifications", {

        begin:function(){
            if(!userId){
                console.log(new Error("userId is invalid"));
            }
            else{
                persistence.filter("Notification", {action_name: action_name}, this.continue("dismissNotificationsByAction"));
            }
        },
        dismissNotificationsByAction:function(err, notifications){

            if(err){
                console.log(err);
            }
            else{
                notifications.forEach(function(notification){
                    self.dismissNotification(userId, notification.notificationId, function(){
                        console.log("Notification dismissed by action");
                    });
                });
            }
        }


    })();
};


var admin = require("firebase-admin");
var serviceAccount = require("./firebaseAdmin.json");
admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: "https://plusprivacy-ef5ac.firebaseio.com"
});

notifyUsers = function (receivers,notification,callback) {
    var toSend = {
        "title":notification.title,
        "description":notification.description?notification.description:"",
        "action_argument":notification.action_argument?notification.action_argument:"",
        "action_name":notification.action_name?notification.action_name:""
    }

    admin.messaging().sendToDevice(receivers, {"data":toSend}).then(function(result){
        callback();
    }).
    catch(callback);
};