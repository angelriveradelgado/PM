'use strict';

App.factory('AtractivoTuristicoService', ['$http', '$q', 'CONFIG',  function($http, $q, CONFIG)
{
	return {
        	             
        getAtractivoTuristicoById: function(idAT) 
        {
            return $http.get( CONFIG.urlWebService + '/atractivoTuristico/' + idAT )
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
        
        getDireccionIdAtractivoTuristico: function(idAT) 
        {
            return $http.get( CONFIG.urlWebService + '/atractivoTuristico/' + idAT + '/direccion')
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
        
        getAtractivosTuristicosByPuebloMagico: function(id) 
        {
            return $http.get( CONFIG.urlWebService + '/atractivoTuristico/puebloMagico/' + id )
            .then
            (
                function(response)
                {
                    return response.data;
                }, 
                function(errResponse)
                {
                    console.error('Error while fetching atractivos');
                    return $q.reject(errResponse);
                }
            );
        },
        
        getAtractivosTuristicosByPuebloMagicoByLimit: function(id, first, numRegistros) 
        {
            return $http.get( CONFIG.urlWebService + '/atractivoTuristico/puebloMagico/' + id + '/limit/' + first + '/' + numRegistros)
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
        
//        Pide la lista de fotos de un pueblomagico
        getAllFotosAtractivoTuristicoByIdAT: function(idPM) 
        {
            return $http.get( CONFIG.urlWebService + '/atractivoTuristico/{id}/fotos' )
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
	     
        createUsuarioTurista: function(atractivoTuristico)
	    {
	    	console.log(usuario);
	    	var data = $.param({
	    		nombre: atractivoTuristico.nombre,  
	    		descripcion: atractivoTuristico.descripcion, 
	    		latitud: atractivoTuristico.latitud, 
	    		longitud: atractivoTuristico.longitud, 
	    		idTurista: atractivoTuristico.idTurista, 
	    		idTipoAtractivo: atractivoTuristico.idTipoAtractivo, 
	    		idAdministrador: atractivoTuristico.idAdministrador, 
	    		idAsentamiento: atractivoTuristico.idAsentamiento
	    		});
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			$http.post(CONFIG.urlWebService + '/atractivoTuristico', data, config)
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
        
        createUsuarioTurista: function(atractivoTuristico)
	    {
	    	console.log(usuario);
	    	var data = $.param({
	    		idAtractivoTuristico: atractivoTuristico.idAtractivoTuristico,  
	    		nombre: atractivoTuristico.nombre,  
	    		descripcion: atractivoTuristico.descripcion, 
	    		latitud: atractivoTuristico.latitud, 
	    		longitud: atractivoTuristico.longitud, 
	    		idTurista: atractivoTuristico.idTurista, 
	    		idTipoAtractivo: atractivoTuristico.idTipoAtractivo, 
	    		idAdministrador: atractivoTuristico.idAdministrador, 
	    		idAsentamiento: atractivoTuristico.idAsentamiento,
	    		idEstadoRegistro: atractivoTuristico.idEstadoRegistro, 
	    		promedio: atractivoTuristico.promedio
	    		});
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			$http.put(CONFIG.urlWebService + '/atractivoTuristico', data, config)
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
	     
	   
     
        createUsuarioTurista: function(atractivoTuristico)
	    {
	    	console.log(usuario);
	    	var data = $.param({
	    		idAtractivoTuristico: atractivoTuristico.idAtractivoTuristico
	    		});
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			$http.put(CONFIG.urlWebService + '/atractivoTuristico', data, config)
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