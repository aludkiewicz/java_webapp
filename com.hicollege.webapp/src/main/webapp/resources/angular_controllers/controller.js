var startApp = angular.module('hicollege_webapp', []);


startApp.controller('hicollege_ctrl', function ($scope, $http, $log) {
	
	$scope.findUserById = function () {
    	$http({
        	method: 'GET', 
        	url: '/find/allusers'	
    	}).
        success(function (data, status, headers, config) {
        	$scope.user = data;
        }).
        error(function (data, status, headers, config) {
            $log.error(status);
        });
	};
});