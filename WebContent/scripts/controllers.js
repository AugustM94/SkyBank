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
                $rootScope.clientId = response.clientId;
                $rootScope.username = response.username;
                $location.path('/');
        	} else {
                $scope.error = "Something went wrong";
                $scope.dataLoading = false;
            }
        });
    };
}]);

angular.module('Home').controller('HomeController',['$scope', '$rootScope', '$log', 'SbapiService', function ($scope, $rootScope, $log, SbapiService) {
	$log.debug("clientid:" + $rootScope.clientId);
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

angular.module('Transactions').controller('TransactionsController',['$scope', '$rootScope', '$log', 'SbapiService', function ($scope, $rootScope, $log, SbapiService) {
	$log.debug("clientid:" + $rootScope.clientId);
	
	$scope.toggleSidebar = function() {
        $scope.toggle = !$scope.toggle;
    };
    
    $scope.overview = {};
    $scope.transactions = {};
    $scope.getTransactionsOverview= function () {
    	SbapiService.GetTransactionsOverview($rootScope.clientId, function(response) {
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

angular.module('Transfer').controller('TransferController',['$scope', '$rootScope', '$log', 'SbapiService', function ($scope, $rootScope, $log, SbapiService) {
	$log.debug("clientid:" + $rootScope.clientId);
	$scope.toggleSidebar = function() {
        $scope.toggle = !$scope.toggle;
    };
    
    $scope.transferFunds = function (accountid, receiver, amount) {
    	SbapiService.TransferFunds(1, accountid, receiver, amount, function(response) {
    		$log.debug(response);
        });
    };
    
    
}]);

angular.module('User').controller('UserController',['$scope', '$rootScope', '$log', 'SbapiService', function ($scope, $rootScope, $log, SbapiService) {
	$log.debug("clientid:" + $rootScope.clientId);
	$scope.toggleSidebar = function() {
        $scope.toggle = !$scope.toggle;
    };
    
    $scope.overview = {};
    
    $scope.getUserOverview = function () {
    	SbapiService.GetUserOverview($rootScope.clientId, function(response) {
    		$log.debug(response);
        	$scope.overview = response;
        });
    };
    
    $scope.getUserOverview();
}]);

angular.module('Newclient').controller('NewClientController',['$scope', '$rootScope', '$log', 'SbapiService', function ($scope, $rootScope, $log, SbapiService) {
	$log.debug("clientid:" + $rootScope.clientId);
	$scope.toggleSidebar = function() {
        $scope.toggle = !$scope.toggle;
    };
    
    
   $scope.addNewClient = function() {
	   SbapiService.AddNewClient($scope.username, $scope.password, $scope.fname, $scope.lname, $scope.phone, $scope.cpr, $scope.street, $scope.streetno, $scope.zip, $scope.city, $scope.country, function(response) {
   		$log.debug(response);
       });
   } 
}]);

angular.module('Manageaccount').controller('ManageAccountController',['$scope', '$rootScope', '$log', 'SbapiService', function ($scope, $rootScope, $log, SbapiService) {
	$log.debug("clientid: " + $rootScope.clientId);
	$scope.toggleSidebar = function() {
        $scope.toggle = !$scope.toggle;
    };
    
    $scope.addNewAccount = function () {
    	$log.debug($rootScope.clientId);
    	SbapiService.AddNewAccount($rootScope.clientId, $scope.accountname, function(response) {
    		$log.debug(response);
        });
    };
   
}]);


