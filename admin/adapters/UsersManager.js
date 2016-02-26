/**
 * Created by salboaie on 3/24/15.
 */

/*
    Default UsersManager adapter. Punct de integrare cu alte sisteme, gen casa de sanatate.
*/
var core = require ("swarmcore");
/*
    usersmanager este un adaptor swarm care gestioneaza organizatiile si utilizatorii

 */

core.createAdapter("UsersManager");


var apersistence = require('apersistence');


var  container = require("safebox").container;


var flow = require("asynchron");


var saveCallbackFn =function(err, obj){
    if(err){
        console.log(err);
    }
}

/*
    Model de date pentru organizatie

 */

apersistence.registerModel("Organisation","Redis", {
    ctor: function () {
    },
    organisationId: {
        type: "string",
        pk: true,
        index:true
    },
    displayName: {
        type: "string"
    },
    agent:{             /* numele de grup al agentului */
        type:"string"
    }
}, function(err, model){
    if(err){
        console.log(err);
    }

});

/*
 Default User model
 */

apersistence.registerModel("DefaultUser","Redis", {
    userId: {
        type: "string",
        pk: true,
        index:true
    },
    userName: {
        type: "string"
    },
    organisationId:{
        type:"string",
        index :"true"
    },
    password: {
        type: "string"
    },
    birthYear: {
        type: "string"
    },
    birthMonth: {
        type: "string"
    },
    birthDay: {
        type: "string"
    },
    sex: {
        type: "string"
    },
    phone: {
        type: "string"
    },
    email: {
        type: "string"
    },
    address: {
        type: "string"
    },
    city: {
        type: "string"
    },
    zip_code: {
        type: "string"
    }
}, function(err, model){
    if (err) {
        console.log(err);
    }
});




/*
     Creeaza un utilizator
 */

/*createUser = function(userData, callback){
    if(!userData.userId){
        callback(new Error('Empty userId'));
        return;
    }
    var user = redisPersistence.lookup.async("DefaultUser", userData.userId);
    (function(user){
        if(!redisPersistence.isFresh(user)){
            callback(new Error("User with identical id " + userData.userId + " already exists"), null);
            return ;
        }

        redisPersistence.externalUpdate(user, userData);
        redisPersistence.save(user, saveCallbackFn);
        if(callback)  {
            callback(null, user);
        }
    }).wait(user);
}*/


createUser = function(userData, callback){

    flow.createFlow("create user", {
        begin:function(){
            if(!userData.userId){
                callback(new Error('Empty userId'), null);
            }
            else{
                redisPersistence.lookup("DefaultUser", userData.userId, this.continue("createUser"));
            }
        },
        createUser:function(err, user){
            if(!redisPersistence.isFresh(user)){
                callback(new Error("User with identical id " + userData.userId + " already exists"), null);
            }else{
                redisPersistence.externalUpdate(user, userData);
                redisPersistence.save(user, this.continue("createReport"));
            }
        },
        createReport:function(err, user){
            callback(err, user);
        }
    })();
}

/*
    Sterge un utilizator
 */

/*deleteUser = function(userData){
    redisPersistence.deleteById("DefaultUser", userData.userId);
}*/


deleteUser = function (userData) {
    flow.createFlow("delete user", {
        begin: function () {
            redisPersistence.deleteById("DefaultUser", userData.userId, this.continue("deleteReport"));
        },
        deleteReport: function (err, obj) {
            callback(err, obj);
        }
    })();
}



/*
    Sterge o organizatie
 */


/*deleteOrganisation = function(organisationId){
    redisPersistence.deleteById("Organisation", organisationId);
}*/


deleteOrganisation = function(organisationId){
    flow.createFlow("delete organisation",{
        begin:function(){
            redisPersistence.deleteById("Organisation", organisationId, this.continue("deleteReport"));
        },
        deleteReport: function (err, obj) {
            callback(err, obj);
        }
    })();
}

/*
    Updateaza informatiile unui utilizator
*/

/*updateUser = function(userJsonObj, callback){
    var user = redisPersistence.lookup.async("DefaultUser", userJsonObj.userId);
    (function(user){
        redisPersistence.externalUpdate(user, userJsonObj);
        redisPersistence.saveObject(user,saveCallbackFn);
        callback(null, user);
    }).swait(user);
}*/


updateUser = function(userJsonObj, callback){
    flow.createFlow("update user",{
        begin:function(){
            redisPersistence.lookup.async("DefaultUser", userJsonObj.userId, this.continue("updateUser"));
        },
        updateUser:function(err, user){
            if(err){
                callback(err, null);
            }
            else {
                if (redisPersistence.isFresh(user)) {
                    callback(new Error("User with id "+ userJsonObj.userId +" does not exist"), null);
                }
                else{
                    redisPersistence.externalUpdate(user, userJsonObj);
                    redisPersistence.saveObject(user,this.continue("updateReport"));
                }
            }
        },
        updateReport: function(err, user){
            callback(err, user);
        }
    })();
}

/*
    queryUsers returneaza lista utilizatorilor apartinind de o organizatie
 */

/*queryUsers = function(organisationId, callback){
    var list  = redisPersistence.filter.async("DefaultUser", {"organisationId": organisationId});

    (function(list){
        callback(null,list);
    }).wait(list);
}*/

queryUsers = function(organisationId, callback){
    flow.createFlow("get organisation users",{
        begin: function(){
            redisPersistence.filter("DefaultUser", {"organisationId": organisationId}, this.continue("getOrganisationUsers"));
        },
        getOrganisationUsers: function(err, users){
            callback(err,users);
        }
    })();
}

/*
    Creeaza o organizatie
 */

/*createOrganisation = function(organisationDump, callback){
    var organisation = redisPersistence.lookup.async("Organisation", organisationDump.organisationId);
    (function(organisation){
        if(!redisPersistence.isFresh(organisation)){
            callback(new Error("Organisation with identical id already exists"), null);
            return ;
        }
        redisPersistence.externalUpdate(organisation, organisationDump);
        redisPersistence.saveObject(organisation, saveCallbackFn);
        callback(null, organisation);
    }).swait(organisation);
}*/


createOrganisation = function (organisationDump, callback) {
    flow.createFlow("create organisation", {
        begin: function () {
            redisPersistence.lookup("Organisation", organisationDump.organisationId, this.continue("createOrganisation"));
        },
        createOrganisation: function (err, organisation) {
            if (err) {
                callback(err, null);
            }
            else {
                if (!redisPersistence.isFresh(organisation)) {
                    callback(new Error("Organisation with id " + organisationDump.organisationId + " already exists"), null);
                }
                else {
                    redisPersistence.externalUpdate(organisation, organisationDump);
                    redisPersistence.saveObject(organisation, this.continue("createReport"));
                }
            }

        },
        createReport: function (err, organisation) {
            callback(err, organisation);
        }
    })();
}

/*
    Realizeaza salvarea datelor despre o organizatie
*/
/*updateOrganisation = function(organisationDump, callback){
    var organisation = redisPersistence.lookup.async("Organisation", organisationDump.organisationId);
    (function(organisation){
        redisPersistence.externalUpdate(organisation, organisationDump);
        redisPersistence.saveObject(organisation, saveCallbackFn);
        callback(null, organisation);
    }).swait(organisation);
}*/

updateOrganisation = function (organisationDump, callback) {
    flow.createFlow("update organization", {
        begin: function () {
            redisPersistence.lookup("Organisation", organisationDump.organisationId, this.continue("updateOrganisation"));
        },

        updateOrganisation: function (err, organisation) {
            if (err) {
                callback(err, null);
            }

            else if (redisPersistence.isFresh(organisation)) {
                callback(new Error("Organisation with id " + organisationDump.organisationId + " was not found"), null);
            }
            else {
                redisPersistence.externalUpdate(organisation, organisationDump);
                redisPersistence.saveObject(organisation, this.continue("updateReport"));
            }
        },
        updateReport: function (err, organisation) {
            callback(err, organisation);
        }
    })();
};





/*
    Returneaza lista de organizatii
*/
/*getOrganisations = function(callback){
    var list  = redisPersistence.filter.async("Organisation", null);

    (function(list){
        callback(null,list);
    }).wait(list);
}*/


getOrganisations = function(callback){
    flow.createFlow("get all organizations",{
        begin:function(){
            redisPersistence.filter("Organisation", this.continue("info"));
        },
        info:function(err, result){
                callback(err, result);
            }
    })();
}


/*
    Returneaza informatii despre un utilizator
 */
/*getUserInfo = function(userId, callback){
    var user = redisPersistence.findById.nasync("DefaultUser", userId);
    (function(user){
            if(user){
                user.password = null;
            }
            callback(null, user);
    }).wait(user);
}*/


getUserInfo = function(userId, callback){
    flow.createFlow("retrieve user info",{
        begin:function(){
            redisPersistence.findById("DefaultUser", userId, this.continue("info"));
        },
        info: function(err, user){
            if(err){
                callback(err, null);
            }else
            if(user){
                if(user.password){
                    delete user['password'];
                }
                callback(null, user);
            }
            else{
                callback(null, null);
            }
        }

    })();
}


/*validPassword = function(userId, pass, callback){
    var user = redisPersistence.findById.async("DefaultUser", userId);

    (function(user){
        if(user && user.password  == pass){
            callback(null, true);
        } else {
            callback(null, false);
        }
    }).wait(user);
}*/



validPassword = function(userId, pass, callback){

    flow.createFlow("Validate Password", {
        begin: function () {
            redisPersistence.findById("DefaultUser", userId, this.continue("validatePassword"));
        },
        validatePassword: function (err, user) {
            if (err) {
                callback(err, null);
            }
            else if (user && user.password == pass) {
                callback(null, true);
            } else {
                callback(null, false);
            }
        }
    })();

}




/*
    initialisation
 */
/*function bootSystem(){
    var organisation = redisPersistence.lookup.async("Organisation", "SystemAdministrators");

    (function(organisation){
        if(redisPersistence.isFresh(organisation)){
            organisation.displayName = "System Administrators";
            redisPersistence.saveObject(organisation, function(err, obj){
                if(err){
                    console.log("Error occurred on creating organisation",err);
                }
                else{
                    createUser({userId:"admin", "password":"swarm", userName:"Admin",organisationId:"SystemAdministrators"},saveCallbackFn);
                    createUser({userId:"rafael", "password":"swarm",userName:"Rafael",organisationId:"SystemAdministrators"},saveCallbackFn);
                    createUser({userId:"rafa", "password":"swarm",userName:"Rafael Mastaleru",organisationId:"SystemAdministrators"},saveCallbackFn);
                }
            });
        }
    }).wait(organisation);
}*/


function bootSystem(){
    flow.createFlow("bootSystem",{
        begin:function(){
            redisPersistence.lookup("Organisation", "SystemAdministrators", this.continue("createOrganisation"));
        },
        createOrganisation: function(err, organisation){
            if(redisPersistence.isFresh(organisation)){
                organisation.displayName = "System Administrators";
                redisPersistence.saveObject(organisation, this.continue("createAdministrators"));
            }
        },
        createAdministrators: function(err, organisation){
            if(err){
                console.log("Error occurred on creating organisation",err);
            }
            else{
                createUser({userId:"admin", "password":"swarm", userName:"Admin",organisationId:organisation.organisationId},saveCallbackFn);
                createUser({userId:"rafael", "password":"swarm",userName:"Rafael",organisationId:organisation.organisationId},saveCallbackFn);
                createUser({userId:"rafa", "password":"swarm",userName:"Rafael Mastaleru",organisationId:organisation.organisationId},saveCallbackFn);
            }
        }

    })();
}


container.declareDependency("UsersManagerAdapter", ["redisPersistence"], function(outOfService, redisPersistence){
    if(!outOfService){
        console.log("Enabling persistence...", redisPersistence);
        bootSystem();
    } else {
        console.log("Disabling persistence...");
    }
})

