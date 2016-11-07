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

var privacyForBenefits = {
    meta: {
        name: "pfb.js"
    },

    vars: {
        deal: null,
        deals: null,
        dealId: null,
        website: null,
        tabId: null,
        action: null
    },

    getActiveDeals:function(){
        this.swarm("getAllDealsSwarm");
    },

    getMyDeals: function () {
        this.swarm("listMyDeals");
    },

    getAllDeals:function(){
      this.swarm("listAllPfbDeals");
    },

    acceptDeal: function (dealId) {
        this.dealId = dealId;
        this.swarm("acceptPfBDeal");
    },

    unsubscribeDeal:function(dealId){
        this.dealId = dealId;
        this.swarm("unsubcribePfBDeal");
    },

    getWebsiteDeal: function (_website, _tabId) {

        if (_website.indexOf("://") > -1) {
            this.website = _website.split('/')[2];
        }
        else {
            this.website = _website.split('/')[0];
        }

        if (this.website.indexOf("www.") > -1) {
            this.website = this.website.split('www.')[1];
        }

        //find & remove port number
        this.website = this.website.split(':')[0];
        this.tabId = _tabId;
        this.swarm("websiteHasDeal");
    },
    websiteHasDeal: {
        node: "PrivacyForBenefitsManager",
        code: function () {
            if (websiteHasPfBDeal(this.website)) {
                this.swarm("getWebsitePfBDeal");
            }
            else {
                this.home("no_pfb");
            }
        }
    },
    getWebsitePfBDeal: {
        node: "PrivacyForBenefitsManager",
        code: function () {
            var self = this;

            getPfBDeal(self.meta.userId, self.website, S(function(err, deal){

                if (deal != null) {
                    self.deal = deal;
                    self.home("success");
                } else {
                    self.home("no_pfb");
                }
            }));
        }
    },

    listAllPfbDeals: {
        node: "PrivacyForBenefitsManager",
        code:function(){
            var self = this;
            getAllPfbDeals(self.meta.userId, S(function(err, deals){
                if(err){
                    console.log(err);
                }
                else{
                    self.deals = deals;
                    self.home("gotAllDeals");
                }
            }));
        }
    },

    acceptPfBDeal: {
        node: "PrivacyForBenefitsManager",
        code: function () {
            var self = this;
            saveUserDeal(self.dealId, self.meta.userId,S(function(err, deal){
                if (err) {
                    console.log(err);
                }
                else {
                    self.deal = deal;
                    self.action="subscribed";
                    self.swarm("getService");
                    self.home("dealAccepted");
                }
            }));
        }
    },


    getService:{
        node:"PrivacyForBenefitsManager",
        code:function(){
            var self = this;
            getDealDetails(this.deal.pfbId, S(function(err, service){
                 if(err){
                     console.log(err);
                 }
                else{
                     self.service = service;
                     self.swarm("notifyUserByEmail");
                 }
            }));
        }
    },

    notifyUserByEmail:{
        node: "EmailAdapter",
        code: function () {
            this['from']="pfb@operando.eu";
            this['to'] = this.meta.userId;
            this['subject'] = "[OPERANDO] Deal from"+this.service.website;

           if(this.action == "subscribed"){
                this['content'] = "You have successfully subscribed to " + this.service.website;
            }
            else{
                this['content'] = "You have successfully unsubscribed to " + this.service.website;
            }

            var self = this;
            sendEmail(self['from'],self['to'],self['subject'],self['content'],S(function(err, deliveryResult){
                delete self['from'];
                delete self['to'];
                delete self['subject'];
                delete self['content'];

                if(err){
                    self.error = err;
                    console.log(err);
                }else{
                    console.log("Pfb email successfully sent");
                }
            }))
        }
    },

    unsubcribePfBDeal:{
        node: "PrivacyForBenefitsManager",
        code: function () {
            var self = this;
            removeUserDeal(self.dealId, self.meta.userId,S(function(err, deal){
                if(!err){
                    self.deal = deal;
                    self.action = "unsubscribed";
                    self.swarm("getService");
                    self.home("dealUnsubscribed");
                }
            }));
        }
    },

    listMyDeals: {
        node: "PrivacyForBenefitsManager",
        code: function () {
            var self = this;
            getUserDeals(self.meta.userId, S(function(err, deals){
                if(deals){
                    self.deals = deals;
                    self.home("gotMyDeals");
                }
            }));
        }
    },

    getAllDealsSwarm: {
        node: "PrivacyForBenefitsManager",
        code: function () {
            var self = this;
            getAllDeals(self.meta.userId,S(function (err, deals) {
                if (deals) {
                    self.deals = deals;
                    self.home("gotActiveDeals");
                }
            }));

        }
    }
}
privacyForBenefits;