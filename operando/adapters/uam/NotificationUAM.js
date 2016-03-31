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


var core = require ("swarmcore");
core.createAdapter("NotificationUAM");
var apersistence = require('apersistence');
var  container = require("safebox").container;
var flow = require("callflow");


apersistence.registerModel("Notification","Redis",{
    notificationId:{
        type:"string",
        pk:true,
        index:true
    },
    forUser:{
        type: "string",
        index: true
    },

    notification_type:{
        type: "string",
        index: true
    },
    notification_message:{
        type: "string",
    },
    notification_delivered:{
        type: "boolean",
    },
    notification_date:{
        type: "date",
    }
})