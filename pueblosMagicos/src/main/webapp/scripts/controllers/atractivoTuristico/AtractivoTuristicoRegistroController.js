'use strict';

App.controller('AtractivoTuristicoRegistroController', ['$document', '$rootScope','$scope', '$routeParams', '$http', 'PuebloMagicoService', 'AtractivoTuristicoService', '$window', 'CONFIG', 'EstadoService', 
	function ($document, $rootScope, $scope, $routeParams, $http, PuebloMagicoService, AtractivoTuristicoService, $window,  CONFIG, EstadoService) 
{

	$scope.atractivoTuristico = {
			idAtractivoTuristico: null,  
    		nombre: null,  
    		descripcion: null, 
    		latitud: null, 
    		longitud: null, 
    		aidUsuario: $rootScope.globals.currentUser.idUsuario,
    		tidUsuario: null,
    		taIdtipoAtractivo: null, 
    		idAdministrador: null, 
    		aidAsentamiento: null,
    		erIdEstadoRegistro: null, 
    		promedio: null,
    		direccion: null    		
	};
	
	var getPuebloMagicoById = function(id)
	{
		PuebloMagicoService.getPuebloMagicoById(id)
		.then
		(
			function(d)
			{		
				var e = d;
				$scope.puebloMagico = JSON.parse(JSON.stringify(e));
				$scope.getAsentamientos($scope.puebloMagico.midMunicipio);
			},
			function(errResponse)
			{
				console.error('Error al buscar PMs');
			}
		);
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
				$scope.municipios = [];
				$scope.asentamientos = [];
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
				$scope.asentamientos = [];
			},
			function(errResponse)
			{
				console.error('Error al buscar municipios');
			}
		);
	}
	
	var fetchAsentamientosByIdMunicipio = function(id)
	{
		EstadoService.fetchAsentamientosByIdMunicipio(id)
		.then
		(
			function(d)
			{		
				var e = d;
				$scope.asentamientos = JSON.parse(JSON.stringify(e));
			},
			function(errResponse)
			{
				console.error('Error al buscar municipios');
			}
		);
	}
	
	var getTiposAtractivoTuristico = function()
	{
		AtractivoTuristicoService.getTiposAtractivoTuristico()
		.then
		(
			function(d)
			{		
				var e = d;
				$scope.tiposAtractivo = JSON.parse(JSON.stringify(e));
			},
			function(errResponse)
			{
				console.error('Error al buscar municipios');
			}
		);
	}
	
		
	$scope.createAtractivoTuristico = function()
	{
		console.log($scope.atractivoTuristico);
		AtractivoTuristicoService.createAtractivoTuristico($scope.atractivoTuristico);
		$window.location.href = CONFIG.urlWebService +'/#/gestion/atractivosTuristicos'
		console.log('crear establecimiento');
	}
	
	
	getTiposAtractivoTuristico();
	getPuebloMagicoById($routeParams.idPuebloMagico);
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
		$scope.atractivoTuristico.latitud = marker.getPosition().lat();
		$scope.atractivoTuristico.longitud = marker.getPosition().lng();
		console.log($scope.atractivoTuristico);
		//$scope.puebloMagico.idMunicipio = $scope.idMunicipio;
		$scope.createAtractivoTuristico($scope.atractivoTuristico);
		//$window.location.href = CONFIG.urlWebService +'/#/gestion/atractivosTuristicos';
	}
	
	$scope.getMunicipios = function(id)
	{
		console.log(id);
		fetchMunicipiosByIdEstado(id);
	}
	
	$scope.getAsentamientos = function(id)
	{
		console.log(id);
		fetchAsentamientosByIdMunicipio(id);
	}
	
}]);
