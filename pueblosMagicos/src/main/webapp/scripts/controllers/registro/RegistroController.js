'use strict';

App.controller('RegistroController', ['$scope', '$routeParams', '$http', 'UsuarioService', '$window', 'CONFIG', function ($scope, $routeParams, $http, UsuarioService, $window,  CONFIG) 
{
	$scope.usuario = {
			nombreUsuario: "",   		
    		contrasena: "",
    		nombre: "",
    		apellidoPaterno: "",
    		apellidoMaterno: "",
    		correo: "",
    		genero: "",
    		fechaNacimiento: ""
    		
	};
	$scope.contrasenaConfirmar="";
	$scope.msgContrasena="";
	
	$scope.submit = function()
	{
		console.log($scope.usuario);
		console.log($scope.usuario.fechaNacimiento.toISOString() );
		console.log($scope.usuario.fechaNacimiento.getDate() + '-' + ($scope.usuario.fechaNacimiento.getMonth()+1) + '-' + $scope.usuario.fechaNacimiento.getFullYear());
		
		//verificar que no exista el usuario o correo
		
		
		//verificar que las contraseñas coincidan
		if($scope.usuario.contrasena == $scope.contrasenaConfirmar)
		{
			UsuarioService.createUsuarioTurista($scope.usuario);
			$scope.msgContrasena="";
			//redirigir a pantalla de confirmacion
			$window.location.href = CONFIG.urlWebService +'/#/';
		}else
		{
			$scope.msgContrasena="Las contraseñas no coinciden";
		}
		
		

	}
	
	
	
	
}]);