var startApp = angular.module('hicollege_webapp', []);


startApp.controller('hicollege_ctrl', function ($scope, $http, $log) {
	
	$scope.addUser = function () {
    	$http({
        	method: 'PUT', 
        	url: '/add/user',
            params: {
            	username: $scope.username,
            	email: $scope.email,
            	age: $scope.age,
            	albums: $scope.albums
            }
    	}).
        success(function (data, status, headers, config) {
        	$scope.user = data;
        }).
        error(function (data, status, headers, config) {
            $log.error(status);
        });
	};
	
	
	$scope.getAllUsers = function () {
    	$http({
        	method: 'GET', 
        	url: '/get/allusers',
            params: {
            	username: $scope.username,
            	email: $scope.email,
            	age: $scope.age
            }
    	}).
        success(function (data, status, headers, config) {
        	$scope.users = data;
        	console.log($scope.users);
        }).
        error(function (data, status, headers, config) {
            $log.error(status);
        });
	};
});