'use strict';

/* Controllers */
/*

app.controller('MyCtrl', function ($scope, $http, $log, $location) {
    $scope.toggleSidebar = function() {
        $scope.toggle = !$scope.toggle;
    };
});*/

angular.module('Authentication').controller('LoginController', ['$scope', '$rootScope', '$cookieStore','$location', 'AuthenticationService','$log',
function ($scope, $rootScope, $cookieStore, $location, AuthenticationService, $log) {
    // reset login status
    AuthenticationService.ClearCredentials();
  
    $scope.login = function () {
        $scope.dataLoading = true;
        AuthenticationService.Login($scope.username, $scope.password, function(response) {
        	$log.debug(response.success);
        	if(response.success) {
                AuthenticationService.SetCredentials($scope.username, $scope.password);
                

                $cookieStore.put('clientid', response.clientId);
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

angular.module('Home').controller('HomeController',['$scope', '$rootScope', '$cookieStore','$log', 'SbapiService', function ($scope, $rootScope, $cookieStore, $log, SbapiService) {
	$rootScope.clientId = $cookieStore.get('clientid');
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

angular.module('Transactions').controller('TransactionsController',['$scope', '$rootScope', '$cookieStore', '$log', 'SbapiService', function ($scope, $rootScope, $cookieStore, $log, SbapiService) {
	$rootScope.clientId = $cookieStore.get('clientid');
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

angular.module('Transfer').controller('TransferController',['$scope', '$rootScope', '$cookieStore', '$log', 'SbapiService', function ($scope, $rootScope, $cookieStore, $log, SbapiService) {
	$rootScope.clientId = $cookieStore.get('clientid');
	$log.debug("clientid:" + $rootScope.clientId);
	
	$scope.toggleSidebar = function() {
        $scope.toggle = !$scope.toggle;
    };
    
    $scope.accounts = {};
    $scope.transferFunds = function () {
    	SbapiService.TransferFunds($scope.accountid, $scope.regno, $scope.accountno, $scope.amount, $scope.description, function(response) {
    		$log.debug(response);
        });
    };
    
    $scope.GetTransferOverview = function () {
    	SbapiService.GetTransferOverview($rootScope.clientId, function(response) {
    		$scope.accounts = response.accounts;
    		$log.debug(response.accounts);
        });
    };
    
    $scope.GetTransferOverview();
    
    
}]);

angular.module('User').controller('UserController',['$scope', '$rootScope', '$cookieStore', '$log', 'SbapiService', function ($scope, $rootScope, $cookieStore, $log, SbapiService) {
	$rootScope.clientId = $cookieStore.get('clientid');
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

angular.module('Newclient').controller('NewClientController',['$scope', '$rootScope', '$cookieStore', '$log', 'SbapiService', function ($scope, $rootScope, $cookieStore, $log, SbapiService) {
	$rootScope.clientId = $cookieStore.get('clientid');
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

angular.module('Manageaccount').controller('ManageAccountController',['$scope', '$rootScope', '$cookieStore', '$log', 'SbapiService', function ($scope, $rootScope, $cookieStore, $log, SbapiService) {
	$rootScope.clientId = $cookieStore.get('clientid');
	$log.debug("clientid:" + $rootScope.clientId);
	
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


