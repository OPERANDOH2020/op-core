/**
 * Created by ciprian on 3/18/16.
 */
var testRunner = {
    start:function (tests) {
        this.tests = tests;
        this.swarm("runTests");
    },
    runTests:{
        node:"TestsManager",
        code:function () {
            runTests(this.tests,S(function(result){
                startSwarm("FetchResult.js","start",result);
            }));
        }
    }
}

testRunner;