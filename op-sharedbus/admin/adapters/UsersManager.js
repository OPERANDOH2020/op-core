/**
 * Created by salboaie on 3/24/15.
 */

/*
 Default UsersManager adapter. Punct de integrare cu alte sisteme, gen casa de sanatate.
 */
var core = require("swarmcore");
/*
 usersmanager este un adaptor swarm care gestioneaza organizatiile si utilizatorii

 */

core.createAdapter("UsersManager");
var mysql = require('mysql')
var mysqlConnection = mysql.createConnection({
    host     : thisAdapter.config.Core.mysqlHost,
    port     : thisAdapter.config.Core.mysqlPort,
    user     : 'root',
    password : thisAdapter.config.Core.mysqlDatabasePassword,
    database : thisAdapter.config.Core.mysqlDatabaseName
});

var persistence = require('apersistence').createMySqlPersistence(mysqlConnection);


var container = require("safebox").container;


var flow = require("callflow");


var saveCallbackFn = function (err, obj) {
    if (err) {
        console.log(err);
    }
};

/*
 Model de date pentru organizatie
 */

var organisationModel = {
    organisationId: {
        type: "string",
        pk: true,
        index: true
    },
    displayName: {
        type: "string"
    },
    agent: {
        /* numele de grup al agentului */
        type: "string"
    }
};

/*
 Default User model
 */

var userModel = {
    userId: {
        type: "string",
        pk: true,
        index: "true"
    },
    userName: {
        type: "string"
    },
    organisationId: {
        type: "string",
        index: "true"
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
    },
    is_active: {
        type: "boolean"
    }
};
/*
 Creeaza un utilizator
*/

createUser = function (userData, callback) {
    persistence.lookup("DefaultUser", userData.userId, function (err, user) {
        if (err) {
            callback(err);
        } else {
            if (user.__meta.freshRawObject !== true) {
                callback(new Error("User with identical id " + userData.userId + " already exists"), null);
            } else {
                persistence.externalUpdate(user, userData);
                persistence.saveObject(user, function(err,result){
                    if(user.password && !err){
                        delete user['password'];
                    }
                    callback(err,result);
                });
            }
        }
    })
};

/*
Filtreaza utilizatorii
 */

filterUsers = function(filters,callback){
    persistence.filter("DefaultUser",filters,callback);
};
/*
 Sterge un utilizator
 */

deleteUser = function (userId,callback) {
    persistence.deleteById("DefaultUser",userId,callback);
};


/*
 Sterge o organizatie
 */


deleteOrganisation = function (organisationId,callback) {
    persistence.deleteById("Organisation",organisationId,callback);

}

/*
 Updateaza informatiile unui utilizator
 */

updateUser = function (userJsonObj, callback) {
    persistence.lookup("DefaultUser",userJsonObj.userId,function(err,user){
        if (!err && persistence.isFresh(user)) {
            err = new Error("User with id " + userJsonObj.userId + " does not exist")
        }
        if(err){
            callback(err);
        }else{
            persistence.externalUpdate(user, userJsonObj);
            persistence.saveObject(user,callback);
        }
    })
}

/*
 queryUsers returneaza lista utilizatorilor apartinind de o organizatie
 */

queryUsers = function (organisationId, callback) {
    flow.create("get organisation users", {
        begin: function () {
            persistence.filter("DefaultUser", {"organisationId": organisationId}, this.continue("getOrganisationUsers"));
        },
        getOrganisationUsers: function (err, users) {
            var organizationUsers = [];

            users.forEach(function (user) {
                if (user.is_active != false) {
                    delete user['password'];
                    organizationUsers.push(user);
                }
            });

            callback(err, organizationUsers);
        }
    })();
}

/*
 Creeaza o organizatie
 */

createOrganisation = function (organisationDump, callback) {
    persistence.lookup("Organisation", organisationDump.organisationId, function (err, organisation) {
        if (err) {
            callback(err);
        } else {
            if (organisation.__meta.freshRawObject !== true) {
                callback(new Error("Organisation with id " + organisationDump.organisationId + " already exists"), null);
            } else {
                persistence.externalUpdate(organisation, organisationDump);
                persistence.saveObject(organisation, callback);
            }
        }
    })
}

/*
 Realizeaza salvarea datelor despre o organizatie
 */

updateOrganisation = function (organisationDump, callback) {
    flow.create("update organization", {
        begin: function () {
            persistence.lookup("Organisation", organisationDump.organisationId, this.continue("updateOrganisation"));
        },

        updateOrganisation: function (err, organisation) {
            if (err) {
                callback(err, null);
            }

            else if (persistence.isFresh(organisation)) {
                callback(new Error("Organisation with id " + organisationDump.organisationId + " was not found"), null);
            }
            else {
                persistence.externalUpdate(organisation, organisationDump);
                persistence.saveObject(organisation, this.continue("updateReport"));
            }
        },
        updateReport: function (err, organisation) {
            callback(err, organisation);
        }
    })();
};


newUserIsValid = function (newUser, callback) {
    flow.create("user is valid", {
        begin: function () {
            persistence.lookup("DefaultUser", newUser.username, this.continue("verifyEmail"))
        },
        verifyEmail: function (err, user) {
            if (err) {
                callback(err);
            } else if (!persistence.isFresh(user)) {
                callback(new Error("Username is unavailable"));
            }
            else {
                persistence.lookup("DefaultUser", newUser.email, this.continue("verifyPasswords"))
            }
        },
        verifyPasswords: function (err, user) {
            if (err) {
                callback(err);
            }
            else if (!persistence.isFresh(user)) {
                callback(new Error("Email is unavailable"));
            }
            else {
                if (newUser.password != newUser.repeat_password) {
                    callback(new Error("Passwords doest not match"));
                }
                else {
                    createUser({
                        userId: newUser.email,
                        password: newUser.password,
                        userName: newUser.username,
                        email: newUser.email,
                        organisationId: "Public"
                    }, function (err, user) {
                        if (user) {
                            if (user['password']) {
                                delete user['password'];
                            }
                        }

                        callback(err, user);
                    });
                }

            }
        }

    })();
}


/*
 Returneaza lista de organizatii
 */

getOrganisations = function (callback) {
    flow.create("get all organizations", {
        begin: function () {
            persistence.filter("Organisation", this.continue("info"));
        },
        info: function (err, result) {
            callback(err, result);
        }
    })();
};


/*
 Returneaza informatii despre un utilizator
 */

getUserInfo = function (userId, callback) {
    flow.create("retrieve user info", {
        begin: function () {
            redisPersistence.findById("DefaultUser", userId, this.continue("info"));
        },
        info: function (err, user) {
            if (err) {
                callback(err, null);
            } else if (user) {
                if (user.password) {
                    delete user['password'];
                }
                callback(null, user);
            }
            else {
                callback(null, null);
            }
        }

    })();
};


validPassword = function (userId, pass, callback) {
    flow.create("Validate Password", {
        begin: function () {
            persistence.findById("DefaultUser", userId, this.continue("validatePassword"));
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
};

bootSystem();

function bootSystem() {
    persistence.registerModel("Organisation", organisationModel, function (err, model) {
        if (err) {
            console.log(err,err.stack);
        }else{
            persistence.registerModel("DefaultUser", userModel, function (err, model) {
                if (err) {
                    console.log(err,err.stack);
                }
                else{
                    createOrganisation({"organisationId":"SystemAdministrators","displayName":"System Administrators"},function(err,result){
                        if(!err || err.message.match("already exists")){
                            createAdministrators();
                        }else{
                            console.log("Failed to create organisation SystemAdministrators",err)
                        }
                    });

                    createOrganisation({"organisationId":"Public","displayName":"OPERANDO PUBLIC"},function(err,result) {
                        if(!err || err.message.match("already exists")){
                            createGuests();
                        }else{
                            console.log("Failed to create organisation Public",err)
                        }
                    });

                    createOrganisation({"organisationId":"Analysts","displayName":"OPERANDO ANALYSTS"},function(err,result){
                        if(!err || err.message.match("already exists")){
                            createAnalists();
                        }else{
                            console.log("Failed to create organisation Analysts",err)
                        }
                    })
                }

            });
        }
    });

    function createAdministrators(){
        console.log("Creating administrators");
        createUser({
            userId: "zeev",
            password: "operando",
            email:"zeev@arteevo.com",
            userName: "Zeev Pritzker",
            organisationId: "SystemAdministrators"
        }, saveCallbackFn);
        createUser({
            userId: "admin",
            password: "swarm",
            email:"admin@operando.eu",
            userName: "Admin",
            organisationId: "SystemAdministrators"
        }, saveCallbackFn);
        createUser({
            userId: "rafa",
            password: "swarm",
            email:"raf@rms.ro",
            userName: "Rafael Mastaleru",
            organisationId: "SystemAdministrators"
        }, saveCallbackFn);
    }

    function createGuests(){
        createUser({
            userId: "guest",
            password: "guest",
            userName: "Guest User",
            organisationId: "Public"
        }, saveCallbackFn);
    }
    function createAnalists(){
        createUser({
            userId: "analyst",
            password: hashThisPassword("analyst"),
            email:"analyst@rms.ro",
            userName: "Analyst Guru",
            organisationId:"Analysts"
        }, saveCallbackFn);
    }
}

function hashThisPassword(password){
    //TODO choose encryption method
    return password;
}



/*

container.declareDependency("UsersManagerAdapter", ["persistence"], function (outOfService, persistence) {
    if (!outOfService) {
        console.log("Enabling persistence...", persistence);
        bootSystem();
    } else {
        console.log("Disabling persistence...");
    }
})*/