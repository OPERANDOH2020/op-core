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
            getUserInfo(self.userId, S(function(err, user){
                if(err){
                    self.err = err.message;
                    self.home('failed');
                    console.log(err);
                }
                else{
                    delete user['is_active'];
                    delete user['activationCode'];
                    self.result = user;
                    self.home("result");
                }
            }));
        }
    },

    updateUserInfo:function(updatedInfo){
        this.updatedInfo = updatedInfo;
        this.swarm("checkUserInfo");
    },

    changePassword:function(currentPassword, newPassword){
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.swarm("changeUserPassword");
    },
    generateAnAuthenticationToken:function(){
        this.sessionId = this.getSessionId();
        this.swarm("generateAuthenticationToken");
    },

    checkUserInfo: {
        node: "UsersManager",
        code: function () {
            //check if this email exits in system
            if (this.updatedInfo['email']) {
                var self = this;
                filterUsers({email: this.updatedInfo['email']}, S(function (err, users) {
                    if (err) {
                        self.error = err.message;
                        self.home("userUpdateFailed");
                    }
                    else if (users.length === 0) {
                        self.swarm("updateUserAccount")
                    }
                    else {
                        self.error = new Error("This email is unavailable!").message;
                        self.home("userUpdateFailed");
                    }
                }));
            }
        }
    },

    updateUserAccount:{
        node:"UsersManager",
        code: function(){
            this.updatedInfo.userId = this.meta.userId;
            var self = this;
            updateUser(this.updatedInfo, S(function(err, user){
                if(err){
                    self.error = err.message;
                    self.home("userUpdateFailed");
                }
                else{
                    self.user = user;
                    self.swarm("updateUserRealIdentity");
                }
            }));
        }
    },

    updateUserRealIdentity: {
        node: "IdentityManager",
        code: function () {
            var self = this;
            changeRealIdentity(this.user, S(function(err, identity){
                if(err){
                    self.error = err.message;
                    self.home("userUpdateFailed");
                }
                else if(identity){
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
                    delete self['newPassword'];
                    self.home("passwordChangeFailure");
                }
                else {
                    var newPassword = self['newPassword'];
                    delete self['newPassword'];
                    self.home("passwordSuccessfullyChanged");
                    startSwarm("emails.js",
                        "sendEmail",
                        "no-reply@"+thisAdapter.config.Core.operandoHost,
                        user['email'],
                        "Changed password",
                        "Your password has been changed \nYour new password is " + newPassword,
                        self.meta['swarmId']);
                }
            }));
        }
    },

    resetPassword:function(email){
        console.log("Resetting password for email:" + email);
        this['email'] = email;
        this.swarm('generateResetLink');
    },
    generateResetLink: {
        node: "UsersManager",
        code: function () {
            var self = this;
            filterUsers({"email": self.email}, S(function (err, users) {
                if (err) {
                    self.error = err.message;
                    self.home('resetPasswordFailed');
                } else if (users.length === 0) {
                    self.error = "No such user! Aborting...";
                    self.home('resetPasswordFailed');
                }
                else {
                    genereateResetPasswordToken(users[0].userId,S(function(err,passwordResetRequest){
                        if(err){
                            self.error = err.message;
                            self.home('resetPasswordFailed')
                        }else{
                            startSwarm("emails.js",
                                "sendEmail",
                                "no-reply@"+thisAdapter.config.Core.operandoHost,
                                users[0].email,
                                "Reset password",
                                "A password reset request was issued for your account.\nPlease access the following link to reset the password.\n\n" +
                                "https://www."+thisAdapter.config.Core.operandoHost + "/resetPassword/"+passwordResetRequest.id+"\n\nThe link expires in 24 hours."
                            );
                            /*
                            In the client wait for the confirmation that the email containing the activation link was sent.
                             */
                        }
                    }))
                }
            }))
        }
    },

    performResetPassword:function(resetToken,newPassword){
        this.resetToken = resetToken;
        this.newPassword = newPassword;
        this.swarm("reset");
    },
    reset:{
        node:"UsersManager",
        code:function(){
            var self = this;
            validateResetPasswordToken(self.resetToken,S(function(err,resetPasswordRequest){
                if(err){
                    self.error = err.message;
                    self.home('resetPasswordFailed')
                }else{
                    setNewPassword(resetPasswordRequest.user,self.newPassword,S(function(err,result){
                        if(err){
                            self.error = err.message;
                            self.home('resetPasswordFailed')
                        }else{
                            self.home('resetPasswordSuccessful');
                            invalidateResetPasswordToken(self.resetToken,S(function(err,result){
                                if(err){
                                    self.error = err.message;
                                    self.home('tokenInvalidationFailed');
                                }else{
                                    self.home('tokenInvalidationSuccessful');
                                }
                            }))
                        }
                    }))
                }
            }))
        }
    },

    generateAuthenticationToken:{
        node:"SessionManager",
        code:function(){
            var userId = this.meta.userId;
            var self = this;
            generateAuthenticationToken(userId, this.sessionId, S(function(err, authenticationToken){
                if(err){
                    self.error = err.message;
                    self.home("generateAuthenticationTokenFailed");
                }
                else{
                    self.authenticationToken = authenticationToken;
                    self.home("generateAuthenticationTokenSuccess");
                }

            }));
        }
    }
}

userInfoSwarming;