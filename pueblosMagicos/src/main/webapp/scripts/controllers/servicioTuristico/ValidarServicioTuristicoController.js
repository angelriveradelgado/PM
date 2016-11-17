'use strict';

App.controller('ValidarServicioTuristicoController', ['$rootScope', '$scope', '$routeParams', '$http', '$window', 'ServicioTuristicoService', 'EstablecimientoService', 'PuebloMagicoService', 'CONFIG', 'MailService', 'UsuarioService',
	function ($rootScope, $scope, $routeParams, $http, $window, ServicioTuristicoService, EstablecimientoService, PuebloMagicoService, CONFIG, MailService, UsuarioService) 
{


	$scope.getServicioTuristicoById = function( )
	{
		ServicioTuristicoService.getServicioTuristicoById( $routeParams.idServicioTuristico )
		.then
		(
			function(d)
			{			
				var p = d;				
				$scope.servicio = JSON.parse(JSON.stringify(p));
				console.log($scope.servicio);
				$scope.getEstablecimientoById($scope.servicio.eidEstablecimiento);
			},
			function(errResponse)
			{
				console.error('Error al buscar foto');
			}
		);
		
	};
	
	$scope.getEstablecimientoById = function( id )
	{
		EstablecimientoService.getEstablecimientoById( id )
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
	
		
	$scope.updateServicioTuristico = function( )
	{
		ServicioTuristicoService.updateServicioTuristico( $scope.servicio )
		.then
		(
			function(d)
			{			
				$scope.showMensaje = 'ok';
				$scope.mensaje = 'El servicio turístico se ha validado exitosamente';
				
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
		MailService.enviarCorreo( $scope.usuario.correo, 'Solicitud servicio turístico', $scope.observaciones )
		.then
		(
			function(d)
			{			
				
				
			},
			function(errResponse)
			{
				console.error('Error al mandar correo');
			}
		);
		
	};
	
	
	$scope.validar = function()
	{
		$scope.updateServicioTuristico(); 
		
		
	}
	
	var redirigir = function()
	{
		$window.location.href = CONFIG.urlWebService +'/#/validacion/serviciosTuristicos'
	}
	
	$scope.getServicioTuristicoById();

}]);

