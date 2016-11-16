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

var emailsSwarming = {

    registerConversation: function(sender,receiver){
        this['senderEmail'] = sender;
        this['receiverEmail'] = receiver;
        this.swarm('getIdsForConversation');
    },
    getIdsForConversation:{
        /*
            There may be conversations between an outside entity (say Facebook) and one of our users.
            That entity will not have an id in our database.
            In this case we will register it usingthe email address.
        */
        node:"UsersManager",
        code:function(){
            var self = this;
            filterUsers({"email":self.senderEmail},S(function(err,users){
                if(err ){
                    self.error = err;
                    self.home("Failed")
                }else{
                    if(users.length===0){
                        self['senderId'] = self.senderEmail;
                    }else{
                        self['senderId'] = users[0].userId;
                    }
                    filterUsers({"email":self.receiverEmail},S(function(err,users){
                        if(err ){
                            self.error = err;
                            self.home("Failed")
                        }else{
                            if(users.length===0){
                                self['receiverId'] = self.receiverEmail;
                            }else{
                                self['receiverId'] = users[0].userId;
                            }
                            self.swarm("register");
                        }}))
                }}))
        }
    },
    register: {
        node: "EmailAdapter",
        code: function () {
            var self = this;
            registerConversation(self.senderId,self.receiverId,S(function(err,newConversationUUID){
                if(err){
                    self.error = err;
                    console.log("Could not register conversation from "+self.senderEmail+" to "+self.receiverEmail+"\n",err);
                    self.home("Failed");
                }else{
                    self.conversationUUID = newConversationUUID;
                    self.home('conversationRegistered');
                }
            }))
        }
    },

    getConversation:function(conversationUUID){
        this['conversationUUID'] = conversationUUID;
        this.swarm('get');
    },
    get: {
        node: "EmailAdapter",
        code: function () {
            var self = this;
            console.log("Getting conversation "+self.conversationUUID);
            getConversation(self.conversationUUID,S(function(err,requestedConversation){
                console.log(arguments);
                if(err){
                    self.error = err;
                    self.home("Failed")
                }else{
                    self.conversation = requestedConversation;
                    self.swarm('getEmailsForConversation');
                }
            }))
        }
    },
    getEmailsForConversation:{
        node:"UsersManager",
        code:function(){
            var self = this;
            console.log("Getting emails for "+self.conversation.receiverId+" and "+self.conversation.senderId);
            getUserEmail(self.conversation.receiverId,S(function(err,user){
                if(err){
                    self.error = err;
                    console.log("User with id "+self.conversation.receiverId+" could not be retrieved\n",err);
                    self.home("Failed");
                }else{
                    self['receiverEmail'] = user.email;
                    getUserEmail(self.conversation.senderId,S(function(err,user){
                        if(err){
                            self.error = err;
                            console.log("User with id "+self.conversation.senderId+" could not be retrieved\n",err);
                            self.home("Failed");
                        }else{
                            self['senderEmail'] = user.email;
                            self.home("gotConversation");
                        }}))
                }}));

            function getUserEmail(id,callback){
                if(is.indexOf("@")>-1){
                    callback(null,id);
                }else{
                    getUserInfo(id,S(function(err,user){
                        if(err){
                            callback(err);
                        }else{
                            callback(err,user.email)
                        }
                    }))
                }
            }
        }
    },

    removeConversation:function(conversationUUID){
        this['conversationUUID'] = conversationUUID;
        this.swarm('remove');
    },
    remove: {
        node: "EmailAdapter",
        code: function () {
            var self = this;
            removeConversation(self.conversationUUID,S(function(err,removalResult){
                if(err){
                    self.error = err;
                    self.home('Failed')
                }else{
                    self.result = removalResult;
                    self.home('conversationRemoved');
                }
            }))
        }
    },


    resetPassword:function(email){
        console.log("Resetting password for email:"+email);
	    this['newPassword'] = new Buffer(require('node-uuid').v1()).toString('base64').slice(0,20);
       	this['email'] = email;
        this.swarm('changePassword');
    },
    setNewPassword:function(email,newPassword){
        this['newPassword'] = newPassword;
        this['email'] = email;
        this.swarm('changePassword');
    },
    changePassword:{
        node:'UsersManager',
        code:function(){
            var newPassword = this['newPassword'];
            var self = this;
            console.log("Change password");
            filterUsers({"email":self.email},S(function(err,users){
                if(err){
                    self.error = err;
                    self.home('resetPasswordFailed');
                } else if(users.length===0){
                    self.error = new Error("No such user! Aborting...");
                    self.home('resetPasswordFailed');
                }else {
                    var user = users[0];
                    changeUserPassword(user.userId, user['password'], newPassword, S(function (err, result) {
                        delete self['newPassword'];
                        if (err) {
                            self.error = err;
                            self.home('resetPasswordFailed');
                        } else {
                            startSwarm("emails.js",
                                "sendEmail",
                                "operando@privatesky.xyz",
                                user['email'],
                                "Reset password",
                                "Your password has been changed \nYour new password is " + newPassword)
                        }
                    }))
                }
            }))
        }
    },

    sendEmail:function(from,to,subject,content){
        this['from'] = from;
        this['to'] = to;
        this['subject'] = subject;
        this['content'] = content;
        this.swarm('prepareEmailDelivery');
    },
    prepareEmailDelivery:{
        node:"UsersManager",
        code:function(){
            var self = this;
            filterUsers({"email":self.from},S(function(err,users){
                if(err ){
                    self.error = err;
                    self.home("Failed")
                }else{
                    if(users.length===0){
                        self['senderId'] = self.senderEmail;
                    }else{
                        self['senderId'] = users[0].userId;
                    }
                    filterUsers({"email":self.to},S(function(err,users){
                        if(err ){
                            self.error = err;
                            self.home("Failed")
                        }else{
                            if(users.length===0){
                                self['receiverId'] = self.receiverEmail;
                            }else{
                                self['receiverId'] = users[0].userId;
                            }
                            self.swarm("deliverEmail");
                        }}))
                }}))
        }
    },
    deliverEmail:{
        node: "EmailAdapter",
        code: function () {
            console.log("Delivering an email to ",this['to']);
            var self = this;
            registerConversation(self['senderId'],self['receiverId'],S(function(err,conversationUUID) {
                if(err){
                    self.error = err;
                    self.home("Failed");
                }else{
                    sendEmail(self['from'], conversationUUID+"@privatesky.xyz", self['subject'], self['content'], S(function (err, deliveryResult) {
                        delete self['from'];
                        delete self['to'];
                        delete self['subject'];
                        delete self['content'];
                        delete self['senderId'];
                        delete self['receiverId'];

                        if (err) {
                            self.error = err;
                            self.home('emailDeliveryUnsuccessful');
                        } else {
                            self.deliveryResult = deliveryResult;
                            self.home('emailDeliverySuccessful');
                        }
                    }))
                }
            }))

        }
    }
};

emailsSwarming;
