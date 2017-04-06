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
    getNotifications: function () {
        this.swarm("getUserNotifications");
    },
    getUserZones:{
        node:"UsersManager",
        code:function() {
            var self = this;
            zonesOfUser(this.meta.userId, function (err, zones) {
                if (err) {
                    self.err = err.message;
                    self.home('failed');
                } else {
                    self.zones = zones.map(function (zone) {
                        return zone.zoneName;
                    });
                    self.swarm("getUserNotifications");
                }
            })
        }
    },
    getUserNotifications:{
        node:"NotificationUAM",
        code:function(){
            var self  = this;
            getNotifications(this.meta.userId,this.zones, S(function(err, notifications){
                if(err){
                    self.err = err.message;
                    console.log(err);
                    self.home('failed');
                }
                else{
                    self.notifications = notifications;
                    self.home("gotNotifications");
                }
            }));
        }
    },

    dismissNotification:function(notificationId, userId){
        if(!userId){
            this.userId = this.meta.userId;
        }else{
            this.userId = userId;
        }
        this.notificationId = notificationId;
        this.swarm("dismissUserNotification");
    },
    dismissUserNotification:{
        node:"NotificationUAM",
        code:function(){
            var self  = this;
            dismissNotification(this.userId,S(function(err){
                if(err){
                    self.err = err.message;
                    console.log(err);
                    self.home('failed');
                }
                else{
                    self.home("notificationDismissed");
                }
            }));
        }
    },

    sendNotification:function(notification){
        this.notification = notification;
        this.swarm("send")
    },
    send:{
        node:"NotificationUAM",
        code:function(){
            var self = this;
            self.notification.sender = this.meta.userId;

            createNotification(self.notification,function(err,notification){
                if(err){
                    self.err = err.message;
                    self.home('failed');
                }else{
                    notifyLoggedUsers(self.notification,function(err){
                        if(err){
                            self.err = err.message;
                            self.home('failed')
                        }else{
                            self.home('notificationSent');
                        }
                    })
                }
            })
        }
    },

    getFilteredNotifications(filter){
        if(!filter){
            this.filter = {}
        }else{
            this.filter = filter;
        }
        this.swarm('filter');
    },
    filter:{
        node:"NotificationUAM",
        code:function(){
            var self = this;
            filterNotifications(this.filter,S(function(err,result){
                if(err){
                    self.err = err.message;
                    self.home('failed')
                }else{
                    self.notifications = result;
                    self.home('gotFilteredNotifications');
                }
            }))
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

    },
    
    EULAChange:function(url){
        var notification = {}
        notification.title = "EULA change";
        notification.zone = "Analysts";
        notification.action = "Access link "+url;
        notification.description = "An EULA change was detected at the url "+url+"\nYou might want to check.";
        notification.creationDate = new Date();
        notification.sender = "Web Crawler";
        this.notification = notification;
        this.swarm('send');
    },
    SettingsChange:function(url) {
        var notification = {};
        notification.title = "Settings change";
        notification.zone = "Analysts";
        notification.action = "Access link " + url;
        notification.description = "A setting change was detected at the url " + url + "\nYou might want to check.";
        notification.creationDate = new Date();
        notification.sender = "Web Crawler";
        this.notification = notification;
        this.swarm('send');
    }
}
notificationSwarming;
