'use strict';
  
angular.module('Getters')

.factory('SbapiService',
    ['$http', '$rootScope', '$log',
    function ($http, $rootScope, $log) {
        var service = {};
        
        service.GetMainOverview = function (clientid, callback) {
        	 
        	$http({
        	    url: "main/overview",
        	    method: "POST",
        	    params: {clientid:clientid}
        	    })
                .success(function (response) {
                    callback(response);
                });
 
        };
        
        service.GetUserOverview = function (clientid, callback) {
          	 
        	$http({
        	    url: "user/overview",
        	    method: "POST",
        	    params: {clientid:clientid}
        	    })
                .success(function (response) {
                    callback(response);
                });
 
        };
        
        service.GetTransactionsOverview = function (clientid, callback) {
       	 
        	$http({
        	    url: " transactions/list",
        	    method: "POST",
        	    params: {clientid:clientid}
        	    })
                .success(function (response) {
                    callback(response);
                });
 
        };
        
        service.GetTransferOverview = function (clientid, callback) {
         	 
        	$http({
        	    url: "transfer/list",
        	    method: "POST",
        	    params: {clientid:clientid}
        	    })
                .success(function (response) {
                    callback(response);
                });
 
        };
        
        service.TransferFunds = function (accountid, regno, accountno, amount, description, callback) {
          	 
        	$http({
        	    url: "transfer/create",
        	    method: "POST",
        	    params: {accountid:accountid, regno:regno, accountno:accountno, amount:amount, description:description}
        	    })
                .success(function (response) {
                    callback(response);
                });
 
        };
        
        service.AddNewAccount = function (clientid, accountname, callback) {
         	 
        	$http({
        	    url: "account/create",
        	    method: "POST",
        	    params: {clientid:clientid, accountname:accountname}
        	    })
                .success(function (response) {
                    callback(response);
                });
 
        };
        
        service.AddNewClient = function (username, password, fname, lname, phone, cpr, street, streetno, zip, city, country, callback){
        	$http({
        	    url: "client/create",
        	    method: "POST",
        	    params: {username:username, 
        	    	password:password, 
        	    	fname:fname, 
        	    	lname:lname,
        	    	phone:phone, 
        	    	cpr:cpr, 
        	    	street:street, 
        	    	streetno:streetno,
        	    	zip:zip, 
        	    	city:city, 
        	    	country:country}
        	    })
                .success(function (response) {
                    callback(response);
                });
        }
        
        return service;
    }]
);

angular.module('Authentication')
  
.factory('AuthenticationService',
    ['Base64', '$http', '$cookieStore', '$rootScope', '$timeout', '$log',
    function (Base64, $http, $cookieStore, $rootScope, $timeout, $log) {
        var service = {};
 
        service.Login = function (username, password, callback) {
 
        	$http({
        	    url: "login",
        	    method: "POST",
        	    params: {username:username,password:password}
        	    })
                .success(function (response) {
                    callback(response);
                });
 
        };
  
  
        service.SetCredentials = function (username, password) {
            var authdata = Base64.encode(username + ':' + password);
  
            $rootScope.globals = {
                currentUser: {
                    username: username,
                    authdata: authdata
                }
            };
  
            $http.defaults.headers.common['Authorization'] = 'Basic' + authdata; // jshint ignore:line
            $cookieStore.put('globals', $rootScope.globals);
        };
  
        service.ClearCredentials = function () {
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $http.defaults.headers.common.Authorization = 'Basic';
        };
  
        return service;
    }])
  
.factory('Base64', function () {
    /* jshint ignore:start */
  
    var keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
  
    return {
        encode: function (input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;
  
            do {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);
  
                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;
  
                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }
  
                output = output +
                    keyStr.charAt(enc1) +
                    keyStr.charAt(enc2) +
                    keyStr.charAt(enc3) +
                    keyStr.charAt(enc4);
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
            } while (i < input.length);
  
            return output;
        },
  
        decode: function (input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;
  
            // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
            var base64test = /[^A-Za-z0-9\+\/\=]/g;
            if (base64test.exec(input)) {
                window.alert("There were invalid base64 characters in the input text.\n" +
                    "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                    "Expect errors in decoding.");
            }
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
  
            do {
                enc1 = keyStr.indexOf(input.charAt(i++));
                enc2 = keyStr.indexOf(input.charAt(i++));
                enc3 = keyStr.indexOf(input.charAt(i++));
                enc4 = keyStr.indexOf(input.charAt(i++));
  
                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;
  
                output = output + String.fromCharCode(chr1);
  
                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }
  
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
  
            } while (i < input.length);
  
            return output;
        }
    };
  
    /* jshint ignore:end */
});