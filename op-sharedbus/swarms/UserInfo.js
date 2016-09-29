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
    vars:{
        userId:null,
        organisationId:null
    },
    info:function(userId){
        this.userId = userId;
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
            var user = getUserInfo.async(self.userId);
            (function(user){
                self.result = user;
                self.home("result");
            }).swait(user);
        }
    },

    updateUserInfo:function(updatedInfo){
        console.log("new request", updatedInfo);
        this.updatedInfo = updatedInfo;
        this.swarm("updateUserAccount");
    },

    updateUserAccount:{
        node:"UsersManager",
        code: function(){
            this.updatedInfo.userId=this.meta.userId;
            var user = updateUser.async(this.updatedInfo);
            var self = this;
            (function(user){
                self.user = user;
                self.home("updatedUserInfo");
            }).swait(user, function(err){
                console.log(err);
                self.home("userUpdateFailed");
            });
        }
    },

    error:{
        node:"Core",
        code:function(){
            self.err = err;
            this.home("error");
        }

    }


};

userInfoSwarming;