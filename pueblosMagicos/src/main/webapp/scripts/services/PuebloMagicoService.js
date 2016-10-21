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
            return $http.post( CONFIG.urlWebService + '/puebloMagico', puebloMagico)
            .then
            (
                function(response){
                    return response.data;
                }, 
                function(errResponse){
                    console.error('Error while creating user');
                    return $q.reject(errResponse);
                }
            );
        },
	     
	    updatePuebloMagico: function(puebloMagico)
	    {
            return $http.put( CONFIG.urlWebService + '/puebloMagico', puebloMagico)
            .then
            (
                function(response)
                {
                    return response.data;
                }, 
                function(errResponse)
                {
                    console.error('Error while updating user');
                    return $q.reject(errResponse);
                }
            );
        },
     
	   deletePuebloMagico: function(id)
	   {
            return $http.delete( CONFIG.urlWebService + '/puebloMagico', id)
            .then(
                function(response)
                {
                    return response.data;
                }, 
                function(errResponse)
                {
                    console.error('Error while deleting user');
                    return $q.reject(errResponse);
                }
            );
        }
	         
	    }; 
	 
	}]);