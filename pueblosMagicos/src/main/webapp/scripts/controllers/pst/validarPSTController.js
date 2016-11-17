'use strict';

App.controller('ValidarPST', ['$rootScope', '$scope', '$routeParams', '$http', '$window', 'PSTService', 'PuebloMagicoService', 'CONFIG', 'MailService', 'UsuarioService',
	function ($rootScope, $scope, $routeParams, $http, $window, PSTService, PuebloMagicoService, CONFIG, MailService, UsuarioService) 
{
	$scope.numPSTs = 0;
	$scope.first = 0;
	$scope.numReg = 15;
	$scope.PSTs = [];
	
	$scope.end = false;
	$scope.idEstadoRegistroPendiente = 2;
	$scope.pst =
	{
		idUsuario: null,
		erIdEstadoRegistro: null,
		numeroRnt: null,
		telefono: null,
		razonSocialEmpresa: null
	}

	
	$scope.getPSTByIdPST = function( )
	{
		PSTService.getPSTByIdPST( $routeParams.idPST )
		.then
		(
			function(d)
			{			
				var p = d;				
				$scope.pst = JSON.parse(JSON.stringify(p));
				console.log($scope.pst);
				$scope.getUsuarioById();
			},
			function(errResponse)
			{
				console.error('Error al buscar foto');
			}
		);
		
	};
	
	$scope.getUsuarioById = function( )
	{
		UsuarioService.getUsuarioById( $scope.pst.idUsuario )
		.then
		(
			function(d)
			{			
				var p = d;				
				$scope.usuario = JSON.parse(JSON.stringify(p));
				console.log($scope.usuario);
			},
			function(errResponse)
			{
				console.error('Error al buscar foto');
			}
		);
		
	};
	
	$scope.updatePST = function( )
	{
		PSTService.updatePST( $scope.pst )
		.then
		(
			function(d)
			{			
				var p = d;				
				$scope.pst = JSON.parse(JSON.stringify(p));
				$scope.showMensaje = 'ok';
				$scope.mensaje = 'El PST se ha validado exitosamente';
				$scope.enviarCorreo();
				setTimeout(redirigir(), 3000);
			},
			function(errResponse)
			{
				console.error('Error al buscar foto');
			}
		);
		
	};
	
	$scope.enviarCorreo = function( )
	{
		MailService.enviarCorreo( $scope.usuario.correo, 'Solicitud PST', $scope.observaciones )
		.then
		(
			function(d)
			{			
				
				
			},
			function(errResponse)
			{
				console.error('Error al buscar foto');
			}
		);
		
	};
	
	
	$scope.validar = function()
	{
		$scope.updatePST(); 
		
		
	}
	
	var redirigir = function()
	{
		$window.location.href = CONFIG.urlWebService +'/#/validacion/prestadores'
	}
	
	$scope.getPSTByIdPST();

}]);

