var log = {
    start:function (logs) {
        console.log("Starting Logging swarm");
        this.logs = logs;
        var counter  = this.broadcast("heartbeat");
    },
    doSomeTests:{
        node:"TestsManager",
        code:function () {
            runTests()
            this.home('Success');
        }
    }
}
log;
