'use strict';

app.controller('testsManagerController', ['$scope', function($scope ) {

    var running_options = {
        multipleSelect:false,
        showIcon:true
    }

    var not_running_options = {
        multipleSelect:true,
        showIcon:true
    }




    $scope.currentlyRunningTests = false;
    $scope.treeOptions = not_running_options;
    $scope.testsTree = [];
    $scope.currentTestResult = [];

    var all_nodes = [];
    var runningTests = [];
    $scope.runSelectedTests=  function(){
        $scope.treeOptions = running_options;
        $scope.currentlyRunningTests = true;
        runningTests = getTestsToRun();
        updateNodes(all_nodes,{'disabled':true,'selected':false});
        updateNodes(runningTests, {'disabled':false,style:{color:'blue'},current_running_message :' is running'});

        swarmHub.startSwarm("TestRunner.js","start",runningTests.map(function(node){return node.realPath}));

        swarmHub.on('FetchResult.js','gotResult',function(returningSwarm){
            var test;
            runningTests.some(function(runningTest){
                if(runningTest.realPath === returningSwarm.result.test){
                    test = runningTest;
                    return true;
                }else{
                    return false;
                }
            })

            if(returningSwarm.result['whyLog'] ){
                $scope.$apply(function(){
                    test['whyLog'] = returningSwarm.result['whyLog'];
                });
                return;
            }

            if(returningSwarm.result['message']){
                var testMessage = returningSwarm.result['message'];
                if(testMessage.match('Pass')){
                    $scope.$apply(function(){
                        if(test.style.color!=='red') {
                            test.style = {color: 'green'}
                        }
                    });
                    return;
                }
                if(testMessage.match('Fail')){
                    test.style = {color:'red'};
                    return;
                }
                return;
            }

            if(returningSwarm.result.result === "Terminated") {
                $scope.$apply(function(){
                    test.current_running_message = ' finished';
                });
                return;
            }

        })

        function getTestsToRun(){
            return all_nodes.filter(function(node){
                if(node.selected){
                    if(!node.children){
                        return true;
                    }
                }
                return false;
            })
        }
    }

    $scope.stopRunningTests = function(){
        $scope.treeOptions = not_running_options;
        $scope.currentlyRunningTests = false;
        updateNodes(all_nodes, {'style':undefined, 'result':"",'current_running_message':undefined,'disabled':false,'selected':false})
        runningTests = [];
    }

    function updateNodes(nodes,properties) {
        nodes.forEach(function (node) {
            for(var prop in properties) {
                node[prop] = properties[prop];

            }
        })
    }

    $scope.$on('selection-changed',function(e,node){
        if($scope.currentlyRunningTests === true){
            $scope.currentTestResult = getExecutionTree(node);
            return;
        }

        $scope.treeNodes.forEach(function(node){
            selectNode(node,false);
        })

        node.forEach(function(node){
            selectNode(node,true);
        })

        function selectNode(node,value){
            node.selected = value;
            if(node.children){
                node.children.forEach(function(node){
                    selectNode(node,value);
                });
            }
        }

        function getExecutionTree(node) {
            if(!node.whyLog){
                return [{
                    'name':'No why logs'
                }]
            }
            else{
                var result = [];
                for(var n in node.whyLog){
                    var newNode = {
                        'name':n
                    }
                    addChildren(newNode,node.whyLog[n]);
                    result.push(newNode);
                }
                return result;
            }
            function addChildren(node, children) {
                if(typeof children !== 'object'){
                    node.children=[{'name':children}];
                    return;
                }
                for (var child in children) {
                    if (!node['children']) {
                        node['children'] = [];
                    }
                    var newNode = {
                        'name': child
                    }
                    addChildren(newNode, children[child]);
                    node.children.push(newNode);
                }
            }
        }
    })

    swarmHub.startSwarm('FetchAvailableTests.js', 'start');
    swarmHub.on("FetchAvailableTests.js", 'gotTests', function (thisSwarm) {
        var tests = thisSwarm.tests;

        $scope.treeNodes = getFolderStructure(tests);
        $scope.treeOptions = not_running_options;

        function getFolderStructure(testsList){

            var nodes = [];
            testsList.forEach(function(testPath){
                var arrayedPath = testPath.split("/");
                var currentPath = "";
                var tree = nodes;
                arrayedPath.forEach(function(pathNode){

                    currentPath+=pathNode+"/";

                    var parent = undefined;
                    var gotNode =tree.some(function(node){
                        if(node.name===pathNode){
                            parent = node;
                            return true;
                        }else{
                            return false
                        }
                    })


                    if(!gotNode){
                        var newNode = {
                            name:pathNode,
                            children:[],
                            realPath:currentPath,
                            selected:false,
                            result:undefined
                        }
                        tree.push(newNode);
                        tree = newNode.children;
                        all_nodes.push(newNode);
                    }else{
                        tree = parent.children;
                    }

                })
            })

            function detectScriptsInTree(nodes) {
                nodes.forEach(function (node) {
                    if(node.name.match(".js")){
                        delete node.children
                        delete node.result
                    }else{
                        detectScriptsInTree(node.children)
                    }
                });
            }
            detectScriptsInTree(nodes);

            function dropRedundantNodes(nodes){
                if(nodes.length===1){
                    if(nodes[0].children.length > 1){
                        return nodes[0].children;
                    }else{
                        return dropRedundantNodes(nodes[0].children);
                    }
                }else{
                    return nodes;
                }
            }

            nodes = dropRedundantNodes(nodes);
            return nodes;
        }
        console.log($scope.testsTree);
    })
}]);


