'use strict';

App.controller('NavegationController', function($rootScope, $http, $location)
{
	var self = this;
	var authenticate = function(credentials, callback)
	{
		var headers = credentials ? {authorization : "Basic " + btoa(credentials.username + ":" + credentials.password)} : {};
	    $http.get('http://localhost:8080/pueblosMagicos/usuario', {headers : headers})
	    .then
	    (
    		function(response)
    		{
    			if(response.data.name)
				{
    				$rootScope.authenticated = true;
				}else
				{
					$rootScope.authenticated = false;
				}
    			callback && callback();
    		}
	    );
	}
	
	authenticate();
	self.credentials = {};
	self.login = function()
	{
		authenticate(self.credentials, function()
		{
			if($rootScope.authenticated)
			{
				$location.path("/");
				self.error = false;
			}else
			{
				$location.path("/login");
				self.error = true;
			}
			
			
		});
	};
	
});