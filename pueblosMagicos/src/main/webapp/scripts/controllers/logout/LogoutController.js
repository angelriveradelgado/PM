'use strict';
  
App.controller('LogoutController', ['$scope', '$rootScope', '$location', '$cookieStore', 'AuthenticationService', function ($scope, $rootScope, $location, $cookieStore, AuthenticationService) 
{
	 $scope.logout = function () 
    {
		AuthenticationService.clearCredentials();
	    $location.path('/');
    };
	    
    
}]);