/**
 * Created by ciprian on 4/20/17.
 */




var udeSwarming = {
    registerNotificationIdentifier:function(deviceId,notificationId){
        this.deviceId = deviceId;
        this.notificationId = notificationId;
        this.swarm('registerNotificationI')
    },
    registerNotificationI:{
        node:"UDEAdapter",
        code:function(){
            var self = this;
            registerNotificationIdentifier(this.deviceId,this.notificationId,S(function(err,result){
                if(err){
                    self.err = err.message;
                    self.home('failed');
                }else{
                    self.home("Notification Identifier Registered")
                }
            }))
        }
    },
    registerDevice:function(deviceId){
        this.deviceId = deviceId;
        this.swarm('registerDeviceId')
    },
    registerDeviceId:{
        node:"UDEAdapter",
        code:function(){
            var self = this;
            registerDevice(this.deviceId,this.meta.userId,S(function(err,result){
                if(err){
                    self.err = err.message;
                    self.home('failed');
                }else{
                    self.home("Device Registered")
                }
            }))
        }
    },
    registerApplication:function(applicationId,deviceId){
        this.deviceId = deviceId;
        this.applicationId = applicationId;
        this.swarm('registerApp');
    },
    registerApp:function(applicationId,deviceId){
        var self = this;
        registerApplicationInDevice(applicationId,deviceId,S(function(err,result){
            if(err){
                self.err = err.message;
                self.home('failed');
            }else{
                self.home('Application Registeres');
            }
        }))
    }

};
udeSwarming;
