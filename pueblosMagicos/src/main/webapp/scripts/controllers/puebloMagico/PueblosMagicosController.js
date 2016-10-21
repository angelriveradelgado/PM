'use strict';


App.controller('InfinitePMController', ['$scope', '$http', 'PuebloMagicoService', 'CONFIG', function ($scope, $http, PuebloMagicoService, CONFIG) 
{
	$scope.estados = [];
	$scope.allEstados = [];
	$scope.end = true;
	$http.get( CONFIG.urlWebService + '/estados/pm')
	.then
	(
	    function(response)
	    {
	    	$scope.allEstados = response.data;
	    	
//	    	$scope.estados = [$scope.allEstados[0], $scope.allEstados[1]];
//	    	$scope.getAllPueblosMagicosByIdEstado($scope.estados[0].idEstado, 0);
//	    	$scope.getAllPueblosMagicosByIdEstado($scope.estados[1].idEstado, 1);
	    	
//	    
	    	$scope.end = false;
	    },  
	    function(errResponse)
	    {
	        console.error('Error while fetching estados');
	    }
	);
	
	$scope.loadMore = function()
	{
    	
    	var i;
    	var estadosLength =  $scope.estados.length;
		for(i = 0; i<3; i++)
		{
			if( $scope.estados.length <  $scope.allEstados.length )
			{
				
				$scope.estados[estadosLength + i] = $scope.allEstados[estadosLength + i]; 
				$scope.getAllPueblosMagicosByIdEstado($scope.estados[estadosLength + i].idEstado, estadosLength + i);
				//$scope.getFotoPuebloMagicoByIdPM($scope.estados[estadosLength + i].idEstado, estadosLength + i);
				
			}else
			{
				$scope.end = true;
			}
		}
	}
	
	$scope.getAllPueblosMagicosByIdEstado = function(idEstado, x)
	{
		PuebloMagicoService.getAllPueblosMagicosByIdEstado(idEstado)
		.then
		(
			function(d)
			{				
				var pms = d;	
				
				$scope.estados[x].pueblosMagicos = [];
				var i;
				var pm = {};
			    for (i in pms)
			    {
			    	pm.idPuebloMagico = pms[i].idPuebloMagico; 
			    	pm.nombre = pms[i].nombre;
			    	pm.latitud = pms[i].latitud;
			    	pm.longitud = pms[i].longitud;
			    	pm.descripcion = pms[i].descripcion;
			    	pm.epmIdestadoPuebloMagico = pms[i].epmIdestadoPuebloMagico;
			    	pm.midMunicipio = pms[i].midMunicipio;
			    	pm.foto = '';
			    	$scope.estados[x].pueblosMagicos[i] = JSON.parse(JSON.stringify(pm));
			    	
			    	if(pm.idPuebloMagico!=null)
		    		{
			    		$scope.getFotoPuebloMagicoByIdPM(pm.idPuebloMagico, x, i);
		    		}
			    	
			    }
			    
			    
			},
			function(errResponse)
			{
				console.error('Error al buscar pms');
			}
		);
		
	};
	
	
	$scope.getFotoPuebloMagicoByIdPM = function(idPM, x, i)
	{
		PuebloMagicoService.getAllFotosPuebloMagicoByIdPM(idPM)
		.then
		(
			function(d)
			{				
				var fotos = d;	
				$scope.estados[x].pueblosMagicos[i].foto= '';
				$scope.estados[x].pueblosMagicos[i].foto = CONFIG.urlWebService + '/puebloMagico/foto/' + fotos[0].idfotoPuebloMagico;
			},
			function(errResponse)
			{
				console.error('Error al buscar foto');
			}
		);
		
	};
	
	

}]);

