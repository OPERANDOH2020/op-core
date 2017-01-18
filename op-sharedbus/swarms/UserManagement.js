var testConnection =
{
    createUser:function(userData){
        console.log("Creating user with data:",userData);
        this.userData = userData;
        this.swarm("create")
    },
    create:{
        node:"UsersManager",
        code: function(){
            var self  = this;
            createUser(this.userData,S(function(err,result){
                if(err){
                    self.result = err.message;
                    self.home('failed');
                }else{
                    self.result = result;
                    self.home('success');
                }
            }))
        }
    },


    editUser:function(userData){
        console.log("Updating user with data:",userData);
        this.userData = userData;
        this.swarm("edit")
    },
    edit:{
        node:"UsersManager",
        code: function(){
            var self  = this;
            updateUser(this['userData'],S(function(err,result){
                if(err){
                    self.result = err.message;
                    self.home('failed');
                }else{
                    self.result = result;
                    self.home('success');
                }
            }))
        }
    },


    filterUsers:function(filter){
        console.log("Fetching users matching filter :",filter);
        this['filter'] = filter;
        this.swarm("filter");
    },
    filter:{
        node:"UsersManager",
        code: function(){
            var self  = this;
            filterUsers(this['filter'],S(function(err,result){
                if(err){
                    self.result = err.message;
                    self.home('failed');
                }else{
                    self.result = result;
                    self.home('success');
                }
            }))
        }
    }
};

testConnection;