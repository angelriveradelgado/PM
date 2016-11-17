'use strict';

App.factory('PSTService', ['$http', '$q', 'CONFIG',  function($http, $q, CONFIG)
{
		return {
	        
	        
	        getPSTByIdPST: function(id) 
	        {
	            return $http.get( CONFIG.urlWebService + '/pst/' + id )
	            .then
	            (
	                function(response)
	                {
	                    return response.data;
	                }, 
	                function(errResponse)
	                {
	                    console.error('Error while fetching pueblosMagicos');
	                    return $q.reject(errResponse);
	                }
	            );
	        },
	        
	        getPSTByIdEstadoRegistroByLimit: function(idEstadoRegistro, first, numReg) 
	        {
	            return $http.get( CONFIG.urlWebService + '/pst/estadoRegistro/' + idEstadoRegistro + '/limit/' + first + '/' + numReg )
	            .then
	            (
	                function(response)
	                {
	                    return response.data;
	                }, 
	                function(errResponse)
	                {
	                    console.error('Error while fetching pueblosMagicos');
	                    return $q.reject(errResponse);
	                }
	            );
	        },
	        		
	        updatePST: function(pst)
		    {
		    	
		    	var data = $.param({
		    		idUsuario:pst.idUsuario,
		    		erIdEstadoRegistro:pst.erIdEstadoRegistro,
		    		numeroRNT:pst.numeroRnt,
		    		telefono:pst.telefono,
		    		razonSocialEmpresa:pst.razonSocialEmpresa
		    	});
		    	console.log(data);
				var config = 
				{
		            headers : 
		            {
		                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
		            }
		        }
				
				return $http.post(CONFIG.urlWebService + '/PSTEdit', data, config)
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