/*
 * Copyright (c) 2017 ROMSOFT.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the The MIT License (MIT).
 * which accompanies this distribution, and is available at
 * http://opensource.org/licenses/MIT
 *
 * Contributors:
 *    RAFAEL MASTALERU (ROMSOFT)
 * Initially developed in the context of OPERANDO EU project www.operando.eu
 */


var osp = {
    meta: {
        name: "osp.js"
    },

    getRequests: function () {
        this.swarm("getOspRequestsFromManager");
    },

    removeOSPRequest:function(ospUserId, feedbackMessage){
        this.feedbackMessage = feedbackMessage;
        this.ospUserId = ospUserId;
        this.swarm("removeOspRequestPhase");
    },

    acceptOSPRequest:function(ospUserId){
        this.ospUserId = ospUserId;
        this.swarm("retrieveOspRequestDataPhase");
    },

    listOSPs:function(){
        this.swarm("listOSPsPhase");
    },
    listOSPOffers:function(){
        this.userId = this.meta.userId;
        this.swarm("listOSPOffersPhase");
    },
    addOspOffer:function(offerDetails){
        this.userId = this.meta.userId;
        this.offerDetails = offerDetails;
        this.swarm("createOspOffer");
    },
    deleteOspOffer:function(offerId){
      this.offerId = offerId;
      this.swarm("deleteOspOfferPhase");
    },

    getOffersStats:function(ospId){
        this.ospId = ospId;
        this.swarm("getOSPOffers");
    },
    getOspRequestsFromManager: {
        node: "OSPRequests",
        code: function () {
            var self = this;
            getOSPRequests(S(function(err, ospRequests){
                if(err){
                    self.error = err.message;
                    self.home("failed");
                }
                else{
                    self.ospRequests = ospRequests;
                    self.swarm("getRequestersEmails");
                }
            }));
        }
    },

    retrieveOspRequestDataPhase: {
        node: "OSPRequests",
        code: function () {
            var self = this;
            getOSPRequest(self.ospUserId, S(function (err, ospDataRequests) {
                if (err) {
                    self.error = err.message;
                    self.home("failed");
                }
                else {
                    if (ospDataRequests.length > 0) {
                        self.requestData = ospDataRequests[0];
                        self.swarm("acceptOspRequestPhase");
                    }
                    else {
                        self.error = "noRequestsFoundForThisUser";
                        self.home("failed");
                    }
                }
            }));
        }
    },
    removeOspRequestPhase:{
        node:"OSPRequests",
        code:function(){
            var self = this;
            removeOSPRequest(this.ospUserId, S(function(err, ospRequest){
                if(err){
                    self.error = err.message;
                    self.home("failed");
                }else{

                    startSwarm("emails.js", "sendEmail", "no-reply@" + thisAdapter.config.Core.operandoHost,
                        ospRequest['email'],
                        "Your OSP request was not accepted",
                        "Unfortunately your request was not accepted. Below is the reason :  \n" +self.feedbackMessage);

                    self.home("success");
                }
            }));
        }
    },
    acceptOspRequestPhase:{
      node:"OSPAdapter",
        code:function(){
            var self = this;
            registerNewOSP(self.ospUserId, self.requestData, S(function(err, ospRequest){
                console.log(err, ospRequest);
                if(err){
                    self.error = err.message;
                    self.home("failed");
                }
                else{

                    startSwarm("emails.js", "sendEmail", "no-reply@" + thisAdapter.config.Core.operandoHost,
                        ospRequest['email'],
                        "Your OSP request was accepted",
                        "Congratulations,\n" +
                        "Your OSP request was accepted\n" +
                        "Login at http://plusprivacy.com/osp-login/ and start using the service");

                    self.swarm("changeUserOrganisation");
                }
            }));
        }
    },

    changeUserOrganisation:{
        node:"UsersManager",
        code:function(){
            var self = this;
            var updateData = {
                userId:self.ospUserId,
                organisationId:"OSP"
            };

            updateUser(updateData, S(function (err, user) {
                if (err) {
                    self.error = err.message;
                    self.home("failed");
                } else {
                    self.swarm("removeOspRequestPhase");
                }
            }));
        }
    },

    getRequestersEmails: {
        node: "UsersManager",
        code: function () {
            var self = this;
            var emailsRetrieved = 0;
            var emailToBeRetrieved = self.ospRequests.length;
            if(self.ospRequests.length>0){
                for (var i = 0; i < self.ospRequests.length; i++) {
                    (function (index) {
                        getUserInfo(self.ospRequests[index].userId, S(function (err, user) {
                            if (err) {
                                console.error(err);
                                emailToBeRetrieved--;
                            } else if (user === null) {
                                //orphan case
                                emailToBeRetrieved--;
                            }
                            else {
                                self.ospRequests[index]['email'] = user['email'];
                                emailsRetrieved++;
                            }

                            if(emailsRetrieved === emailToBeRetrieved){
                                self.home("success");
                            }

                        }));
                    })(i);
                }
            }
            else{
                self.home("success");
            }
        }
    },

    listOSPsPhase: {
        node: "OSPAdapter",
        code: function () {
            var self = this;
            getOSPs(S(function (err, ospList) {
                if (err) {
                    self.error = err.message;
                    self.home("failed");
                } else {
                    self.ospList = ospList;
                    if(self.ospList.length === 0){
                        self.home("success");
                    }
                    else{
                        self.swarm("getOSPsEmails");
                    }

                }
            }))
        }
    },

    listOSPOffersPhase:{
        node: "OSPAdapter",
        code: function () {
            var self = this;
            getOspOffers(self.userId, S(function (err, offers) {
                if (err) {
                    self.error = err.message;
                    self.home("failed");
                } else {
                    self.offers = offers;
                    self.home("success");
                }
            }));
        }
    },

    getOSPsEmails: {
        node: "UsersManager",
        code: function () {
            var self = this;
            var emailsRetrieved = 0;
            var emailToBeRetrieved = self.ospList.length;
            for (var i = 0; i < self.ospList.length; i++) {
                (function (index) {
                    getUserInfo(self.ospList[index].userId, S(function (err, user) {

                        if (err) {
                            console.error(err);
                            emailToBeRetrieved--;
                        }
                        else if (user === null) {
                            //orphan case
                            emailToBeRetrieved--;
                        } else {
                            self.ospList[index]['email'] = user['email'];
                            emailsRetrieved++;
                        }
                        if(emailsRetrieved === emailToBeRetrieved){
                            self.home("success");
                        }

                    }));
                })(i);
            }
        }
    },

    createOspOffer: {
        node: "OSPAdapter",
        code: function () {
            var self = this;
            addOrUpdateOspOffer(self.userId, self.offerDetails, S(function (err, offer) {
                if (err) {
                    self.error = err.message;
                    self.home("failed");
                }
                else {
                    self.offer = offer;
                    self.home("success");
                }
            }));
        }
    },

    deleteOspOfferPhase:{
        node: "OSPAdapter",
        code: function () {
            var self = this;
            deleteOspOffer(self.offerId, S(function(err, offer){
                if (err) {
                    self.error = err.message;
                    self.home("failed");
                }
                else {
                    self.offer = offer;
                    self.home("success");
                }
            }));
        }
    },

    getOSPOffers:{
        node:"OSPAdapter",
        code: function(){
            var self = this;
            getOspOffers(self.ospId,S(function(err, offers){
                if (err) {
                    self.error = err.message;
                    self.home("failed");
                }
                else {
                    self.offersStats = offers;
                    self.swarm("getAcceptedDeals");

                }
            }));
        }
    },
    getAcceptedDeals:{
        node:"PrivacyForBenefitsManager",
        code:function(){
            this.offersStats.forEach(function(offer){
                getOSPAcceptedOffers(offer.offerId, S(function(err, acceptedOffers){
                    if(err){
                        console.log(err);
                    }
                    offer.deals_number = acceptedOffers.length;
                }));
            });

            this.home("success");
        }
    }
};
osp;