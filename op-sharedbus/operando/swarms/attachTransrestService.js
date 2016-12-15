/**
 * Created by ciprian on 14.12.2016.
 */

var attachTransrestService = {
    REST:function(transformation){
        this.transformation = transformation;
        this.swarm("restTransformation");
    },
    restTransformation:{
        node:"WSServer",
        code:function(){
            var self = this;
            var failed = false;
            for(var transform in self.transformation) {
                if (self.transformation[transform].path.indexOf("restAPI") !== 1) {
                    self.error = new Error("Could not register restAPI because 'path' field in transform: "+transform+" does not start with 'restAPI'")
                    failed = true;
                    break;
                }
            }

            if(failed === true){
                self.home("failed");
            }else{
                try {
                    addRESTTransformation({
                        activateUser:{
                            method: 'GET',
                            params: ['validationCode'],
                            path : '/restAPI/activate/$validationCode',
                            code:function(validationCode){
                                console.log("Validating code: ",validationCode);
                                startSwarm("register.js","verifyValidationCode",validationCode);
                                return "Your account is activated";
                            }
                        }
                    });
                    console.log("Attached transformation ");
                    self.home("success");
                }catch(e){
                    self.error = e;
                    self.home('failed');
                }
            }
        }
    }
};


attachTransrestService;