'use strict';

App.factory('EstablecimientoService', ['$http', '$q', 'CONFIG',  function($http, $q, CONFIG)
{
	return {
        
		getEstablecimientoById: function(idEstablecimiento) 
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
	    
	    getEstablecimientosByIdEstadoRegistroByLimit: function(idEstadoRegistro, first, numReg) 
	    {
	        return $http.get( CONFIG.urlWebService + '/establecimiento/idEstadoRegistro/' + idEstadoRegistro + '/limit/' + first + '/' + numReg )
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
	    
	    getDireccionByIdEstablecimiento: function(id) 
        {
            return $http.get( CONFIG.urlWebService + '/establecimiento/' + id + '/direccion')
            .then
            (
                function(response)
                {
                    return response.data;
                }, 
                function(errResponse)
                {
                    console.error('Error while fetching atractivos Turisticos');
                    return $q.reject(errResponse);
                }
            );
        },
        
	    getTiposVialidad: function() 
	    {
	        return $http.get( CONFIG.urlWebService + '/tiposVialidad' )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fe	tchAlltipos ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching tipos');
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
	     
	    createEstablecimiento: function(establecimiento)
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
			
			
			return $http.post( CONFIG.urlWebService + '/establecimiento', data, config )
	        .then
	        (
	            function(response)
	            {
	            	console.log('create establecimiento ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error create establecimiento');
	                return $q.reject(errResponse);
	            }
	        );
			
//			return $http.post(CONFIG.urlWebService + '/establecimiento', data, config)
//	        .success(function (data, status, headers, config) {
//	            return data;
//	        })
//	        .error(function (data, status, header, config) {
//	            return "Data: " + data +
//	                "<hr />status: " + status +
//	                "<hr />headers: " + header +
//	                "<hr />config: " + config;
//	        });
        }, 
        
	    updateEstablecimiento: function(establecimiento)
	    {
	    	var data = $.param({
	    		idEstablecimiento: establecimiento.idEstablecimiento,
	    		pstIdUsuario: establecimiento.pstIdUsuario,
	    		nombreComercial: establecimiento.nombreComercial,   		
	    		rfc: establecimiento.rfc,
	    		nombreVialidad: establecimiento.nombreVialidad,
	    		numeroExterior: establecimiento.numeroExterior,
	    		numeroInterior: establecimiento.numeroInterior,
	    		vidUsuario: establecimiento.VIdUsuario,
	    		tvIdTipoVialidad: establecimiento.tvIdTipoVialidad,
	    		longitud: establecimiento.longitud,
	    		latitud: establecimiento.latitud,
	    		AIdAsentamiento: establecimiento.aidAsentamiento,
	    		erIdEstadoRegistro: establecimiento.erIdEstadoRegistro
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			
			return $http.post(CONFIG.urlWebService + '/establecimientoEdit', data, config)
	        .success(function (data, status, headers, config) {
	            return data;
	        })
	        .error(function (data, status, header, config) {
	            return "Data: " + data +
	                "<hr />status: " + status +
	                "<hr />headers: " + header +
	                "<hr />config: " + config;
	        });
        },
        
        deleteEstablecimiento: function(id) 
	    {
	        return $http.delete( CONFIG.urlWebService + '/establecimiento/' + id)
	        .then
	        (
	            function(response)
	            {
	            	console.log('establecimiento eliminado');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error al eliminar establecimiento');
	                return $q.reject(errResponse);
	            }
	        );
	    },
        
         
    }; 
 
}]);