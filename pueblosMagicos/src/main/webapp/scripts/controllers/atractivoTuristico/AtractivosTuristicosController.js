'use strict';

App.controller('AtractivosTuristicosController', ['$scope', '$routeParams', '$http', 'PuebloMagicoService', 'AtractivoTuristicoService', '$window', 'CONFIG', function ($scope, $routeParams, $http, PuebloMagicoService, AtractivoTuristicoService, $window,  CONFIG) 
{
	console.log('atractivo');
	var self = this;
	self.nombrePuebloMagico;
	$scope.numAtractivos = 0;
	$scope.end = true;
	var atractivoTuristico = {
			idAtractivoTuristico: null,  
    		nombre: null,  
    		descripcion: null, 
    		latitud: null, 
    		longitud: null, 
    		idTurista: null, 
    		idTipoAtractivo: null, 
    		idAdministrador: null, 
    		idAsentamiento: null,
    		idEstadoRegistro: null, 
    		promedio: null,
    		imagen: null
    		
	};
	
	$scope.nombrePuebloMagico = $routeParams.nombrePuebloMagico;
	console.log($scope.nombrePuebloMagico);
	

	PuebloMagicoService.getPuebloMagicoByNombre($scope.nombrePuebloMagico)
	.then
	(
		function(d)
		{			
			var pm = d;
			console.log('pm');
			console.log(pm);
			
			$scope.idPuebloMagico = pm.idPuebloMagico;
			$scope.end = false;
		},
		function(errResponse)
		{
			console.log('pmerror');
			console.error('Error al buscar PM');
			return null;
		}
	);
	

	
	
//	var PM = PuebloMagicoService.getPuebloMagicoByNombre($scope.nombrePuebloMagico);
//	$scope.idPuebloMagico = PM.idPuebloMagico;
//	console.log(PM);
	console.log('id');
	console.log($scope.idPuebloMagico);
	
	$scope.atractivosTuristicos = [];
	
	
	$scope.getAtractivosTuristicos = function(idPM, first, num )
	{
		AtractivoTuristicoService.getAtractivosTuristicosByPuebloMagicoByLimit(idPM, first, num )
		.then
		(
			function(d)
			{			
				var atractivos = d;
				console.log('d');
				console.log(d);
				if( atractivos.length === 0)
				{
					$scope.end = true;
					console.log('null');
				}else
				{

					//$scope.atractivosTuristicos.push( JSON.parse(JSON.stringify(d)) );
					Array.prototype.push.apply($scope.atractivosTuristicos, JSON.parse(JSON.stringify(d)));
					var atractivo = {};
					var i;
//				    for (i in atractivos)
//				    {
//				    	console.log('i');
//						console.log(i);
//				    	atractivo.idAtractivoTuristico = atractivos[i].idAtractivoTuristico; 
//				    	atractivo.nombre = atractivos[i].nombre; 
//				    	atractivo.descripcion = atractivos[i].descripcion; 
//				    	atractivo.latitud = atractivos[i].latitud; 
//				    	atractivo.longitud = atractivos[i].longitud; 
//				    	atractivo.taIdtipoAtractivo = atractivos[i].taIdtipoAtractivo; 
//				    	atractivo.erIdEstadoRegistro = atractivos[i].erIdEstadoRegistro; 
//				    	atractivo.promedio = atractivos[i].promedio; 
//				    	atractivo.tidUsuario = atractivos[i].tidUsuario; 
//				    	atractivo.aidUsuario = atractivos[i].aidUsuario; 
//				    	
//				    	console.log('atractivo');
//						console.log(atractivo);
//						if( JSON.parse(JSON.stringify(atractivo)) != undefined )
//						{
//							$scope.atractivosTuristicos[$scope.atractivosTuristicos.length] = JSON.parse(JSON.stringify(atractivo));
//						
//						}
//						//$scope.atractivosTuristicos[$scope.atractivosTuristicos.length] = angular.copy(atractivo);
//				    	console.log('insertatractivos');
//						console.log($scope.atractivosTuristicos);
////				    	if(pm.idPuebloMagico!=null)
////			    		{
////				    		$scope.getFotoPuebloMagicoByIdPM(pm.idPuebloMagico, x, i);
////			    		}
//				    	
//				    	
//				    }
				}
			},
			function(errResponse)
			{
				console.log('pmerror');
				console.error('Error al buscar PM');
				return null;
			}
		);
	};
	
	
	
	$scope.loadMore = function()
	{    	
		console.log('atractivosscope');
		console.log($scope.atractivosTuristicos)	
		console.log('params');
		console.log($scope.idPuebloMagico);
		console.log('numatractivos');
		console.log($scope.numAtractivos);
		console.log('1');
		
		
		
		$scope.getAtractivosTuristicos($scope.idPuebloMagico, $scope.numAtractivos, 1 );
		
    	//var atractivos = AtractivoTuristicoService.getAtractivosTuristicosByPuebloMagicoByLimit($scope.idPuebloMagico, $scope.numAtractivos, 1 );
    	
    	
    	
    	
//    	console.log('atractivos');
//    	console.log(atractivos);
		
		$scope.numAtractivos++;
	}
	
	$scope.getImage = function(idAtractivoTuristico)
	{
		console.log('foto');
		console.log(CONFIG.urlWebService + '/atractivoTuristico/foto/' + idAtractivoTuristico);
		var image = CONFIG.urlWebService + '/atractivoTuristico/foto/' + idAtractivoTuristico;
		return image;
	};
	
	
}]);