/*
 * Copyright (c) 2016 ROMSOFT.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the The MIT License (MIT).
 * which accompanies this distribution, and is available at
 * http://opensource.org/licenses/MIT
 *
 * Contributors:
 *    Ciprian Tălmăcel (ROMSOFT)
 *    Sinică Alboaie (ROMSOFT)
 * Initially developed in the context of OPERANDO EU project www.operando.eu
 */
var userInfoSwarming =
{
    meta:{
        name:"UserInfo.js"
    },
    info:function(){
        this.userId = this.meta.userId;
        this.swarm("getUserInfo");
    },

    getAllUsers:function(organisationId){
        this.organisationId = organisationId;
        this.swarm("getOrganisationUsers");
    },

    getOrganisationUsers:{
        node:"UsersManager",
        code: function(){
            var self = this;
            queryUsers(organisationId, S(function(err, users){
                if(err){
                    self.err = err;
                    self.swarm("error");

                }
            }));
        }
    },

    getUserInfo:{
        node:"UsersManager",
        code : function (){
            var self = this;
            var user = getUserInfo(self.userId, S(function(err, user){
                if(err){
                    console.log(err);
                }
                else{

                    self.result = user;
                    self.home("result");
                }
            }));
        }
    },

    updateUserInfo:function(updatedInfo){
        console.log(updatedInfo);
        this.updatedInfo = updatedInfo;
        this.swarm("updateUserAccount");
    },

    changePassword:function(currentPassword, newPassword){
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        console.log(currentPassword,newPassword);
        this.swarm("changeUserPassword");
    },

    updateUserAccount:{
        node:"UsersManager",
        code: function(){
            this.updatedInfo.userId=this.meta.userId;
            var self = this;
            updateUser(this.updatedInfo, S(function(err, user){
                if(err){
                    console.log(err);
                    self.error = err;
                    self.home("userUpdateFailed");
                }
                else{
                    self.user = user;
                    self.home("updatedUserInfo");
                }
            }));
        }
    },

    changeUserPassword:{
        node:"UsersManager",
        code:function(){
            var self = this;
            changeUserPassword(this.meta.userId, this.currentPassword, this.newPassword, S(function (err, user) {
                delete self.currentPassword;
                if (err) {
                    self.error = err.message;
                    self.home("passwordChangeFailure");
                }
                else {
                    var newPassword = self['newPassword'];
                    delete self['newPassword'];
                    startSwarm("emails.js",
                        "sendEmail",
                        "operando@privatesky.xyz",
                        user['email'],
                        "Changed password",
                        "Your password has been changed \nYour new password is " + newPassword);
                }
            }));
        }
    },
    
    resetPassword:function(email){
        console.log("Resetting password for email:"+email);
        this['newPassword'] = new Buffer(require('node-uuid').v1()).toString('base64').slice(0,20);
        this['email'] = email;
        this.swarm('setNewPassword');
    },
    setNewPassword: {
        node: "UsersManager",
        code: function () {
            var self = this;
            filterUsers({"email": self.email}, S(function (err, users) {
                if (err) {
                    self.error = err.message;
                    self.home('resetPasswordFailed');
                } else if (users.length === 0) {
                    self.error = new Error("No such user! Aborting...");
                    self.home('resetPasswordFailed');
                }
                else {
                    setNewPassword(users[0], self['newPassword'], S(function (err, res) {
                        if(err){
                            self.error = err.message;
                            self.home('resetPasswordFailed');
                        }else{
                            console.log("Starting new emails swarm");
                            var newPassword = self['newPassword'];
                            delete self['newPassword'];
                            startSwarm("emails.js",
                                "sendEmail",
                                "operando@privatesky.xyz",
                                user['email'],
                                "Reset password",
                                "Your password has been changed \nYour new password is " + newPassword);
                        }

                    }))
                }
            }))
        }
    }
}

userInfoSwarming;