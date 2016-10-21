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


apersistence.registerModel("Notification","Redis",{
    notificationId:{
        type:"string",
        pk:true,
        index:true
    },
    forUser:{
        type: "string",
        index: true
    },
    type:{
        type: "string",
        index: true
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
    ctor: function(){

    }
});


getNotifications = function (userId, callback) {
    flow.create("Get notifications for user", {
        begin: function () {
            redisPersistence.filter("Notification", {"forUser": userId}, this.continue("getNotifications"));
        },
        getNotifications: function (err, notifications) {
            var list = [];
            notifications.forEach(function (n) {
                    list.push(user);
            });
            callback(err, list);
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



createNotification = function (userId, type, title, description, callback) {

    flow.create("Create Notification", {
        begin: function () {
            if (!userData.userId) {
                callback(new Error('Empty userId'), null);
            }
            else {
                redisPersistence.lookup("Notification", uuid.v1(), this.continue("createUser"));
            }
        },
        createUser: function (err, notification) {
            if (!redisPersistence.isFresh(notification)) {
                callback(new Error("notification with identical id " + notification.notificationId + " already exists"), null);
            } else {
                notification.forUser        = userId;
                notification.type           = type;
                notification.title          = title;
                notification.description    = description;

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
                callback(new Error("Organisation with id " + notification.notificationId + " was not found"), null);
            }
            else {
                redisPersistence.externalUpdate(organisation, notificationDump);
                redisPersistence.saveObject(organisation, callback);
            }
        }
    })();
};
