'use strict';

App.factory('TuristaService', ['$http', '$q', 'CONFIG',  function($http, $q, CONFIG)
{
		return {
	        
	        
			getRegistrosVisitaByIdUsuarioByIdAtractivoTuristico: function(idUsuario, idAtractivoTuristico) 
	        {
	            return $http.get( CONFIG.urlWebService + '/turista/' + idUsuario + '/registroVisitaAtractivoTuristico/' + idAtractivoTuristico )
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
	        
	        getCalificacionByIdUsuarioByIdAtractivoTuristico: function(idUsuario, idAtractivoTuristico) 
	        {
	            return $http.get( CONFIG.urlWebService + '/turista/' + idUsuario + '/calificacionAtractivoTuristico/' + idAtractivoTuristico )
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
	        
	        getRegistrosVisitaByIdUsuarioByIdPuebloMagico: function(idUsuario, idPuebloMagico) 
	        {
	            return $http.get( CONFIG.urlWebService + '/turista/' + idUsuario + '/registroVisitaPuebloMagico/' + idPuebloMagico )
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
	        
	        getCalificacionByIdUsuarioByIdPuebloMagico: function(idUsuario, idPM) 
	        {
	            return $http.get( CONFIG.urlWebService + '/turista/' + idUsuario + '/calificacionPuebloMagico/' + idPM )
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
	        
	        getRegistroVisitaByIdUsuarioByIdServicioTuristico: function(idUsuario, idServicioTuristico, idEstablecimiento) 
	        {
	            return $http.get( CONFIG.urlWebService + '/turista/' + idUsuario + '/registroVisitaServicioTuristico/' + idServicioTuristico + '/' + idEstablecimiento )
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
	        
	        getEvaluacionByIdUsuarioByIdServicioTuristico: function(idUsuario, idServicioTuristico) 
	        {
	            return $http.get( CONFIG.urlWebService + '/turista/' + idUsuario + '/evaluacionServicioTuristico/' + idServicioTuristico )
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
	        
	        getRegistrosVisitaNoEvaluadosByIdUsuario: function(idUsuario) 
	        {
	            return $http.get( CONFIG.urlWebService + '/turista/' + idUsuario + '/registroVisitaNoEvaluados' )
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
	        
	        getRegistrosVisitaEvaluadosByIdUsuario: function(idUsuario) 
	        {
	            return $http.get( CONFIG.urlWebService + '/turista/' + idUsuario + '/registroVisitaEvaluados' )
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
	        }
	        
		         
    }; 
 
}]);