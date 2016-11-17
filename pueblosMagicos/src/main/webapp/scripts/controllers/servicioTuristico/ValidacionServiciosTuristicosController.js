'use strict';

App.controller('ValidacionServiciosTuristicosController', ['$scope', '$http', 'PSTService', 'ServicioTuristicoService', 'PuebloMagicoService', 'CONFIG', 
	function ($scope, $http, PSTService, ServicioTuristicoService, PuebloMagicoService, CONFIG) 
{
	$scope.numST = 0;
	$scope.first = 0;
	$scope.numReg = 15;
	$scope.servicios = [];
	
	$scope.end = false;
	$scope.idEstadoRegistroPendiente = 2;
	
	$scope.loadMore = function()
	{    			
		$scope.getServiciosTuristicosByIdEstadoRegistroByLimit($scope.idEstadoRegistroPendiente, $scope.first, $scope.numReg  );
		$scope.first = $scope.first + $scope.numReg;
	}
	
	$scope.getServiciosTuristicosByIdEstadoRegistroByLimit = function(idEstadoRegistro, first, num )
	{
		ServicioTuristicoService.getServiciosTuristicosByIdEstadoRegistroByLimit(idEstadoRegistro, first, num )
		.then
		(
			function(d)
			{			
				var s = {};
				s = d;
				if( s.length === 0)
				{
					$scope.end = true;
					console.log('null');
				}else
				{
					Array.prototype.push.apply($scope.servicios, JSON.parse(JSON.stringify(s)));
					$scope.numST = $scope.numST + s.length;
				}
			},
			function(errResponse)
			{
				console.error('Error al buscar foto');
			}
		);
		
	};
	
	
	
	

}]);

