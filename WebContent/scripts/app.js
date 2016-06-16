'use strict';

// declare modules
angular.module('Authentication', []);
angular.module('Getters', []);
angular.module('Home', []);
angular.module('User', []);
angular.module('Newclient', []);
angular.module('Manageaccount', []);
angular.module('Transactions', []);
angular.module('Transfer', []);

angular.module('BasicHttpAuthExample', [
    'Authentication',
    'Getters',
    'Home',
    'User',
    'Newclient',
    'Manageaccount',
    'Transactions',
    'Transfer',
    'ngRoute',
    'ngCookies'
])

.config(['$routeProvider', function ($routeProvider) {

    $routeProvider
        .when('/login', {
            controller: 'LoginController',
            templateUrl: 'views/login.jsp'
        })

        .when('/', {
            controller: 'HomeController',
            templateUrl: 'views/home.jsp'
        })
        
        .when('/user', {
            controller: 'UserController',
            templateUrl: 'views/user.jsp'
        })
        
        .when('/newclient', {
            controller: 'NewClientController',
            templateUrl: 'views/newclient.jsp'
        })
        
        .when('/manageaccount', {
            controller: 'ManageAccountController',
            templateUrl: 'views/manageaccount.jsp'
        })
        
        .when('/transfer', {
        	controller: 'TransferController',
        	templateUrl: 'views/transfer.jsp'
        })
        
        .when('/transactions', {
        	controller: 'TransactionsController',
        	templateUrl: 'views/transactions.jsp'
        })

        .otherwise({ redirectTo: '/' });
}])

.run(['$rootScope', '$location', '$cookies','$cookieStore', '$http',
    function ($rootScope, $location, $cookies, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
            if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
                $location.path('/login');
            }
        });
    }]);