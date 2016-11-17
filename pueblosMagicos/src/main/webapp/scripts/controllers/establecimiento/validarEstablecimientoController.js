'use strict';

App.controller('ValidarEstablecimientoController', ['$rootScope', '$scope', '$routeParams', '$http', '$window', 'EstablecimientoService', 'PuebloMagicoService', 'CONFIG', 'MailService', 'UsuarioService',
	function ($rootScope, $scope, $routeParams, $http, $window, EstablecimientoService, PuebloMagicoService, CONFIG, MailService, UsuarioService) 
{
	$scope.establecimiento =
	{
			idEstablecimiento: null,
    		pstIdUsuario: null,
    		nombreComercial: null,   		
    		rfc: null,
    		nombreVialidad: null,
    		numeroExterior: null,
    		numeroInterior: null,
    		VIdUsuario: null,
    		tvIdTipoVialidad: null,
    		longitud: null,
    		latitud: null,
    		aidAsentamiento: null,
    		erIdEstadoRegistro: null
	}

	
	$scope.getEstablecimientoById = function( )
	{
		EstablecimientoService.getEstablecimientoById( $routeParams.idEstablecimiento )
		.then
		(
			function(d)
			{			
				var p = d;				
				$scope.establecimiento = JSON.parse(JSON.stringify(p));
				console.log($scope.establecimiento);
				$scope.getUsuarioById ($scope.establecimiento.pstIdUsuario);
			},
			function(errResponse)
			{
				console.error('Error al buscar foto');
			}
		);
		
	};
	
		
	$scope.updateEstablecimiento = function( )
	{
		EstablecimientoService.updateEstablecimiento( $scope.establecimiento )
		.then
		(
			function(d)
			{			
				$scope.showMensaje = 'ok';
				$scope.mensaje = 'El establecimiento se ha validado exitosamente';
				
				$scope.enviarCorreo();
				setTimeout(redirigir(), 3000);
			},
			function(errResponse)
			{
				console.error('Error al buscar foto');
			}
		);
		
	};
	
	$scope.getUsuarioById = function( id)
	{
		UsuarioService.getUsuarioById( id )
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
	
	$scope.enviarCorreo = function( )
	{
		MailService.enviarCorreo( $scope.usuario.correo, 'Solicitud establecimiento', $scope.observaciones )
		.then
		(
			function(d)
			{			
				
				
			},
			function(errResponse)
			{
				console.error('Error al mandar correo');
				setTimeout(redirigir(), 3000);
			}
		);
		
	};
	
	
	$scope.validar = function()
	{
		$scope.updateEstablecimiento(); 
		
		
	}
	
	var redirigir = function()
	{
		$window.location.href = CONFIG.urlWebService +'/#/validacion/establecimientos'
	}
	
	$scope.getEstablecimientoById();

}]);

