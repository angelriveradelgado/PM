'use strict';

App.controller('ValidacionEstablecimientos', ['$scope', '$http', 'EstablecimientoService', 'PuebloMagicoService', 'CONFIG', 
	function ($scope, $http, EstablecimientoService, PuebloMagicoService, CONFIG) 
{
	$scope.numEstablecimientos = 0;
	$scope.first = 0;
	$scope.numReg = 15;
	$scope.establecimientos = [];
	
	$scope.end = false;
	$scope.idEstadoRegistroPendiente = 2;
	
	$scope.loadMore = function()
	{    			
		$scope.getEstablecimientosByIdEstadoRegistroByLimit($scope.idEstadoRegistroPendiente, $scope.first, $scope.numReg  );
		$scope.first = $scope.first + $scope.numReg;
	}
	
	$scope.getEstablecimientosByIdEstadoRegistroByLimit = function(idEstadoRegistro, first, num )
	{
		EstablecimientoService.getEstablecimientosByIdEstadoRegistroByLimit(idEstadoRegistro, first, num )
		.then
		(
			function(d)
			{			
				var e = {};
				e = d;
				if( e.length === 0)
				{
					$scope.end = true;
				}else
				{
					Array.prototype.push.apply($scope.establecimientos, JSON.parse(JSON.stringify(e)));
					$scope.numEstablecimientos = $scope.numEstablecimientos + e.length;
				}
			},
			function(errResponse)
			{
				console.error('Error al buscar foto');
			}
		);
		
	}
	
	
	
	

}]);

