'use strict';

App.controller('GestionAtractivosTuristicosController', ['$rootScope','$scope', '$routeParams', '$http', 'PuebloMagicoService', 'AtractivoTuristicoService', '$window', 'CONFIG', 
	function ($rootScope, $scope, $routeParams, $http, PuebloMagicoService, AtractivoTuristicoService, $window,  CONFIG) 
{
	var atractivoTuristico = {
			idAtractivoTuristico: null,  
    		nombre: null,  
    		descripcion: null, 
    		latitud: null, 
    		longitud: null, 
    		aidUsuario: null,
    		tidUsuario: null,
    		taIdtipoAtractivo: null, 
    		idAdministrador: null, 
    		aidAsentamiento: null,
    		erIdEstadoRegistro: null, 
    		promedio: null,
    		direccion: null    		
	};
	
	$scope.showTable = false;
	
	$scope.numAtractivos = 0;
	$scope.first = 0;
	$scope.numReg = 15;
	$scope.atractivos = [];
	
	$scope.end = true;
	
	$scope.loadMore = function()
	{    			
		$scope.getAtractivosTuristicosByPuebloMagicoByLimit($scope.idPuebloMagico, $scope.first, $scope.numReg  );
		$scope.first = $scope.first + $scope.numReg;
	}
	
	$scope.iniTabla = function()
	{
		$scope.numAtractivos = 0;
		$scope.first = 0;
		$scope.atractivos = [];
		$scope.end=false;
	}
	
	var fetchAllPueblosMagicos = function()
	{
		PuebloMagicoService.fetchAllPueblosMagicos()
		.then
		(
			function(d)
			{		
				var pm = d ;
				$scope.pueblosMagicos = JSON.parse(JSON.stringify(pm));
			},
			function(errResponse)
			{
				console.error('Error al buscar establecimientos');
			}
		);
	}
	
	$scope.getAtractivosTuristicosByPuebloMagicoByLimit = function(id, first, numRegistros)
	{		
		AtractivoTuristicoService.getAtractivosTuristicosByPuebloMagicoByLimit(id, first, numRegistros)
		.then
		(
			function(d)
			{		
				var atractivos = {};
				atractivos = d;
				if( atractivos.length === 0)
				{
					$scope.end = true;
					console.log('null');
				}else
				{
					Array.prototype.push.apply($scope.atractivos, JSON.parse(JSON.stringify(atractivos)));
					$scope.numAtractivos = $scope.numAtractivos + atractivos.length;
				}
//				$scope.atractivos = JSON.parse(JSON.stringify(d));
//				console.log($scope.atractivos );
				$scope.showTable = true;
			},
			function(errResponse)
			{
				console.error('Error al buscar establecimientos');
			}
		);
	}
	
	$scope.eliminar = function( id )
	{
		console.log($scope.selectedItem);
		AtractivoTuristicoService.deleteAtractivoTuristico(id)
		.then
		(
			function(d)
			{		
				$scope.numAtractivos = 0;
				$scope.first = 0;
				$scope.atractivos = [];
				$scope.end = false;
			},
			function(errResponse)
			{
				console.error('Error al eliminar pm');
			}
		);
	}
	
	
	$scope.getRegistro = function(erIdEstadoRegistro)
	{
		if(erIdEstadoRegistro == 1)
		{
			return 'Activo';
		}else if(erIdEstadoRegistro == 2)
		{
			return 'Inactivo';
		}else 
		{
			return '';
		}
	}
	
	fetchAllPueblosMagicos();
}]);