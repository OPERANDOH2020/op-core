'use strict';
app.controller('notificationsController', ['$scope','ModalService','swarmService',"authenticationService",
    function ($scope,ModalService,swarmService,authenticationService) {
        $scope.notification = {};
        $scope.notificationWasSent = false;
        $scope.errorOccured = false;


        authenticationService.authenticateUser("test@plusprivacy.com", "test", function(){}, function(){},function(){});
        swarmHub.startSwarm("zones.js","getAllZones");

        $scope.sendNotification = function(){
            if($scope.notification.actionType !==undefined){
                $scope.notification.action = $scope.notification.actionType;
                if($scope.notification.actionArgument !==undefined){
                    $scope.notification.action+=" with argument: "+$scope.notification.actionArgument
                }
            }
            $scope.notificationWasSent = false;
            $scope.errorOccured = false;

            ModalService.showModal({
                templateUrl: "tpl/modals/previewNotification.html",
                controller: "previewNotificationController",
                inputs:{
                    "notification":$scope.notification
                }
            }).then(function(modal) {
                modal.element.modal();
                modal.close.then(function(notification) {
                    swarmHub.startSwarm("notification.js","sendNotification",notification);
                });
            });
        };

        swarmHub.on("notification.js","notificationSent",function(swarm){
            $scope.notificationWasSent = true;
            $scope.notification = {};
            $scope.$apply();
        });

        swarmHub.on("notification.js","failed",function(swarm){
            $scope.errorOccured = true;
            console.log("Error "+swarm.err+" occured")
        });

        swarmHub.on("zones.js","gotAllZones",function(swarm){
            $scope.zones = swarm.zones;
            $scope.$apply();
        });

    }]);

app.controller('previewNotificationController', ['$scope',"notification","$element",'close', function($scope,notification,$element, close) {
    var template={
        "title":"Title: ",
        "zone":"Receiver: ",
        "type":"Type of notification: ",
        "category":"Category: ",
        "description":"Description: ",
        "action":"Action to take: "
    };

    $scope.notification = notification;

    $scope.previewNotification = {};
    for(var field in template){
        $scope.previewNotification[field] = template[field]+notification[field];
    }

    $scope.send = function(){
        $scope.notification.expirationDate = new Date($scope.notification.expirationDate);
        $element.modal('hide');
        close($scope.notification,500);
    }
}]);
