'use strict';

/* Controllers */


app.controller('MyCtrl', function ($scope, $http, $log) {
	
	var url = 'Login';
	$http.get(url).then(function (response) {
		$scope.countries = response.data;
	}); 
	

    $scope.toggleSidebar = function() {
        $scope.toggle = !$scope.toggle;
    };


});
