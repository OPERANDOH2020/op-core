/**
 * Created by ciprian on 09.03.2017.
 */


var mysql     = require('mysql');
var container = require('safebox').container;
var apersistence = require('apersistence');
/*
var connectionSettings = {
    connectionLimit:10,
    host     : thisAdapter.config.Core.mysqlHost,
    port     : thisAdapter.config.Core.mysqlPort,
    user     : 'root',
    password : thisAdapter.config.Core.mysqlDatabasePassword,
    database : thisAdapter.config.Core.mysqlDatabaseName
}*/

var connectionSettings = {
    connectionLimit:10,
    host     : "localhost",
    port     : 3306,
    user     : 'root',
    password : "operando",
    database : "operando"
};

var mysqlConnection = mysql.createPool(connectionSettings);
container.resolve('mysqlConnection',mysqlConnection);

container.declareDependency("mysqlPersistence",['mysqlConnection'],function(outOfService,mysqlConnection){
    if(outOfService){
        console.log("MySQL persistence failed");
    }else{
        console.log("Initialising MySQL persistence");
        return apersistence.createMySqlPersistence(mysqlConnection);
    }
});


