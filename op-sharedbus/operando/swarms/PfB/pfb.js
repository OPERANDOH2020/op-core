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
    meta:{
        name:"pfb.js"
    },

    vars:{
        deal: null,
        website: null,
        tabId:null
    },

    viewDeal:function(){
        //TODO
        //Implement viewDeal ctor
    },

    getAcceptedDeals:function(){
        //TODO
        //Implement getAcceptedDeals ctor
    },

    acceptDeal:function(){
        //TODO
        //Implement acceptDeal ctor
    },

    refuseDeal:function(){
        //TODO
        //Implement refuseDeal ctor
    },

    getWebsiteDeal:function(_website, _tabId){

        if (_website.indexOf("://") > -1) {
            this.website = _website.split('/')[2];
        }
        else {
            this.website = _website.split('/')[0];
        }

        if(this.website.indexOf("www.")>-1){
            this.website = this.website.split('www.')[1];
        }

        //find & remove port number
        this.website = this.website.split(':')[0];
        this.tabId = _tabId;
        console.log(this.website);
        this.swarm("websiteHasDeal");
    },
    websiteHasDeal:{
        node:"PrivacyForBenefitsManager",
        code:function(){
            if(websiteHasPfBDeal(this.website)){
                this.swarm("getWebsitePfBDeal")
            }
            else{
                this.home("no_pfb");
            }
        }
    },
    getWebsitePfBDeal:{
        node:"PrivacyForBenefitsManager",
        code:function(){
            this.deal = getWebsitePfBDeal(this.website);
            if (this.deal != null) {
                this.home("success");
            } else {
                this.home("no_pfb");
            }
        }
    }
}
privacyForBenefits;