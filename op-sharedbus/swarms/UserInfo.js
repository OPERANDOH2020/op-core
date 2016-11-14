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
                delete self.newPassword;
                if (err) {
                    self.error = err.message;
                    self.home("passwordChangeFailure");
                }
                else {
                    self.home("passwordSuccessfullyChanged");
                }
            }));
        }
    }

};

userInfoSwarming;