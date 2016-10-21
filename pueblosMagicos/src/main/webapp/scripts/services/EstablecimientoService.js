'use strict';

App.factory('EstablecimientoService', ['$http', '$q', 'CONFIG',  function($http, $q, CONFIG)
{
	return {
        
		getEstablecimiento: function(idEstablecimiento) 
	    {
	        return $http.get( CONFIG.urlWebService + '/establecimiento/' + idEstablecimiento )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetchAllEstados ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching estados');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getEstablecimientoByidPST: function(idPST) 
	    {
	        return $http.get( CONFIG.urlWebService + '/establecimiento/idpst/' + idPST )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetchAllEstados ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching estados');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	     
	    insertEstablecimiento: function(establecimiento)
	    {
	    	var data = $.param({
	    		pstIdUsuario: establecimiento.pstIdUsuario,
	    		nombreComercial: establecimiento.nombreComercial,   		
	    		rfc: establecimiento.rfc,
	    		nombreVialidad: establecimiento.nombreVialidad,
	    		numeroExterior: establecimiento.numeroExterior,
	    		numeroInterior: establecimiento.numeroInterior,
	    		tvIdTipoVialidad: establecimiento.tvIdTipoVialidad,
	    		longitud: establecimiento.longitud,
	    		latitud: establecimiento.latitud,
	    		AIdAsentamiento: establecimiento.AIdAsentamiento
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			$http.put(CONFIG.urlWebService + '/turista', data, config)
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