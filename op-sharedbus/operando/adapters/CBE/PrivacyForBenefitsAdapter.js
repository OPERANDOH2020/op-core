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
        identifier:".btn-connect-option.facebook.badge-facebook-connect",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dolor diam, pharetra vel velit in, finibus mollis purus. Sed luctus mattis porta. In a massa dignissim, imperdiet eros vitae, facilisis sem. Praesent posuere ex vehicula dolor pulvinar dictum. Nulla facilisi. Vestibulum faucibus nisi eleifend, scelerisque leo ac, finibus ex. Pellentesque eget ullamcorper nunc. Sed porttitor ex ligula, sed scelerisque nisl mollis at. Mauris lacus elit, dictum id ipsum vel, cursus malesuada nisl. Donec tincidunt sapien eget pulvinar sodales. Aenean laoreet libero vitae dolor aliquam, hendrerit euismod augue rhoncus"
    },
    {
        serviceId: 1,
        website: "kissfm.ro",
        benfit: "2 euros",
        identifier:".btn.btn-facebook.btn-block",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dolor diam, pharetra vel velit in, finibus mollis purus. Sed luctus mattis porta. In a massa dignissim, imperdiet eros vitae, facilisis sem. Praesent posuere ex vehicula dolor pulvinar dictum. Nulla facilisi. Vestibulum faucibus nisi eleifend, scelerisque leo ac, finibus ex. Pellentesque eget ullamcorper nunc. Sed porttitor ex ligula, sed scelerisque nisl mollis at. Mauris lacus elit, dictum id ipsum vel, cursus malesuada nisl. Donec tincidunt sapien eget pulvinar sodales. Aenean laoreet libero vitae dolor aliquam, hendrerit euismod augue rhoncus"
    },
    {
        serviceId: 2,
        website: "dribbble.com",
        benfit: "2 euros",
        identifier:".sign-connections .auth-twitter, .signup-twitter .auth-twitter",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dolor diam, pharetra vel velit in, finibus mollis purus. Sed luctus mattis porta. In a massa dignissim, imperdiet eros vitae, facilisis sem. Praesent posuere ex vehicula dolor pulvinar dictum. Nulla facilisi. Vestibulum faucibus nisi eleifend, scelerisque leo ac, finibus ex. Pellentesque eget ullamcorper nunc. Sed porttitor ex ligula, sed scelerisque nisl mollis at. Mauris lacus elit, dictum id ipsum vel, cursus malesuada nisl. Donec tincidunt sapien eget pulvinar sodales. Aenean laoreet libero vitae dolor aliquam, hendrerit euismod augue rhoncus"
    },
    {
        serviceId: 3,
        website: "ssl.bbc.com",
        benfit: "free content",
        identifier:".bbcid-facebook-com",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dolor diam, pharetra vel velit in, finibus mollis purus. Sed luctus mattis porta. In a massa dignissim, imperdiet eros vitae, facilisis sem. Praesent posuere ex vehicula dolor pulvinar dictum. Nulla facilisi. Vestibulum faucibus nisi eleifend, scelerisque leo ac, finibus ex. Pellentesque eget ullamcorper nunc. Sed porttitor ex ligula, sed scelerisque nisl mollis at. Mauris lacus elit, dictum id ipsum vel, cursus malesuada nisl. Donec tincidunt sapien eget pulvinar sodales. Aenean laoreet libero vitae dolor aliquam, hendrerit euismod augue rhoncus"
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


container.declareDependency("PrivacyForBenefitsManager", ["redisPersistence"], function (outOfService, redisPersistence) {
    if (!outOfService) {
        console.log("Enabling persistence...", redisPersistence);
    } else {
        console.log("Disabling persistence...");
    }
})
