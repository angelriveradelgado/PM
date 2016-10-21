'use strict';

App.factory('UsuarioService', ['$http', '$q', 'CONFIG',  function($http, $q, CONFIG)
{
		return {
	        
	        
	        getUsuarioByNombre: function(nombre) 
	        {
	            return $http.get( CONFIG.urlWebService + '/usuario/nombre/' + nombre )
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
	        
		     
		    createUsuarioTurista: function(usuario)
		    {
		    	var fecha = ("0" + usuario.fechaNacimiento.getDate()).slice(-2) + '-' + (usuario.fechaNacimiento.getMonth()+1) + '-' + usuario.fechaNacimiento.getFullYear();
		    	console.log(fecha);
		    	console.log(usuario);
		    	var data = $.param({
		    		nombreUsuario: usuario.nombreUsuario,   		
		    		contrasena: usuario.contrasena,
		    		nombre: usuario.nombre,
		    		apellidoPaterno: usuario.apellidoPaterno,
		    		apellidoMaterno: usuario.apellidoMaterno,
		    		correo: usuario.correo,
		    		fechaNacimiento: fecha,
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
				
				$http.post(CONFIG.urlWebService + '/turista', data, config)
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
	        },
	     
		   deleteUsuarioTurista: function(usuario)
		   {
			   var data = $.param({
		    		idUsuario: usuario.idUsuario
		    	});
			   console.log(data);
				var config = 
				{
		            headers : 
		            {
		                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
		            }
		        }
				
				$http.delete(CONFIG.urlWebService + '/turista', data, config)
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
	        
	        createUsuarioPST: function(usuario)
		    {
	        	console.log('en');
		    	console.log(usuario);
		    	var data = $.param({
		    		nombreUsuario: usuario.nombreUsuario,   		
		    		contrasena: usuario.contrasena,
		    		nombre: usuario.nombre,
		    		apellidoPaterno: usuario.apellidoPaterno,
		    		apellidoMaterno: usuario.apellidoMaterno,
		    		correo: usuario.correo,
		    		numeroRNT: usuario.numeroRNT,
		    		telefono: usuario.telefono,
		    		razonSocialEmpresa: usuario.razonSocialEmpresa
		    	});
		    	console.log(data);
				var config = 
				{
		            headers : 
		            {
		                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
		            }
		        }
				
				$http.post(CONFIG.urlWebService + '/pst', data, config)
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