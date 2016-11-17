'use strict';

App.factory('MailService', ['$http', '$q', 'CONFIG',  function($http, $q, CONFIG)
{
		return {
	             
		    enviarCorreo: function(email, subject, body)
		    {
		    	
		    	var data = $.param({
		    		email:email,
		    		subject:subject,
		    		body:body
		    	});
		    	console.log(data);
				var config = 
				{
		            headers : 
		            {
		                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
		            }
		        }
				
				return $http.post(CONFIG.urlWebService + '/enviarCorreo', data, config)
		        .success(function (data, status, headers, config) {
		            return data;
		        })
		        .error(function (data, status, header, config) {
		            return "Data: " + data +
		                "<hr />status: " + status +
		                "<hr />headers: " + header +
		                "<hr />config: " + config;
		        });
	        }
    }; 
 
}]);