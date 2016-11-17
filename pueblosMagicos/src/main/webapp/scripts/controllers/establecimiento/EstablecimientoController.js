'use strict';

App.controller('EstablecimientoController', ['$document', '$rootScope','$scope', '$routeParams', '$http', 'PSTService', 'EstablecimientoService', '$window', 'CONFIG', 
	function ($document, $rootScope, $scope, $routeParams, $http, PSTService, EstablecimientoService, $window,  CONFIG) 
{
	var establecimiento = 
	{
		idEstablecimiento : null,
		pstIdUsuario: $rootScope.globals.currentUser.idUsuario,
		nombreComercial: null,   		
		rfc: null,
		nombreVialidad: null,
		numeroExterior: null,
		numeroInterior: null,
		VIdUsuario: null,
		tvIdTipoVialidad: null,
		longitud: null,
		latitud: null,
		AIdAsentamiento: null,
		erIdEstadoRegistro: null
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
	
	$scope.showEstablecimiento = function(establecimiento)
	{
		$scope.activeEstablecimiento = establecimiento;
	}
	
	$scope.eliminar = function( id )
	{
		EstablecimientoService.deleteEstablecimiento(id)
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

	getPSTByIdPST();
	getEstablecimientoByidPST();
	
}]);
