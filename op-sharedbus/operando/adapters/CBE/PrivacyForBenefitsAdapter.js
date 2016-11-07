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


var dummyVendors = [
    {
        serviceId: 0,
        website: "9gag.com",
        benefit: "5 &euro;",
        identifier: ".btn-connect-option.facebook.badge-facebook-connect",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        logo:"https://upload.wikimedia.org/wikipedia/commons/thumb/9/97/9GAG_new_logo.svg/2000px-9GAG_new_logo.svg.png"
    },
    {
        serviceId: 1,
        website: "nytimes.com",
        benefit: "2 &euro;",
        identifier: "#facebook-oauth-button-registration-modal, #facebook-oauth-button-login-modal",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dolor diam, pharetra vel velit in, finibus mollis purus.",
        logo:"https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg"
    },
    {
        serviceId: 2,
        website: "dribbble.com",
        benefit: "2 &euro;",
        identifier: ".sign-connections .auth-twitter, .signup-twitter .auth-twitter",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dolor diam, pharetra vel velit in, finibus mollis purus. Sed luctus mattis porta.",
        logo:"http://www.userlogos.org/files/logos/jumpordie/dribbble_01.png"
    },
    {
        serviceId: 3,
        website: "ssl.bbc.com",
        benefit: "free content",
        identifier: ".bbcid-facebook-com",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        logo:"https://upload.wikimedia.org/wikipedia/en/thumb/f/ff/BBC_News.svg/1280px-BBC_News.svg.png"
    },
    {
        serviceId: 4,
        website: "flightradar24.com",
        benefit: "free content",
        identifier: ".fr24-icon-facebook",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        logo:"https://lh3.googleusercontent.com/P0aVrLO7Wxob0dIBx80m85wUyHFiK3-Xw7Xw_6CDFAogdeoRR0YWnSjkFNOR4M7nIA=w300"
    },{
        serviceId: 5,
        website: "etsy.com",
        benefit: "free content",
        identifier: ".google-connect-button",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dolor diam, pharetra vel velit in, finibus mollis purus. Sed luctus mattis porta. ",
        logo:"http://www.brandsoftheworld.com/sites/default/files/styles/logo-thumbnail/public/122010/etsy-thumb.png"


    },{
        serviceId: 6,
        website: "viagogo.fr",
        benefit: "Get 20% off your first ticket price!",
        identifier: "#ExternalLoginForm div button, #ExternalLoginFormNewCustomer div button",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dolor diam, pharetra vel velit in, finibus mollis purus. ",
        logo:"http://www.limerickpost.ie/site/wp-content/uploads/viagogo-logo.jpg"
    },{
        serviceId: 7,
        website: "dating.telegraph.co.uk",
        benefit: "Get first date free",
        identifier: ".joinBtn.fbJoinButton",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dolor diam, pharetra vel velit in, finibus mollis purus. Sed luctus mattis porta. In a massa dignissim, imperdiet eros vitae, facilisis sem.",
        logo:"http://is.i2.datinglab.net/pics/i2/4/chrome/logo_4.svg"
    }
]


var core = require("swarmcore");
core.createAdapter("PrivacyForBenefitsManager");
var apersistence = require('apersistence');
var container = require("safebox").container;
var flow = require("callflow");
var voucher_codes = require('voucher-code-generator');

apersistence.registerModel("PrivacyForBenefitsService", "Redis", {
        serviceId: {
            type: "string",
            index: "true",
            pk: "true"
        },
        website: {
            type: "string",
            index: "true"
        },
        benefit: {
            type: "string"

        },
    },
    function (err, model) {
        if (err) {
            console.log(model);
        }
    }
);


apersistence.registerModel("UserPfB", "Redis", {
        id: {
            type: "string",
            index: "true",
            pk: "true"
        },
        userId: {
            type: "string",
            index: "true"
        },
        pfbId: {
            type: "string",
            index: "true"
        },
        voucher:{
            type: "string"
        }
    },
    function (err, model) {
        if (err) {
            console.log(model);
        }
    }
);

websiteHasPfBDeal = function (website) {
    for (var i = 0; i < dummyVendors.length; i++) {
        if (dummyVendors[i].website == website) {
            return true;
        }
    }
    return false;
}

getPfBDeal = function (userId, website, callback) {

    var deal;
    flow.create("getWebsitePfBDeal", {
        begin: function () {
            for (var i = 0; i < dummyVendors.length; i++) {
                if (dummyVendors[i].website == website) {
                    deal = dummyVendors[i];
                    break;
                }
            }

            if (deal) {
                redisPersistence.filter("UserPfB", {userId: userId}, this.continue("checkWebsite"));
            }
            else {
                callback(null, null);
            }
        },

        checkWebsite: function (err, deals) {

            var dealIsAlreadyAccepted = false;

            if (deals && deals.length > 0) {
                for (var i = 0; i < deals.length; i++) {
                    if (deals[i].pfbId == deal.serviceId) {
                        dealIsAlreadyAccepted = true;
                        callback(null, null);
                        break;
                    }
                }

                if(dealIsAlreadyAccepted == false){
                    callback(null, deal);
                }
            }
            else{
                callback(null, deal);
            }

        }

    })();

}


getAllDeals = function(userId, callback){
    var availableDeals = dummyVendors.slice(0);
    flow.create("getAllDeals", {
        begin: function(){
            if (!userId) {
                callback(new Error('Empty userId'), null);
            }
            else {
                redisPersistence.filter("UserPfB", {userId:userId}, this.continue("getAllDeals"));
            }
        },
        getAllDeals:function(err, deals){
            if(err){
                callback(err);
            }
            else{
                var i = availableDeals.length;
                while (i--) {
                    for (var k = 0; k < deals.length; k++) {
                        if (availableDeals[i].serviceId == deals[k].pfbId) {
                            availableDeals.splice(i, 1);
                            break;
                        }
                    }
                }
                callback(null, availableDeals);
            }

        }

    })();

}

getUserDeals = function (userId, callback) {
    var userDeals = [];
    flow.create("get user accepted deals", {
        begin: function(){
            if (!userId) {
                callback(new Error('Empty userId'), null);
            }
            else {
                redisPersistence.filter("UserPfB", {userId:userId}, this.continue("populateUserDeals"));
            }
        },
        populateUserDeals: function (err, deals) {
            if(err){
                callback(err);
            }
            else{
                for (var i = 0; i < deals.length; i++) {
                    for (var k = 0; k < dummyVendors.length; k++) {

                        if (dummyVendors[k].serviceId == deals[i].pfbId) {
                            var deal = dummyVendors[k];
                            if(deals[i].voucher){
                                deal.voucher = deals[i].voucher;
                            }
                            userDeals.push(deal);
                            break;
                        }
                    }
                }
                callback(null, userDeals);
            }
        }
    })();
}


getDealDetails = function(pfbId, callback){

    var allDeals = dummyVendors;
    console.log(pfbId);
    var service = allDeals.filter(function(value){
        return value.serviceId === pfbId;
    });

    if(service.length == 0){
        callback(new Error("Service not found"), null);
    }
    else{
        callback(null, service[0]);
    }

}

getAllPfbDeals = function(userId, callback){
    var allDeals = dummyVendors;

    for(var i = 0; i<allDeals.length; i++){
        allDeals[i].subscribed = false;
    }

    getUserDeals(userId, function(err, deals){


      for (var i = 0; i<deals.length; i++){
          for(var j = 0; j<allDeals.length; j++){
              if(deals[i].serviceId === allDeals[j].serviceId)
              {
                  allDeals[j].subscribed = true;
                  break;
              }
          }
      }
        callback(null,allDeals);

    });
}


saveUserDeal = function (dealId, userId, callback) {
    flow.create("store pfb deal", {
        begin: function () {
            if (!userId) {
                callback(new Error('Empty userId'), null);
            }
            else {

                for (var i = 0; i < dummyVendors.length; i++) {
                    var deal = dummyVendors[i];

                    if (deal.serviceId == dealId) {
                        var deal = {
                            userId: userId,
                            pfbId: dealId,
                        }

                        redisPersistence.filter("UserPfB", deal, this.continue("saveDeal"));
                        break;
                    }
                }
            }
        },

        saveDeal: function (err) {

            if(!err){

                redisPersistence.lookup("UserPfB", generateUUID(), function (err, deal) {

                    if (redisPersistence.isFresh(deal)) {
                        deal.userId = userId;
                        deal.pfbId = dealId;
                        deal.voucher = voucher_codes.generate({
                            pattern: "##-####-##-####-####-##",
                            charset: voucher_codes.charset("numbers")
                        })[0];
                        redisPersistence.saveObject(deal, callback)
                    }
                })

            }
            else{
                console.log("Save deal error",err);
            }

        }

    })();
}


removeUserDeal = function(dealId, userId, callback){
    flow.create("remove pfb deal",{
        begin:function(){
            for (var i = 0; i < dummyVendors.length; i++) {
                var deal = dummyVendors[i];
                console.log(deal);
                if (deal.serviceId === dealId) {
                    var dealData = {
                        userId: userId,
                        pfbId: deal.serviceId
                    }

                    redisPersistence.filter("UserPfB", dealData, this.continue("removeDeal"));
                    break;
                }
            }
        },
        removeDeal:function(err, deals){
            if(err){
                callback(err, null);
            }
            else{//console.log(deals);
                deals.forEach(function(deal){
                    redisPersistence.delete(deal);
                    callback(null,deal);
                })
            }
        }

    })();
}



function generateUUID() {
    var d = new Date().getTime();
    var uuid = 'xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = (d + Math.random()*16)%16 | 0;
        d = Math.floor(d/16);
        return (c=='x' ? r : (r&0x3|0x8)).toString(16);
    });
    return uuid;
}



container.declareDependency("PrivacyForBenefitsManager", ["redisPersistence"], function (outOfService, redisPersistence) {
    if (!outOfService) {
        console.log("Enabling persistence...", redisPersistence);
    } else {
        console.log("Disabling persistence...");
    }
})
