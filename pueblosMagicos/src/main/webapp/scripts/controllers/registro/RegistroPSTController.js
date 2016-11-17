'use strict';

App.controller('RegistroPSTController', ['$scope', '$routeParams', '$http', 'UsuarioService', '$window', 'CONFIG', function ($scope, $routeParams, $http, UsuarioService, $window,  CONFIG) 
{
	$scope.usuario = {
			nombreUsuario: "",   		
    		contrasena: "",
    		nombre: "",
    		apellidoPaterno: "",
    		apellidoMaterno: "",
    		correo: "",
    		numeroRNT: "",
    		telefono: "",
    		razonSocialEmpresa: ""
    		
	};
	$scope.contrasenaConfirmar="";
	$scope.msgContrasena="";
	
	$scope.submit = function()
	{
		console.log($scope.usuario);
		console.log($scope.contrasenaConfirmar);
		console.log($scope.contrasenaConfirmar);
		
		//verificar que no exista el usuario o correo
		
		
		//verificar que las contraseñas coincidan
		if($scope.usuario.contrasena == $scope.contrasenaConfirmar)
		{
			console.log('antes');
			UsuarioService.createUsuarioPST($scope.usuario);
			console.log('despues');
			$scope.msgContrasena="";
			//redirigir a pantalla de confirmacion
			$window.location.href = CONFIG.urlWebService +'/#/';
		}else
		{
			$scope.msgContrasena="Las contraseñas no coinciden";
		}
		
		

	}
	
	
	
	
}]);