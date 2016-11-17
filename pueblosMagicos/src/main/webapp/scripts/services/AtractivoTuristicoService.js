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
        
        getDireccionByIdAtractivoTuristico: function(idAT) 
        {
        	console.log(CONFIG.urlWebService + '/atractivoTuristico/' + idAT + '/direccion');
            return $http.get( CONFIG.urlWebService + '/atractivoTuristico/' + idAT + '/direccion')
            .then
            (
                function(response)
                {
                    return response.data;
                }, 
                function(errResponse)
                {
                    console.error('Error while fetching direccion atractivo Turistico');
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
        getAllFotosAtractivoTuristicoByIdAT: function(id) 
        {
            return $http.get( CONFIG.urlWebService + '/atractivoTuristico/' + id + '/fotos' )
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
        
        
        getTiposAtractivoTuristico: function() 
        {
            return $http.get( CONFIG.urlWebService + '/atractivoturistico/tipos/atractivos' )
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
        
        
        getCalificacionesByIdAT: function(idAT) 
        {
            return $http.get( CONFIG.urlWebService + '/atractivoTuristico/' + idAT + '/Calificacion' )
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
        
        createAtractivoTuristico: function(atractivoTuristico)
	    {
	    	var data = $.param({
	    		nombre: atractivoTuristico.nombre,   		
	    		descripcion: atractivoTuristico.descripcion,
	    		latitud: atractivoTuristico.latitud,
	    		longitud: atractivoTuristico.longitud,
	    		idTurista: null,
	    		idTipoAtractivo: atractivoTuristico.taIdtipoAtractivo,
	    		idAdministrador: atractivoTuristico.aidUsuario,
	    		idAsentamiento: atractivoTuristico.aidAsentamiento
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			return $http.post(CONFIG.urlWebService + '/atractivoTuristico', data, config)
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
        
        updateAtractivoTuristico: function(atractivoTuristico)
	    {
	    	var data = $.param({
	    		idAtractivoTuristico: atractivoTuristico.idAtractivoTuristico,
	    		nombre: atractivoTuristico.nombre,   		
	    		descripcion: atractivoTuristico.descripcion,
	    		latitud: atractivoTuristico.latitud,
	    		longitud: atractivoTuristico.longitud,
	    		idTurista: null,
	    		idTipoAtractivo: atractivoTuristico.taIdtipoAtractivo,
	    		idAdministrador: atractivoTuristico.aidUsuario,
	    		idAsentamiento: atractivoTuristico.aidAsentamiento,
	    		idEstadoRegistro: atractivoTuristico.erIdEstadoRegistro
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			return $http.post(CONFIG.urlWebService + '/atractivoTuristicoEdit', data, config)
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
          
	 	  deleteAtractivoTuristico: function(id)
	 	  {
	            return $http.delete( CONFIG.urlWebService + '/atractivoTuristico/' + id)
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
	       
	       deleteFotoAtractivoTuristico: function( idfotoAtractivoTuristico)
	 	  {
	            return $http.delete( CONFIG.urlWebService + '/atractivoTuristico/eliminarFoto/' + idfotoAtractivoTuristico)
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
	       
	       insertCalificacionAtractivoTuristico: function( calificacion )
		    {
		    	var data = $.param({
		    		idAtractivo: calificacion.idAtractivo,   		
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
				
				return $http.post(CONFIG.urlWebService + '/calificacionAtractivoTuristico', data, config)
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