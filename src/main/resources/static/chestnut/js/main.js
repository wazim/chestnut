angular
    .module('chestnutApp',  ['ngRoute'])
    .config(['$routeProvider', function($routeProvider) {
    		    $routeProvider
    		    .when('/', {
    				    templateUrl: 'chestnut/index.html',
    				    controller: 'ChestnutCtrl'
    			})
    			.when('/:userId', {
                    	templateUrl: 'chestnut/index.html',
                    	controller: 'ChestnutCtrl'
                })
    			.otherwise({
                         redirectTo: 'chestnut/index.html'
                });
    }])
    .controller('ChestnutCtrl', function ($scope, $http, $location) {
        $scope.userId = 'jonathansharifi';
        $scope.items = [];

        $http.get('items/user/'+$scope.userId).
           success(function(data, status, headers, config) {
             $scope.items = data;
        })

        console.log($location.path());
    });