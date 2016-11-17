'use strict';

App.controller('ServiciosPSTController', ['$rootScope','$scope', '$routeParams', '$http', 'EstablecimientoService', 'ServicioTuristicoService', '$window', 'CONFIG', 
	function ($rootScope, $scope, $routeParams, $http, EstablecimientoService, ServicioTuristicoService, $window,  CONFIG) 
{
	var servicioTuristico = 
	{
		idServicioTuristico: null,
		EIdEstablecimiento: null,
		nombre: null,
		aforo: null,
		tstIdtSt: null,
		VIdUsuario: null,
		precioMinimo: null,   
		precioMaximo: null,
		precioMedio: null,
		descripcion: null,
		sitioWeb: null,
		erIdEstadoRegistro: null,
		telefono: null,
		extTelefono: null,
		promedio: null
	}
	
	$scope.showTable = false;
	
	var getEstablecimientoByidPST = function()
	{
		EstablecimientoService.getEstablecimientoByidPST($rootScope.globals.currentUser.idUsuario)
		.then
		(
			function(d)
			{		
				$scope.establecimientos = JSON.parse(JSON.stringify(d));
			},
			function(errResponse)
			{
				console.error('Error al buscar establecimientos');
			}
		);
	}
	
	var getPSTByIdPST = function()
	{
		PSTService.getPSTByIdPST($rootScope.globals.currentUser.idUsuario)
		.then
		(
			function(d)
			{		
				$scope.pst = JSON.parse(JSON.stringify(d));
			},
			function(errResponse)
			{
				console.error('Error al buscar establecimientos');
			}
		);
	}
	
	var servicioTuristicoAlojamiento = 
	{
		stIdServicio: null,
		toIdtipoOperacion: null,
		taIdtipoAlojamiento: null,
		tsaIdtipoServicioAlojamiento: null
	}
	
	$scope.getServicios = function(idEstablecimiento)
	{		
		ServicioTuristicoService.getAllServiciosTuristicosByEstablecimiento(idEstablecimiento)
		.then
		(
			function(d)
			{		
				$scope.servicios = JSON.parse(JSON.stringify(d));
				$scope.showTable = true;
				$scope.getEstablecimientoById(idEstablecimiento);
			},
			function(errResponse)
			{
				console.error('Error al buscar establecimientos');
			}
		);
	}
	
	$scope.getEstablecimientoById = function(id)
	{		
		EstablecimientoService.getEstablecimientoById(id)
		.then
		(
			function(d)
			{		
				$scope.establecimiento = JSON.parse(JSON.stringify(d));
			},
			function(errResponse)
			{
				console.error('Error al buscar establecimientos');
			}
		);
	}
	
	$scope.deleteServicioTuristicoAventura = function( id )
	{
		ServicioTuristicoService.deleteServicioTuristicoAventura(id)
		.then
		(
			function(d)
			{		
				$scope.establecimientos = JSON.parse(JSON.stringify(d));
				$scope.establecimientos = [];
				getEstablecimientoByidPST();
			},
			function(errResponse)
			{
				console.error('Error al buscar establecimientos');
			}
		);
	}
	
	$scope.deleteServicioTuristicoAlojamiento = function( id )
	{
		ServicioTuristicoService.deleteServicioTuristicoAlojamiento(id)
		.then
		(
			function(d)
			{		
				$scope.establecimientos = JSON.parse(JSON.stringify(d));
				$scope.establecimientos = [];
				getEstablecimientoByidPST();
			},
			function(errResponse)
			{
				console.error('Error al buscar establecimientos');
			}
		);
	}
	
	$scope.getRegistro = function(erIdEstadoRegistro)
	{
		if(erIdEstadoRegistro == 1)
		{
			return 'Aprobado';
		}else if(erIdEstadoRegistro == 2)
		{
			return 'Pendiente';
		}else if(erIdEstadoRegistro == 3)
		{
			return 'Denegado';
		}else 
		{
			return '';
		}
	}
	
	getPSTByIdPST();
	getEstablecimientoByidPST();
}]);