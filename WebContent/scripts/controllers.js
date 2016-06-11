'use strict';

/* Controllers */
/*

app.controller('MyCtrl', function ($scope, $http, $log, $location) {
    $scope.toggleSidebar = function() {
        $scope.toggle = !$scope.toggle;
    };
});*/

angular.module('Authentication').controller('LoginController', ['$scope', '$rootScope', '$location', 'AuthenticationService','$log',
function ($scope, $rootScope, $location, AuthenticationService, $log) {
    // reset login status
    AuthenticationService.ClearCredentials();
  
    $scope.login = function () {
        $scope.dataLoading = true;
        AuthenticationService.Login($scope.username, $scope.password, function(response) {
        	$log.debug(response.success);
        	if(response.success) {
                AuthenticationService.SetCredentials($scope.username, $scope.password);
                $location.path('/');
        	} else {
                $scope.error = response.message;
                $scope.dataLoading = false;
            }
        });
    };
}]);

angular.module('Home').controller('HomeController',['$scope', '$log', 'SbapiService', function ($scope, $log, SbapiService) {

	$scope.toggleSidebar = function() {
        $scope.toggle = !$scope.toggle;
    };
    
    $scope.getTransactions = function () {
    	SbapiService.GetTransactionsList($scope.toggle, function(response) {
        	$log.debug(response);
        	
        });
    };
    
    $scope.getTransactions();
}]);


