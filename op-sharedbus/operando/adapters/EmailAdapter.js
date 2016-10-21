/**
 * Created by ciprian on 26.09.2016.
 */

var mysql      = require('mysql');
/*
var core = require("swarmcore");
thisAdapter = core.createAdapter("EmailAdapter");

var mysqlConnection = mysql.createConnection({
    host     : thisAdapter.config.Core.mysqlHost,
    port     : thisAdapter.config.Core.mysqlPort,
    user     : 'root',
    password : thisAdapter.config.Core.mysqlDatabasePassword,
    database : thisAdapter.config.Core.mysqlDatabaseName
});
*/

var mysqlConnection = mysql.createConnection({
    user     : 'root',
    password : 'operando',
    database : 'operando'
});
var uuid = require('node-uuid');
var apersistence = require('apersistence');
var persistence = apersistence.createMySqlPersistence(mysqlConnection);
var conversationModel = {
    id:{
        type:"string",
        pk:true

    },
    sender:{
        type:'string'
    },
    receiver:{
        type:'string'
    }
};
/*
Initialize the database connection then
Start Haraka
 */

persistence.registerModel("conversation",conversationModel,function(err,result){
    if(err){
        console.log(err);
    }else {
        startHaraka();
    }
});


registerConversation = function(sender,proxy,callback){
    var newConversationUUID = uuid.v1();
    newConversationUUID = new Buffer(newConversationUUID).toString('base64');
    var conversation = apersistence.createRawObject('conversation',newConversationUUID);
    conversation['sender'] = sender;
    conversation['receiver'] = proxy;
    persistence.save(conversation,function(err,res){
        if(err){
            callback(err);
        }else{
            callback(undefined,newConversationUUID)
        }
    });
};

getConversation = function(uuid,callback){
    persistence.findById('conversation',uuid,function(err,conversation){
        if(err){
            callback(err);
            return;
        }
        if(conversation===null){
            callback(new Error("Conversation "+uuid+" does not exist"));
            return;
        }
        callback(err,conversation);
    })
};

removeConversation = function(conversationUUID,callback){
    persistence.deleteById('conversation',conversationUUID,callback);
};

const mailer = require('nodemailer');
var smtpTransport = require('nodemailer-smtp-transport');
var transporter = mailer.createTransport(smtpTransport({host: '127.0.0.1', port: 25}));
sendEmail = function(from,to,subject,text,callback){
    transporter.sendMail({
        "from": from,
        "to": to,
        "subject": subject,
        "text": text
    }, callback)
};


function startHaraka(){
    var pathToHaraka ="/home/ciprian/storage/Workspace/op-interfaces/op-interfaces-email-services/op-haraka-server";
    var spawn = require('child_process').spawn;
    var haraka_server;
    haraka_server = spawn("haraka", ['-c',pathToHaraka],{'detached':true});
    haraka_server.stdout.on('data',(data)=>{
        console.log(data.toString());
    });
}

