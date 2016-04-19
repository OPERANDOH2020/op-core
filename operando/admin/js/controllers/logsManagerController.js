/**
 * Created by ciprian on 4/12/16.
 */

'use strict';
app.controller('logsManagerController',["$scope","loggingService",function ($scope,loggingService) {

    $scope.logTypes = loggingService.getLogLevels();

    $scope.logsToDisplay = [];

    loggingService.enableLoggingService();


    for(var level in $scope.logTypes){
        $scope.logTypes[level] = [];
    }

    loggingService.registerForLogs('logsManagerController',function(log){
        var level = log.type;
        $scope.logTypes[level].push(log);
    })

    $scope.displayLogs= function(logType){
        $scope.logToDisplay = $scope.logTypes[logType];
        alert("Something");
    }
}])

