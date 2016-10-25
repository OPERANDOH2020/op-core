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


var notificationSwarming = {

    meta: {
        name: "notification.js"
    },

    vars: {
        notifications: null
    },


    start: function () {
        console.log("Swarm extension started");
    },


    getNotifications: function () {
        this.swarm("getUserNotifications");
    },

    dismissNotification:function(notificationId, dismissed){
        this.notificationId = notificationId;
        this.dismissed = dismissed;
        this.swarm("dismissUserNotification");
    },

    sendNotification:function(){
        //TODO
        //Implement sendNotification ctor
    },
    deleteNotification:function(){
        //TODO
        //Implement deleteNotification ctor
    },

    getUserNotifications:{
        node:"NotificationUAM",
        code:function(){
            var self  = this;
            getNotifications(this.meta.userId, S(function(err, notifications){
                if(err){
                    console.log(err);
                }
                else{
                    self.notifications = notifications;
                    self.home("gotNotifications");
                }
            }));
        }
    },

    dismissUserNotification:{
        node:"NotificationUAM",
        code:function(){
            var self = this;
            var notificationData = {
                notificationId:this.notificationId,
                dismissed:this.dismissed
            }
            updateNotification(notificationData, S(function(err, notification){
                if(err){
                    console.log("Error on dismissing notification",err);
                }
                else{
                    self.home("notificationDismissed");
                }
            }));

        }
    },



    success: {
        node: "Core",
        code: function () {
            console.log("Returning Notifications");
            this.notifications = [{
                message: "Pellentesque semper augue sed suscipit fringilla. Etiam vitae gravida augue, id tempus enim.",
                title: "Security error FACEBOOK MESSENGER",
                type: "SECURITY",
                action: "UNINSTALL",
                targetId: "com.facebook.orca"
            },
                {
                    message: "Pellentesque semper augue sed suscipit fringilla. Etiam vitae gravida augue, id tempus enim. ",
                    title: "Security error FACEBOOK",
                    type: "PRIVACY",
                    action: "DISABLE",
                    targetId: "com.facebook.katana"
                },
                {
                    message: "Pellentesque semper augue sed suscipit fringilla. Etiam vitae gravida augue, id tempus enim. ",
                    title: "Security error INSTAGRAM",
                    type: "PRIVACY",
                    action: "DISABLE",
                    targetId: "com.instagram.android"
                }];
            this.home("success");
        }

    }

}
notificationSwarming;
