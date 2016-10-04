'use strict';

App.factory('PuebloMagicoService', ['$http', '$q', function($http, $q)
{
	return {
        
	    fetchAllPueblosMagicos: function() 
	    {
            return $http.get('http://localhost:8080/pueblosMagicos/puebloMagico')
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
            return $http.get('http://localhost:8080/pueblosMagicos/puebloMagico/' + first + '/' + max )
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
            return $http.post('http://localhost:8080/pueblosMagicos/puebloMagico', puebloMagico)
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
            return $http.put('http://localhost:8080/pueblosMagicos/puebloMagico', puebloMagico)
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
            return $http.delete('http://localhost:8080/pueblosMagicos/puebloMagico', id)
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