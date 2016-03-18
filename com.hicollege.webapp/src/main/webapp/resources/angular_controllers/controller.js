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
            	albums: $scope.user_albums
            }
    	}).
        success(function (data, status, headers, config) {
        	$scope.user = data;
        }).
        error(function (data, status, headers, config) {
            $log.error(status);
        });
	};
	
	$scope.addAlbum = function () {
    	$http({
        	method: 'PUT', 
        	url: '/add/album',
            params: {
            	title: $scope.albumTitle,
            	songs: $scope.albumSongs,
            	artists: $scope.albumArtists
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
        	url: '/find/allusers'
    	}).
        success(function (data, status, headers, config) {
        	$scope.users = data;
        }).
        error(function (data, status, headers, config) {
            $log.error(status);
        });
	};
	
	
	$scope.getAllAlbums = function () {
    	$http({
        	method: 'GET', 
        	url: '/find/allalbums'
    	}).
        success(function (data, status, headers, config) {
        	$scope.albums = data;
        }).
        error(function (data, status, headers, config) {
            $log.error(status);
        });
	};
});