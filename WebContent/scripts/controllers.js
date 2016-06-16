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
    
    $scope.overview = {};
    $scope.getMainOverview= function () {
    	SbapiService.GetMainOverview(1, function(response) {
    		$log.debug(response);
        	$scope.overview = response;
        });
    };
    
    $scope.getMainOverview();
}]);

angular.module('Transactions').controller('TransactionsController',['$scope', '$log', 'SbapiService', function ($scope, $log, SbapiService) {

	$scope.toggleSidebar = function() {
        $scope.toggle = !$scope.toggle;
    };
    
    $scope.overview = {};
    $scope.transactions = {};
    $scope.getTransactionsOverview= function () {
    	SbapiService.GetTransactionsOverview(1, function(response) {
    		$log.debug(response);
        	$scope.overview = response;
        	$scope.transactions = response.accounts[0].transactions;
        });
    };
    
    $scope.changeAccount = function (accountIndex) {
    	$log.debug("changing account to " + accountIndex);
    	$log.debug($scope.overview.accounts[accountIndex].transactions);
    	$scope.transactions = $scope.overview.accounts[accountIndex].transactions;
    }
    
    $scope.getTransactionsOverview();
}]);

angular.module('Transfer').controller('TransferController',['$scope', '$log', 'SbapiService', function ($scope, $log, SbapiService) {

	$scope.toggleSidebar = function() {
        $scope.toggle = !$scope.toggle;
    };
    
    $scope.transferFunds = function (accountid, receiver, amount) {
    	SbapiService.TransferFunds(1, accountid, receiver, amount, function(response) {
    		$log.debug(response);
        });
    };
    
    
}]);

angular.module('User').controller('UserController',['$scope', '$log', 'SbapiService', function ($scope, $log, SbapiService) {

	$scope.toggleSidebar = function() {
        $scope.toggle = !$scope.toggle;
    };
    
    $scope.overview = {};
    
    $scope.getUserOverview = function () {
    	SbapiService.GetUserOverview(1, function(response) {
    		$log.debug(response);
        	$scope.overview = response;
        });
    };
    
    $scope.getUserOverview();
}]);

angular.module('Newclient').controller('NewClientController',['$scope', '$log', 'SbapiService', function ($scope, $log, SbapiService) {

	$scope.toggleSidebar = function() {
        $scope.toggle = !$scope.toggle;
    };
    
    
   $scope.addNewClient = function() {
	   SbapiService.AddNewClient($scope.username, $scope.password, $scope.fname, $scope.lname, $scope.phone, $scope.cpr, $scope.street, $scope.streetno, $scope.zip, $scope.city, $scope.country, function(response) {
   		$log.debug(response);
       });
   }
}]);


