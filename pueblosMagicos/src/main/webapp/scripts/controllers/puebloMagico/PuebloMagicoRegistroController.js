'use strict';

App.controller('PuebloMagicoRegistroController', ['$document', '$rootScope','$scope', '$routeParams', '$http', 'PuebloMagicoService', '$window', 'CONFIG', 'EstadoService', 
	function ($document, $rootScope, $scope, $routeParams, $http, PuebloMagicoService, $window,  CONFIG, EstadoService) 
{

	$scope.puebloMagico =
	{
		idPuebloMagico: null,
		nombre: null,   		
		latitud: null,
		longitud: null,
		descripcion: null,
		idEstadoPubloMagico: null,
		idMunicipio: null
	}
	
	var fetchEstados = function()
	{
		EstadoService.fetchEstados()
		.then
		(
			function(d)
			{		
				var e = d;
				$scope.estados = JSON.parse(JSON.stringify(e));
				console.log($scope.estados[0].nombreEstado);
			},
			function(errResponse)
			{
				console.error('Error al buscar PMs');
			}
		);
	}
	
	var fetchMunicipiosByIdEstado = function(id)
	{
		EstadoService.fetchMunicipiosByIdEstado(id)
		.then
		(
			function(d)
			{		
				var e = d;
				$scope.municipios = JSON.parse(JSON.stringify(e));
			},
			function(errResponse)
			{
				console.error('Error al buscar municipios');
			}
		);
	}
	
		
	$scope.createPuebloMagico = function()
	{
		console.log($scope.puebloMagico);
		PuebloMagicoService.createPuebloMagico($scope.puebloMagico);

		console.log('crear establecimiento');
	}
	
	$scope.createPuebloMagico3 = function()
	{
		console.log($scope.puebloMagico);
		PuebloMagicoService.createPuebloMagico($scope.puebloMagico)	
		.success
		(
			function(d)
			{
				$window.location.href = CONFIG.urlWebService +'/#/gestion/pueblosMagicos';
				console.log('PM creado');
			}
		)
		.error
		(
			function(d)
			{
				console.error('Error al crear asentamiento');
			}
		);
		
		console.log('crear PM');
	}
	
	
	fetchEstados();
	var mapOptions = 
	{
        zoom: 5,
        center: new google.maps.LatLng(24.195065, -100.133534),
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        scrollwheel : false
    }
	$scope.map = new google.maps.Map( document.getElementById('map'), mapOptions);
	
	var marker = new google.maps.Marker(
	{
        map: $scope.map,
        position: new google.maps.LatLng(24.195065, -100.133534),
        draggable:true,
        //icon: '/pueblosMagicos/img/icons/pm.jpg',
        title: 'mueve el marcador'
    });
	
	$scope.submit = function()
	{
		$scope.puebloMagico.latitud = marker.getPosition().lat();
		$scope.puebloMagico.longitud = marker.getPosition().lng();
		console.log($scope.puebloMagico);
		//$scope.puebloMagico.idMunicipio = $scope.idMunicipio;
		$scope.createPuebloMagico($scope.puebloMagico);
		//$window.location.href = CONFIG.urlWebService +'/#/gestion/pueblosMagicos';
	}
	
	$scope.getMunicipios = function(id)
	{
		console.log(id);
		fetchMunicipiosByIdEstado(id);
	}
}]);
