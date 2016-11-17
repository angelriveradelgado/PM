'use strict';

App.controller('ValidacionPST', ['$scope', '$http', 'PSTService', 'PuebloMagicoService', 'CONFIG', function ($scope, $http, PSTService, PuebloMagicoService, CONFIG) 
{
	$scope.numPSTs = 0;
	$scope.first = 0;
	$scope.numReg = 15;
	$scope.PSTs = [];
	
	$scope.end = false;
	$scope.idEstadoRegistroPendiente = 2;
	
	$scope.loadMore = function()
	{    			
		$scope.getPSTByIdEstadoRegistroByLimit($scope.idEstadoRegistroPendiente, $scope.first, $scope.numReg  );
		$scope.first = $scope.first + $scope.numReg;
	}
	
	$scope.getPSTByIdEstadoRegistroByLimit = function(idEstadoRegistro, first, num )
	{
		PSTService.getPSTByIdEstadoRegistroByLimit(idEstadoRegistro, first, num )
		.then
		(
			function(d)
			{			
				var psts = {};
				psts = d;
				if( psts.length === 0)
				{
					$scope.end = true;
					console.log('null');
				}else
				{
					Array.prototype.push.apply($scope.PSTs, JSON.parse(JSON.stringify(psts)));
					$scope.numPSTs = $scope.numPSTs + psts.length;
				}
			},
			function(errResponse)
			{
				console.error('Error al buscar foto');
			}
		);
		
	};
	
	
	
	

}]);

