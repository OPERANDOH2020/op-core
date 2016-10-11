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
        this['sender'] = sender;
        this['receiver'] = receiver;
        this.swarm('register');
    },
    getConversation:function(conversationUUID){
        this['conversationUUID'] = conversationUUID;
        this.swarm('get');
    },
    removeConversation:function(conversationUUID){
        this['conversationUUID'] = conversationUUID;
        this.swarm('remove');
    },
    register: {
        node: "EmailAdapter",
        code: function () {
            var self = this;
            registerConversation(self.sender,self.receiver,S(function(err,newConversationUUID){
                if(err){
                    self.error = err;
                }else{
                    self.conversationUUID = newConversationUUID;
                }
                self.home('conversationRegistered');
            }))
        }
    },
    get: {
        node: "EmailAdapter",
        code: function () {
            var self = this;
            getConversation(self.conversationUUID,S(function(err,requestedConversation){
                if(err){
                    self.error = err;
                }else{
                    self.conversation = requestedConversation;
                }
                self.home('gotConversation');
            }))


        }
    },
    remove: {
        node: "EmailAdapter",
        code: function () {
            var self = this;
            removeConversation(self.conversationUUID,S(function(err,removalResult){
                if(err){
                    self.error = err;
                }else{
                    self.result = removalResult;
                }
                self.home('conversationRemoved');
            }))
        }
    }
};

emailsSwarming;