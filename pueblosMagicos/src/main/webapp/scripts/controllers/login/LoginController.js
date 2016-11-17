'use strict';
  
App.controller('LoginController', ['$scope', '$rootScope', '$location', '$cookieStore', 'AuthenticationService', function ($scope, $rootScope, $location, $cookieStore, AuthenticationService) 
{
    AuthenticationService.clearCredentials();
  
    $scope.login = function () 
    {
    	$scope.auth($scope.usuario)
    };
    
    $scope.auth = function(usuario)
    {
    	console.log('login');
    	AuthenticationService.auth(usuario)
    	.then
    	(
    		function(d)
    		{		        
    			console.log(d.data);
    			if( d.data != '' )
				{
    				$rootScope.globals = 
                    {
                        currentUser : JSON.parse(JSON.stringify(d.data))
                    };
    				 //$http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; // jshint ignore:line
                    console.log('globals');
    				console.log( $rootScope.globals );
    				$cookieStore.put('globals', $rootScope.globals);
                    $location.path('/');
				}else
				{
					$scope.error = true;
				}
                
                
    		},
    		function(errResponse)
    		{
    			console.log('login error');
    			return null;
    		}
    	);
    }
    
}]);

