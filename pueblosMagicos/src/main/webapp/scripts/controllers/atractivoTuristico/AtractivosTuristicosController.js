'use strict';

App.controller('AtractivosTuristicosController', ['$scope', '$routeParams', '$http', 'PuebloMagicoService', 'AtractivoTuristicoService', '$window', 'CONFIG', function ($scope, $routeParams, $http, PuebloMagicoService, AtractivoTuristicoService, $window,  CONFIG) 
{
	$scope.atractivosTuristicos = [];
	console.log('atractivo');
	var self = this;
	self.nombrePuebloMagico;
	$scope.numAtractivos = 0;
	$scope.end = true;
	$scope.numReg = 10;
	$scope.first = 0;
	
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
    		foto: null
    		
	};
	
	
	
	var getPuebloMagicoByNombre = function() 
	{
		PuebloMagicoService.getPuebloMagicoByNombre($scope.nombrePuebloMagico)
		.then
		(
			function(d)
			{			
				var pm = d;
				
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
	}
	
	

	$scope.nombrePuebloMagico = $routeParams.nombrePuebloMagico;
	getPuebloMagicoByNombre();

	
	
	
	
	$scope.getAtractivosTuristicos = function(idPM, first, num )
	{
		AtractivoTuristicoService.getAtractivosTuristicosByPuebloMagicoByLimit(idPM, first, num )
		.then
		(

			function(d)
			{			
				var atractivos = {};
				atractivos = d;
				console.log('atractivos.length');
				console.log(atractivos.length);
				console.log(atractivos);
				if( atractivos.length === 0)
				{
					$scope.end = true;
					console.log('null');
				}else
				{
					Array.prototype.push.apply($scope.atractivosTuristicos, JSON.parse(JSON.stringify(atractivos)));
					$scope.numAtractivos = $scope.numAtractivos + atractivos.length;
		 
					
					console.log('num');
					console.log($scope.numAtractivos);

					console.log('first');
					console.log(first);
					var i;
					for(i=0; i<num && (first + i) < $scope.numAtractivos ; i++)
					{
						$scope.atractivosTuristicos[first + i].foto = '';
					}
					
					for(i=0; i<num && (first + i) < $scope.numAtractivos; i++)
					{
						console.log('pos');
						console.log(first + i);
						var idAtractivoTuristico = $scope.atractivosTuristicos[first + i].idAtractivoTuristico;
						$scope.getFoto(idAtractivoTuristico, first + i);

					}
					console.log($scope.atractivosTuristicos);
					var atractivo = {};
					var i;

					$scope.end = false;
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
		$scope.getAtractivosTuristicos($scope.idPuebloMagico, $scope.first, $scope.numReg  );
		$scope.first = $scope.first + $scope.numReg;
	}
	
	$scope.getFoto = function( idAtractivoTuristico, x )
	{
//		var image = CONFIG.urlWebService + '/atractivoTuristico/foto/' + idAtractivoTuristico;
//		return image;
		
		var url;
		AtractivoTuristicoService.getAllFotosAtractivoTuristicoByIdAT( idAtractivoTuristico )
		.then
		(
			function(d)
			{			
				var fotos = d;
				
				if(fotos.length!=0)
				{
					url = CONFIG.urlWebService + '/atractivoTuristico/foto/' + fotos[0].idfotoAtractivoTuristico;
					console.log(url);
					console.log('pos');
					console.log(x);
					console.log($scope.atractivosTuristicos);
					$scope.atractivosTuristicos[x].foto= '';
					$scope.atractivosTuristicos[x].foto= url;
				}else
				{
					url = 'img/icons/turismo.png';
					console.log('img alt');
					$scope.atractivosTuristicos[x].foto = url;
				}
				
			},
			function(errResponse)
			{
				console.error('Error al buscar fotoAT');
			}
		);
	}
	
	$scope.getImage = function( idAtractivoTuristico )
	{

		var foto = $scope.getFoto( idAtractivoTuristico );
		console.log('foto');
		console.log(foto);
		
		return foto;
		
	};
	
	
	
	
}]);