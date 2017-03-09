/*
var core = require("swarmcore");
core.createAdapter("UsersManager");
*/

const crypto = require('crypto');
var container = require("safebox").container;
var flow = require("callflow");
var uuid = require('uuid');
var passwordMinLength = 4;
var persistence = undefined;
var saltLength = 48;


require('/home/ciprian/storage/Workspace/op-core/op-sharedbus/autolib/mysqlPersistence.js');

container.declareDependency("UsersManagerAdapter",["mysqlPersistence"],function(outOfService,mysqlPersistence){
    if(outOfService){
        console.log("UsersManager failed");
    }else{
        console.log("Initialising UsersManager");
        persistence = mysqlPersistence;
        rebootUsersManager(test);
    }
});

function test(){

    var users =  [{
            userId: "id21",
            password: "guest",
            email: "guedsafsdst@operando.eu",
            organisationId: "Public"
        },
        {
            userId: "id12",
            password: "analyst",
            email: "asadgadsfgnalyst@rms.ro",
            organisationId: "Analysts"
        },
        {
            userId:"id443",
            password: "haraka",
            email: "iAmAnEmailSsdgfdsfgerver@plusprivacy.com",
            organisationId: "Public"
        }];


    console.log("Running test");


    var userData = {
        userId: "id21",
        password: "guest",
        email: "guedsafsdst@operando.eu",
        organisationId: "PublicAAAAAA"
    }

    updateUser(userData,console.log);

}



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
        console.log("UsersManager available")
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
