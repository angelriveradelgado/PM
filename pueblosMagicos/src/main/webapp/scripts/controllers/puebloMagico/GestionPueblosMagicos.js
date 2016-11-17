'use strict';

App.controller('GestionPueblosMagicos', ['$scope', '$http', 'EstadoService', 'PuebloMagicoService', 'CONFIG', function ($scope, $http, EstadoService, PuebloMagicoService, CONFIG) 
{
	$scope.estados = [];
	$scope.allEstados = [];
	$scope.numPueblos = 0;
	$scope.first = 0;
	$scope.numReg = 15;
	$scope.pueblosMagicos = [];
	
	$scope.end = false;
	
	$scope.loadMore = function()
	{    			
		$scope.fetchSomePueblosMagicos($scope.first, $scope.numReg  );
		$scope.first = $scope.first + $scope.numReg;
	}
	
	$scope.fetchSomePueblosMagicos = function(first, num )
	{
		PuebloMagicoService.fetchSomePueblosMagicos(first, num )
		.then
		(
			function(d)
			{			
				var pueblos = {};
				pueblos = d;
				if( pueblos.length === 0)
				{
					$scope.end = true;
					console.log('null');
				}else
				{
					Array.prototype.push.apply($scope.pueblosMagicos, JSON.parse(JSON.stringify(pueblos)));
					$scope.numPueblos = $scope.numPueblos + pueblos.length;
				}
			},
			function(errResponse)
			{
				console.error('Error al buscar foto');
			}
		);
		
	};
	
	$scope.eliminar = function( id )
	{
		console.log($scope.selectedItem);
		PuebloMagicoService.deletePuebloMagico(id)
		.then
		(
			function(d)
			{		
				$scope.pueblosMagicos = [];
				$scope.end = false;
			},
			function(errResponse)
			{
				console.error('Error al eliminar pm');
			}
		);
	}
	
	

}]);

