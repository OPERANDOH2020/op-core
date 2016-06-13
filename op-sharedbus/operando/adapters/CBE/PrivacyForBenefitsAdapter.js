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
        benfit: "5 euros",
        identifier: ".btn-connect-option.facebook.badge-facebook-connect",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dolor diam, pharetra vel velit in, finibus mollis purus. Sed luctus mattis porta. In a massa dignissim, imperdiet eros vitae, facilisis sem. Praesent posuere ex vehicula dolor pulvinar dictum. Nulla facilisi. Vestibulum faucibus nisi eleifend, scelerisque leo ac, finibus ex. Pellentesque eget ullamcorper nunc. Sed porttitor ex ligula, sed scelerisque nisl mollis at. Mauris lacus elit, dictum id ipsum vel, cursus malesuada nisl. Donec tincidunt sapien eget pulvinar sodales. Aenean laoreet libero vitae dolor aliquam, hendrerit euismod augue rhoncus",
        logo:"https://upload.wikimedia.org/wikipedia/commons/thumb/9/97/9GAG_new_logo.svg/2000px-9GAG_new_logo.svg.png"
    },
    {
        serviceId: 1,
        website: "kissfm.ro",
        benfit: "2 euros",
        identifier: ".btn.btn-facebook.btn-block",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dolor diam, pharetra vel velit in, finibus mollis purus. Sed luctus mattis porta. In a massa dignissim, imperdiet eros vitae, facilisis sem. Praesent posuere ex vehicula dolor pulvinar dictum. Nulla facilisi. Vestibulum faucibus nisi eleifend, scelerisque leo ac, finibus ex. Pellentesque eget ullamcorper nunc. Sed porttitor ex ligula, sed scelerisque nisl mollis at. Mauris lacus elit, dictum id ipsum vel, cursus malesuada nisl. Donec tincidunt sapien eget pulvinar sodales. Aenean laoreet libero vitae dolor aliquam, hendrerit euismod augue rhoncus",
        logo:"https://upload.wikimedia.org/wikipedia/en/8/86/Logo_Kiss_FM.png"
    },
    {
        serviceId: 2,
        website: "dribbble.com",
        benfit: "2 euros",
        identifier: ".sign-connections .auth-twitter, .signup-twitter .auth-twitter",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dolor diam, pharetra vel velit in, finibus mollis purus. Sed luctus mattis porta. In a massa dignissim, imperdiet eros vitae, facilisis sem. Praesent posuere ex vehicula dolor pulvinar dictum. Nulla facilisi. Vestibulum faucibus nisi eleifend, scelerisque leo ac, finibus ex. Pellentesque eget ullamcorper nunc. Sed porttitor ex ligula, sed scelerisque nisl mollis at. Mauris lacus elit, dictum id ipsum vel, cursus malesuada nisl. Donec tincidunt sapien eget pulvinar sodales. Aenean laoreet libero vitae dolor aliquam, hendrerit euismod augue rhoncus",
        logo:"http://www.userlogos.org/files/logos/jumpordie/dribbble_01.png"
    },
    {
        serviceId: 3,
        website: "ssl.bbc.com",
        benfit: "free content",
        identifier: ".bbcid-facebook-com",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dolor diam, pharetra vel velit in, finibus mollis purus. Sed luctus mattis porta. In a massa dignissim, imperdiet eros vitae, facilisis sem. Praesent posuere ex vehicula dolor pulvinar dictum. Nulla facilisi. Vestibulum faucibus nisi eleifend, scelerisque leo ac, finibus ex. Pellentesque eget ullamcorper nunc. Sed porttitor ex ligula, sed scelerisque nisl mollis at. Mauris lacus elit, dictum id ipsum vel, cursus malesuada nisl. Donec tincidunt sapien eget pulvinar sodales. Aenean laoreet libero vitae dolor aliquam, hendrerit euismod augue rhoncus",
        logo:"https://upload.wikimedia.org/wikipedia/en/thumb/f/ff/BBC_News.svg/1280px-BBC_News.svg.png"
    }
]


var core = require("swarmcore");
core.createAdapter("PrivacyForBenefitsManager");
var apersistence = require('apersistence');
var container = require("safebox").container;
var flow = require("callflow");

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
            index: "true",
        },
        pfbId: {
            type: "string",
            index: "true"
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

getWebsitePfBDeal = function (website) {
    for (var i = 0; i < dummyVendors.length; i++) {
        if (dummyVendors[i].website == website) {
            return dummyVendors[i];
        }
    }
    return null;
}


getAllDeals = function(){
    return dummyVendors;
}

getUserDeals = function (dealId, userId, callback) {
    flow.create("store pfb deal", {
        begin: function () {
            if (!userId) {
                callback(new Error('Empty userId'), null);
            }
            else {
               this.continue("getPfBDeals");
            }
        },
        getPfBDeals: function (err, user) {
            for (var i = 0; i < dummyVendors.length; i++) {
                if (deal.serviceId == dealId) {
                    var deal = {
                        userId: userId,
                        pfbId: dealId
                    }

                    redisPersistence.filter("UserPfB", deal, this.continue("getDealsIndex"));
                    break;
                }
            }
        },

        returnDeals: function (err, deals) {
            console.log(deals);
            callback(err, deals);
        }
    })();
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
                            pfbId: dealId
                        }

                        redisPersistence.filter("UserPfB", deal, this.continue("getDealsIndex"));
                        break;
                    }
                }
            }
        },

        getDealsIndex: function (err, deals) {
            console.log(deals);
            if (deals.length == 0) {
                redisPersistence.filter("UserPfB", undefined, this.continue("saveDeal"));

            }
        },

        saveDeal: function (err, deals) {
            var index = 0;
            var self = this;
            console.log(err);
            if (deals != undefined) {
                index = deals.length;
            }

            redisPersistence.lookup("UserPfB", index + 1, function (err, deal) {

                console.log(deal);
                if (redisPersistence.isFresh(deal)) {
                    deal.userId = userId;
                    deal.pfbId = dealId;
                    console.log(deal);
                    redisPersistence.save(deal, self.continue("returnDeal"))
                }
            })
        },


        returnDeal: function (err, deal) {
            console.log(deal);
        }

    })();
}


container.declareDependency("PrivacyForBenefitsManager", ["redisPersistence"], function (outOfService, redisPersistence) {
    if (!outOfService) {
        console.log("Enabling persistence...", redisPersistence);
    } else {
        console.log("Disabling persistence...");
    }
})
