
var core = require("swarmcore");
core.createAdapter("UsersManager");

const crypto = require('crypto');
var container = require("safebox").container;
var flow = require("callflow");
var uuid = require('uuid');
var passwordMinLength = 4;
var persistence = undefined;
var saltLength = 48;

container.declareDependency("UsersManagerAdapter",["mysqlPersistence"],function(outOfService,mysqlPersistence){
    if(outOfService){
        console.log("UsersManager failed");
    }else{
        console.log("Initialising UsersManager");
        persistence = mysqlPersistence;
        rebootUsersManager();
    }
});


function rebootUsersManager(callback){
    registerModels(callback?callback:onRebootFinished);

    function registerModels(callback){
        var userModel = {
            userId: {
                type: "string",
                pk: true,
                index: true,
                length:254
            },
            organisationId: {
                type: "string",
                index: true
            },
            password: {
                type: "string",
                length:1024
            },

            email: {
                type: "string",
                index:true,
                length:254
            },
            is_active: {
                type: "boolean",
                default:true
            },
            zones:{
                type:"string"
            },
            salt:{
                type:"string",
                length:saltLength*2

            },
            activationCode:{
                type: "string",
                index:true,
                length:254,
                default:"0"
            }
        };

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
                type: "string"
            }
        };

        var errs = [];
        var left = 2;

        persistence.registerModel("DefaultUser",userModel,onRegisterDone);
        persistence.registerModel("Organisation",organisationModel,onRegisterDone);

        function onRegisterDone(err,result){
            if(err) {
                errs.push(err);
            }
            left--;
            if(left==0){
                if(errs.length>0){
                    callback(errs);
                }else{
                    callback(null);
                }
            }
        }
    }
    function onRebootFinished(){
        startSwarm("initOperando.js","init");
        console.log("UsersManager available");
    }
}

createUser = function (userData, callback) {
    persistence.filter("DefaultUser",{"email":userData.email},function(err,result){
        if(err){
            callback(err)
        }else if(result.length>0){
            callback(new Error("User with email "+userData.email+" already exists"));
        }else{

            if(!userData.userId){
                userData.userId = uuid.v1().split("-").join("");
            }
            persistence.lookup("DefaultUser", userData.userId, function(err,user){
                if(err){
                    callback(new Error("Could not retrieve user by id"))
                }else if(!persistence.isFresh(user)){
                    callback(new Error("User with id "+userData.userId+" already exists"));
                }else{
                    if(!userData.zones){
                        userData.zones = "user";
                    }else {
                        userData.zones += ",user";
                    }
                    userData.salt = crypto.randomBytes(saltLength).toString('base64');
                    user.salt = userData.salt;
                    hashThisPassword(userData.password,userData.salt,function(err,hashedPassword){
                        userData.password = hashedPassword;
                        persistence.externalUpdate(user,userData);
                        persistence.save(user,function(err,newUser){
                            if(err){
                                console.log(err);
                                callback(new Error("Could not create user"))
                            }else{
                                delete user['password'];
                                callback(undefined,user);
                            }
                        })
                    });
                }
            });
        }
    });
};

filterUsers = function(conditions,callback){
    persistence.filter("DefaultUser",conditions,function(err,result){
        /*
         if(result.length>0){
         result = result.map(function(user){
         delete user.password;
         delete user.salt;
         delete user.__meta.savedValues.password;
         delete user.__meta.savedValues.salt;
         return user;
         })
         }*/
        callback(err,result)
    });
};

deleteUser = function (userData,callback) {
    flow.create("delete user", {
        begin: function () {
            persistence.deleteById("DefaultUser", userData.userId, this.continue("deleteReport"));
        },
        deleteReport: function (err, obj) {
            callback(err, obj);
        }
    })();
};

updateUser = function (userJsonObj, callback) {
    flow.create("update user", {
        begin: function () {
            persistence.lookup("DefaultUser", userJsonObj.userId, this.continue("updateUser"));
        },
        updateUser: function (err, user) {
            if (err) {
                callback(err, null);
            }
            else {
                if (persistence.isFresh(user)) {
                    callback(new Error("User with id " + userJsonObj.userId + " does not exist"), null);
                }
                else {
                    persistence.externalUpdate(user, userJsonObj);
                    persistence.saveObject(user, this.continue("updateReport"));
                }
            }
        },
        updateReport: function (err, user) {
            callback(err, user);
        }
    })();
};

activateUser = function(activationCode,callback){
    filterUsers({"activationCode":activationCode},function(err,users){
        if(err){
            callback(err);
        }else if(users.length===0){
            callback(new Error("No user with activation code "+activationCode));
        }else{
            users[0].activationCode = "0";
            persistence.saveObject(users[0],callback)
        }
    })
};

newUserIsValid = function (newUser, callback) {
    //TO DO: Change name of the function. Something like : "createPublicUser"

    flow.create("user is valid", {
        begin: function () {
            if(!newUser.email){
                callback(new Error("emailIsInvalid"));
            }
            else if(!newUser.password || newUser.password.length < passwordMinLength){
                callback(new Error("Password must contain at least "+passwordMinLength+" characters"));
            }
            else{
                persistence.filter("DefaultUser", {email:newUser.email}, this.continue("verifyPasswords"))
            }
        },
        verifyPasswords: function (err, users) {
            if (err) {
                callback(err);
            }
            else if (users.length > 0) {
                callback(new Error("emailIsUnavailable"));
            }
            else {
                if (newUser.password != newUser.repeat_password) {
                    callback(new Error("passwordsNotMatch"));
                }
                else {

                    var activationCode = new Buffer(uuid.v1()).toString('base64');
                    if(thisAdapter.config.development && thisAdapter.config.development === true ){
                        activationCode = "0";
                    }

                    createUser({
                        password: newUser.password,
                        email: newUser.email,
                        organisationId: "Public",
                        activationCode:activationCode
                    }, function (err, user) {
                        delete user['password'];
                        delete user['salt'];
                        delete user['__meta'];
                        callback(err, user);
                    });
                }
            }
        }
    })();
};

getUserInfo = function (userId, callback) {
    flow.create("retrieve user info", {
        begin: function () {
            persistence.findById("DefaultUser", userId, this.continue("info"));
        },
        info: function (err, user) {
            if (err) {
                callback(err, null);
            } else if (user) {
                delete user['__meta'];
                delete user['password'];
                delete user['salt'];
                callback(null, user);
            }
            else {
                callback(null, null);
            }
        }
    })();
};

validateUser = function (email, pass, organisationPretender, callback) {
    flow.create("Validate Password", {
        begin: function () {
            persistence.filter("DefaultUser", {email: email}, this.continue("validatePassword"));
        },
        validatePassword: function (err, users) {
            if(err){
                callback(err);
            }else if(users.length === 0 || !pass){
                callback( new Error("invalidCredentials"));
            }
            else {
                var user = users[0];

                if (user.organisationId !== organisationPretender) {
                    callback(new Error("accessDenied"));
                }
                else {
                    hashThisPassword(pass, user.salt, function (err, hashedPassword) {
                        if (err)
                            callback(err);
                        else if (hashedPassword !== user.password)
                            callback(new Error("invalidCredentials"));
                        else if (user.activationCode !== "0")
                            callback(new Error("accountNotActivated"));
                        else
                            callback(null, user.userId);
                    });
                }

            }
        }
    })();
};

getUserId = function(email, callback){
    persistence.filter("DefaultUser",{"email":email},function(err,result){
        if(err){
            callback(err);
        }else if(result.length>1){
            callback(new Error("Multiple users with email "+email));
        }else if(result.length===0){
            callback(new Error("No user with the specified email"))
        }
        else{
            callback(undefined,result[0].userId);
        }
    });
};

changeUserPassword = function(userId, currentPassword, newPassword, callback){
    flow.create("Validate Password", {
        begin: function () {

            if (newPassword === currentPassword) {
                callback(new Error("You should enter different passwords"), false);
            }
            else if (newPassword.length < passwordMinLength) {
                callback(new Error("Your new password it too short! It should have at least " + passwordMinLength + " characters "), false);
            }
            else{
                persistence.findById("DefaultUser", userId, this.continue("validatePassword"));
            }
        },
        validatePassword: function (err, user) {
            var self = this;
            if (err || ! user) {
                callback(err, null);
            }
            else{
                hashThisPassword(currentPassword,user.salt,function(err,hashedPassowrd){
                    if(hashedPassowrd===user.password){
                        user.activationCode = "0";
                        setNewPassword(user,newPassword,callback);
                    }else{
                        callback(new Error("The password you provided does not match our records"));
                    }
                })
            }
        }
    })();
};

setNewPassword = function(user,newPassword,callback){
    user.salt = crypto.randomBytes(48).toString('base64');
    hashThisPassword(newPassword,user.salt,function(err,hashedPassword){
        user.password = hashedPassword;
        persistence.saveObject(user,callback);
    });
};

function hashThisPassword(plainPassword,salt,callback){
    crypto.pbkdf2(plainPassword, salt, 20000, 512, 'sha512',function(err,res){
        if(err){
            callback(err)
        }
        else{
            callback(undefined,res.toString('base64'));
        }
    });
};

createOrganisation = function (organisationDump, callback) {
    flow.create("create organisation", {
        begin: function () {
            persistence.lookup("Organisation", organisationDump.organisationId, this.continue("createOrganisation"));
        },
        createOrganisation: function (err, organisation) {
            if (err) {
                callback(err, null);
            }
            else {
                if (!persistence.isFresh(organisation)) {
                    callback(new Error("Organisation with id " + organisationDump.organisationId + " already exists"), null);
                }
                else {
                    persistence.externalUpdate(organisation, organisationDump);
                    persistence.saveObject(organisation, this.continue("createReport"));
                }
            }
        },
        createReport: function (err, organisation) {
            callback(err, organisation);
        }
    })();
};

deleteOrganisation = function (organisationId,callback) {
    flow.create("delete organisation", {
        begin: function () {
            persistence.deleteById("Organisation", organisationId, this.continue("deleteReport"));
        },
        deleteReport: function (err, obj) {
            callback(err, obj);
        }
    })();
};

getOrganisations = function (callback) {
    flow.create("get all organizations", {
        begin: function () {
            persistence.filter("Organisation",{}, this.continue("info"));
        },
        info: function (err, result) {
            callback(err, result);
        }
    })();
};

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
