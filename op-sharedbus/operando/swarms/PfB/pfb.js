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

    viewDeal: function () {
        //TODO
        //Implement viewDeal ctor
    },


    getActiveDeals:function(){

        this.action = "listAvailableDeals";
        this.swarm("checkUser");
    },

    getMyDeals: function () {
        this.action = "listMyDeals";
        this.swarm("checkUser");
    },

    acceptDeal: function (dealId) {
        this.dealId = dealId;
        this.action = "acceptPfBDeal";
        this.swarm("checkUser");
    },

    refuseDeal: function () {
        //TODO
        //Implement refuseDeal ctor
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
                this.action="getWebsitePfBDeal";
                this.swarm("checkUser");
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

            getPfBDeal(self.userId, self.website, S(function(err, deal){

                if (deal != null) {
                    self.deal = deal;
                    self.home("success");
                } else {
                    self.home("no_pfb");
                }

            }));
        }
    },


    checkUser: {
        node: "SessionManager",
        code: function () {
            var self = this;
            getUserBySession(this.getSessionId(), S(function (err, userId) {

                if (err != null) {
                    self.error.message = err.message;
                    self.swarm("error");
                }
                else {
                    self.userId = userId;
                    switch (self.action) {
                        case "acceptPfBDeal":
                            self.swarm("acceptPfBDeal");
                            break;
                        case "listMyDeals":
                            self.swarm("listMyDeals");
                            break;
                        case "listAvailableDeals":
                            self.swarm("getAllDealsSwarm");
                            break;
                        case "getWebsitePfBDeal":
                            self.swarm("getWebsitePfBDeal");
                    }
                }
            }));
        }
    },


    acceptPfBDeal: {
        node: "PrivacyForBenefitsManager",
        code: function () {
            var self = this;
            saveUserDeal(self.dealId, self.userId,function(err, deal){
                console.log(deal);
            })
        }
    },

    listMyDeals: {
        node: "PrivacyForBenefitsManager",
        code: function () {
            var self = this;
            getUserDeals(self.userId, S(function(err, deals){
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
            getAllDeals(self.userId,S(function (err, deals) {
                if (deals) {
                    self.deals = deals;
                    self.home("gotActiveDeals");
                }
            }));

        }
    }
}
privacyForBenefits;