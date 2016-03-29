/**
 * Created by ciprian on 3/18/16.
 */
var fetchTests = {
    start:function () {
        console.log("Fetch available tests");
        this.swarm("fetchTests");
    },
    fetchTests:{
        node:"TestsManager",
        code:function () {
            this.tests = getAvailableTests();
            this.home("gotTests");
        }
    }
}

fetchTests;