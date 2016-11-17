'use strict';

App.factory('PuebloMagicoService', ['$http', '$q', 'CONFIG',  function($http, $q, CONFIG)
{
	return {
        
	    fetchAllPueblosMagicos: function() 
	    {
            return $http.get( CONFIG.urlWebService + '/puebloMagico')
            .then
            (
                function(response)
                {
                	console.log('fetchAllPueblosMagicos ok ');
                    return response.data;
                },  
                function(errResponse)
                {
                    console.error('Error while fetching pueblosMagicos');
                    return $q.reject(errResponse);
                }
            );
        },
	        
	        
        fetchSomePueblosMagicos: function(first, max) 
        {
            return $http.get( CONFIG.urlWebService + '/puebloMagico/' + first + '/' + max )
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
        
        getAllPueblosMagicosByIdEstado: function(idEstado) 
        {
            return $http.get( CONFIG.urlWebService + '/puebloMagico/estado/' + idEstado )
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
        
        getPuebloMagicoById: function(id) 
        {
            return $http.get( CONFIG.urlWebService + '/puebloMagico/' + id )
            .then
            (
                function(response)
                {
                	console.log(response);
                	console.log(response.data);
                    return response.data;
                }, 
                function(errResponse)
                {
                    console.error('Error while fetching pueblosMagicos');
                    return $q.reject(errResponse);
                }
            );
        },
        
        getPuebloMagicoByNombre: function(nombre) 
        {
            return $http.get( CONFIG.urlWebService + '/puebloMagico/nombre/' + nombre )
            .then
            (
                function(response)
                {
                	console.log(response);
                	console.log(response.data);
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
        getAllFotosPuebloMagicoByIdPM: function(idPM) 
        {
            return $http.get( CONFIG.urlWebService + '/puebloMagico/' + idPM + '/fotos' )
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
	     
	    createPuebloMagico: function(puebloMagico)
	    {
	    	var data = $.param({
	    		nombre: puebloMagico.nombre,   		
	    		latitud: puebloMagico.latitud,
	    		longitud: puebloMagico.longitud,
	    		descripcion: puebloMagico.descripcion,
	    		idMunicipio: puebloMagico.idMunicipio
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			$http.post(CONFIG.urlWebService + '/puebloMagico', data, config)
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
	     
        updatePuebloMagico: function(puebloMagico)
	    {
	    	var data = $.param({
	    		idPuebloMagico: puebloMagico.idPuebloMagico,
	    		nombre: puebloMagico.nombre,   		
	    		latitud: puebloMagico.latitud,
	    		longitud: puebloMagico.longitud,
	    		descripcion: puebloMagico.descripcion,
	    		idEstadoPubloMagico: puebloMagico.idEstadoPubloMagico,
	    		idMunicipio: puebloMagico.midMunicipio
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			$http.post(CONFIG.urlWebService + '/puebloMagicoEdit', data, config)
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
     
	   deletePuebloMagico: function(id)
	   {
            return $http.delete( CONFIG.urlWebService + '/puebloMagico/' + id)
            .then(
                function(response)
                {
                    return response.data;
                }, 
                function(errResponse)
                {
                    console.error('Error while delete pm');
                    return $q.reject(errResponse);
                }
            );
        },
        
	       insertCalificacionPuebloMagico: function( calificacion )
		    {
		    	var data = $.param({
		    		idPuebloMagico: calificacion.idPuebloMagico,   		
		    		idUsuario: calificacion.idUsuario,
		    		calificacion: calificacion.calificacion,
		    		comentario: calificacion.comentario,
		    		idRegistroVisita: calificacion.idregistroVisita
		    	});
		    	
		    	console.log(data);
				var config = 
				{
		            headers : 
		            {
		                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
		            }
		        }
				
				return $http.post(CONFIG.urlWebService + '/puebloMagico/calificacion', data, config)
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