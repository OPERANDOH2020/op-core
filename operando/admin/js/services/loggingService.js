/**
 * Created by ciprian on 4/18/16.
 */
angular.module('app')
    .service("loggingService", function () {
        var enabled = false;
        var logs = [];
        var logLevels = {};
        var logsListeners = {};



        var limit = 200;
        var start = 0;


        function startLogger(limit,startTimeStamp) {
            swarmHub.startSwarm("Logger.js", "start", limit, startTimeStamp);


            swarmHub.on("LogsFetcher.js", "getLogLevels", function (swarm) {
                for(var level in swarm.levels){
                    logs[level] = [];
                }
            })


            swarmHub.on("LogsFetcher.js", "gotNewLog", function (swarm) {
                logs[swarm.log['level']].push(swarm.log);
                for (var observer in logListeners) {
                    logsListeners[observer](swarm.log);
                }
            })
        }

        function getMoreLogs(startTimestamp,nr_of_logs){

        }





        return {
            enableLoggingService : function(){
                if(enabled === false){
                    startLogger();
                    enabled = true;
                }
            },
            registerForLogs: function (whoAsks,onNewLogs) {
                logs.forEach(onNewLogs);
                logsListeners[whoAsks] = onNewLogs;
            },

            getMoreLogs:function(type,timestampOfLastLog){


            },
            getLogLevels:function(){
                return logLevels;
            }
        }
    });