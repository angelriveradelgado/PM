'use strict';

App.factory('EstadoService', ['$http', '$q', 'CONFIG',  function($http, $q, CONFIG)
{
	return {
        
		fetchPueblosMagicosByIdEstado: function() 
	    {
            return $http.get( CONFIG.urlWebService + '/estados/pm')
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
        
        fetchEstados: function() 
	    {
            return $http.get( CONFIG.urlWebService + '/estados')
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
        
        fetchEstadosWithPM: function() 
	    {
            return $http.get( CONFIG.urlWebService + '/estados/pm')
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
        
        fetchMunicipiosByIdEstado: function(id) 
	    {
            return $http.get( CONFIG.urlWebService + '/estados/pm/municipios/idEstado/' + id)
            .then
            (
                function(response)
                {
                	console.log('fetchMunicipios ok ');
                    return response.data;
                },  
                function(errResponse)
                {
                    console.error('Error while fetching municipios');
                    return $q.reject(errResponse);
                }
            );
        },
        
        fetchMunicipiosByIdEstadoWithPM: function(id) 
	    {
            return $http.get( CONFIG.urlWebService + '/estados/pm/municipiosWithPM/idEstado/' + id)
            .then
            (
                function(response)
                {
                	console.log('fetchMunicipios ok ');
                    return response.data;
                },  
                function(errResponse)
                {
                    console.error('Error while fetching municipios');
                    return $q.reject(errResponse);
                }
            );
        },
        
        fetchAsentamientosByIdMunicipio: function(id) 
	    {
            return $http.get( CONFIG.urlWebService + '/estados/pm/asentamientos/idMunicipio/' + id)
            .then
            (
                function(response)
                {
                	console.log('fetchMunicipios ok ');
                    return response.data;
                },  
                function(errResponse)
                {
                    console.error('Error while fetching municipios');
                    return $q.reject(errResponse);
                }
            );
        },
	     
	    updateUsuarioTurista: function(usuario)
	    {
	    	console.log(service);
	    	console.log(usuario);
	    	var data = $.param({
	    		idUsuario: usuario.idUsuario,
	    		nombreUsuario: usuario.nombreUsuario,   		
	    		contrasena: usuario.contrasena,
	    		nombre: usuario.nombre,
	    		apellidoPaterno: usuario.apellidoPaterno,
	    		apellidoMaterno: usuario.apellidoMaterno,
	    		correo: usuario.correo,
	    		fechaNacimiento: usuario.fechaNacimiento,
	    		genero: usuario.genero
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			$http.post(CONFIG.urlWebService + '/turistaEdit', data, config)
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