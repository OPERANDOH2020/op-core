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

var core = require("swarmcore");
core.createAdapter("OSPAdapter");
var apersistence = require('apersistence');
var container = require("safebox").container;
var flow = require("callflow");
var uuid = require('uuid');

apersistence.registerModel("OspDetails", "Redis", {
        userId: {
            type: "string",
            index: true,
            pk: true
        },
        name: {
            type: "string"
        },
        phone: {
            type: "string"
        },
        website: {
            type: "string",
            index: true
        },
        deals_description: {
            type: "string"
        },
        osp_accepted_time:{
            type:"string",
            default:"0"
        }
    },
    function (err, model) {
        if (err) {
            console.log(err);
        } else {
        }
    }
);


apersistence.registerModel("OspOffer", "Redis", {
        userId: {
            type: "string",
            index: true
        },
        offerId:{
            type: "string",
            index: true,
            pk:true
        },
        name: {
            type: "string"
        },
        logo:{
            type: "string"
        },
        description: {
            type: "string"
        },
        start_date:{
            type:"string"
        },
        end_date:{
            type:"string"
        },
        impact_score:{
            type:"string",
            default:"0"
        }
    },
    function (err, model) {
        if (err) {
            console.log(err);
        } else {
        }
    }
);


container.declareDependency("OSPAdapter", ["redisPersistence"], function (outOfService, redisPersistence) {
    if (!outOfService) {
        console.log("Enabling persistence...", redisPersistence);
    } else {
        console.log("Disabling persistence...");
    }
});

registerNewOSP = function (userId, ospDetailsData, callback) {
    flow.create("register new OSP", {
        begin: function () {
            redisPersistence.lookup("OspDetails", userId, this.continue("createOSP"));
        },
        createOSP: function (err, ospDetails) {
            console.log(ospDetails);
            if (err) {
                callback(err, null);
            }
            else if (!redisPersistence.isFresh(ospDetails)) {
                callback(new Error("OspAlreadyRegistered"), null);
            }
            else {
                ospDetails['osp_accepted_time'] = Date.now();
                redisPersistence.externalUpdate(ospDetails, ospDetailsData);
                redisPersistence.saveObject(ospDetails, callback);
            }
        }
    })();
};

getOSPs = function(callback){
    flow.create("getOSPs",{
        begin:function(){
            redisPersistence.filter("OspDetails",{},callback);
        }
    })();
};


addOrUpdateOspOffer = function (ospUserId, offerDetails, callback){
    flow.create("addOspOffer", {
        begin: function () {
            redisPersistence.lookup("OspDetails", ospUserId, this.continue("checkOspOffer"));
        },
        checkOspOffer: function (err, osp) {
            if (err) {
                callback(err);
            }
            else if (redisPersistence.isFresh(osp)) {
                callback(new Error("ospUserDoestNotExists"));
            }
            else {
                this.next("createOspOfferId");
            }
        },

        createOspOfferId: function () {
            var ospOfferId;
            if(offerDetails.offerId){
                ospOfferId= offerDetails.offerId;
            }
            else{
                ospOfferId = uuid.v1().split("-").join("");
            }
            redisPersistence.lookup("OspOffer", ospOfferId, this.continue("createOspOffer"));
        },
        createOspOffer: function (err, ospOffer) {
            if (err) {
                callback(new Error("Could not retrieve ospOffer by id"));
            } else
            {
                var offerEndDate = new Date(offerDetails['end_date']);
                offerEndDate.setHours(23,59,59,999);
                offerDetails['end_date'] = offerEndDate.toISOString();
                console.log(offerDetails);
                redisPersistence.externalUpdate(ospOffer, offerDetails);
                ospOffer['userId'] = ospUserId;
                redisPersistence.saveObject(ospOffer, callback);
            }
        }
    })();
};

deleteOspOffer = function (offerId, callback) {

    flow.create("delete osp offer", {
        begin: function () {
            if (!offerId) {
                callback(new Error("offerIdRequired"));
            }
            else {
                redisPersistence.lookup("OspOffer", offerId, this.continue("deleteOffer"));
            }
        },
        deleteOffer: function (err, offer) {
            if (err) {
                callback(err);
            }
            else if (redisPersistence.isFresh(offer)) {
                callback(new Error("ospOfferDoesNotExists"));
            }
            else {
                redisPersistence.deleteById("OspOffer", offerId, callback);
            }
        }
    })();
};

getOspOffers = function(ospUserId, callback){
    flow.create("getOSPOffers",{
        begin:function(){
            redisPersistence.filter("OspOffer",{userId:ospUserId},callback);
        }
    })();
};


getOSPOffer = function(offerId,callback){

    flow.create("getOSPOffer",{
        begin:function(){
            redisPersistence.lookup("OspOffer",offerId,this.continue("checkOffer"));
        },
        checkOffer:function(err, offer){
            if(redisPersistence.isFresh(offer)){
                callback(new Error("Offer not found"));
            }
            else{
                redisPersistence.lookup("OspDetails",offer.userId, function(err, ospDetails){
                    if(err){
                        callback(err);
                    }
                    else if(redisPersistence.isFresh(ospDetails)){
                        callback(new Error(("OSPDetails with this userId not found")));
                    }
                    else{
                        offer['website'] = ospDetails['website'];
                        callback(null, offer);
                    }
                });
            }
        }
    })();
};

getAllOffers = function(callback){
    var availableOffers = [];
    flow.create("getAllOffers",{
        begin:function(){
            redisPersistence.filter("OspOffer",{},this.continue("checkDate"));
        },
        checkDate:function(err, offers){
            if(err){
                callback(err);
            }
            else{
                var currentDate = new Date();
                availableOffers = offers.filter(function(offer){
                    return (currentDate >= new Date(offer['start_date']) && currentDate <= new Date(offer['end_date']));
                });

                this.next("getOSPDetails");

            }
        },
        getOSPDetails:function(){

            availableOffers.forEach(function(offer){
                    redisPersistence.lookup("OspDetails",offer.userId, function(err, ospDetails){
                       if(!redisPersistence.isFresh(ospDetails)){
                           offer['website'] = ospDetails['website'];
                       }
                    });
            });

            callback(null,availableOffers);

        }
    })();
};