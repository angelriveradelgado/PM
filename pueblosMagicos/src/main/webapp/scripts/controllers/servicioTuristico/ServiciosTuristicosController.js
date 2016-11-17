'use strict';

App.controller('ServiciosTuristicosController', ['$scope', '$routeParams', '$http', 'ServicioTuristicoService', 'PuebloMagicoService', '$window', 'CONFIG', function ($scope, $routeParams, $http, ServicioTuristicoService, PuebloMagicoService, $window,  CONFIG) 
{
	$scope.numServicios = 0;
	$scope.end = true;
	$scope.numReg = 10;
	$scope.first = 0;
	
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
	
	
	
	var getPuebloMagicoByNombre = function() 
	{
		PuebloMagicoService.getPuebloMagicoByNombre($scope.nombrePuebloMagico)
		.then
		(
			function(d)
			{			
				var pm = d;
				
				$scope.idPuebloMagico = pm.idPuebloMagico;
				//$scope.end = false;
			},
			function(errResponse)
			{
				console.log('pmerror');
				console.error('Error al buscar PM');
				return null;
			}
		);		
	}
	
	$scope.nombrePuebloMagico = $routeParams.nombrePuebloMagico;
	getPuebloMagicoByNombre();
	

	$scope.serviciosTuristicos = [];
	
	
	$scope.getServiciosTuristicosByPMByLimit = function(idPM, first, num )
	{
		ServicioTuristicoService.getServiciosTuristicosByPMByLimit(idPM, first, num )
		.then
		(

			function(d)
			{			
				var servicios = {};
				servicios = d;
				console.log('servicios.length');
				console.log(servicios.length);
				console.log(servicios);
				if( servicios.length === 0)
				{
					$scope.end = true;
					console.log('null');
				}else
				{
					Array.prototype.push.apply($scope.serviciosTuristicos, JSON.parse(JSON.stringify(servicios)));
		
					
		 
					
					var i;
					for(i=0; i<servicios.length ; i++)
					{
						$scope.serviciosTuristicos[first + i].foto = '';
					}
					
					for(i=0; i<servicios.length; i++)
					{
						console.log('pos');
						console.log(first + i);
						var idServicioTuristico = $scope.serviciosTuristicos[first + i].idServicioTuristico;
						$scope.getFoto(idServicioTuristico, first + i);

					}
					console.log($scope.serviciosTuristicos);

					$scope.end = false;
				}
			},
			function(errResponse)
			{
				console.error('Error al buscar servicios');
				return null;
			}
		);
	};
	
	
	
	$scope.loadMore = function()
	{    
		$scope.getServiciosTuristicosByPMByLimit($scope.idPuebloMagico, $scope.first, $scope.numReg );
		$scope.first = $scope.first + $scope.numReg;
    	console.log($scope.serviciosTuristicos);
	}
	
	$scope.getFoto = function( idServicioTuristico, x )
	{
		
		var url;
		ServicioTuristicoService.getAllFotosByIdServicioTuristico( idServicioTuristico )
		.then
		(
			function(d)
			{			
				var fotos = d;
				console.log(fotos);
				if(fotos.length!=0)
				{
					url = CONFIG.urlWebService + '/servicioTuristico/foto/' + fotos[0].idfotoServicioTuristico;
					$scope.serviciosTuristicos[x].foto= '';
					$scope.serviciosTuristicos[x].foto= url;
				}else
				{
					url = 'img/icons/turismo.png';
					console.log('img alt');
					$scope.serviciosTuristicos[x].foto= url;
				}
				
				
			},
			function(errResponse)
			{
				console.error('Error al buscar fotoST');
			}
		);
	}


	
}]);