'use strict';

App.controller('EstablecimientoRegistroController', ['$document', '$rootScope','$scope', '$routeParams', '$http', 'EstablecimientoService', '$window', 'CONFIG', 'EstadoService', 
	function ($document, $rootScope, $scope, $routeParams, $http, EstablecimientoService, $window,  CONFIG, EstadoService) 
{
	$scope.establecimiento = 
	{
		idEstablecimiento : null,
		pstIdUsuario: $rootScope.globals.currentUser.idUsuario,
		nombreComercial: null,   		
		rfc: null,
		nombreVialidad: null,
		numeroExterior: null,
		numeroInterior: null,
		VIdUsuario: null,
		tvIdTipoVialidad: null,
		longitud: null,
		latitud: null,
		AIdAsentamiento: null,
		erIdEstadoRegistro: null
	}

	
	var fetchEstadosWithPM = function()
	{
		EstadoService.fetchEstadosWithPM()
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
		EstadoService.fetchMunicipiosByIdEstadoWithPM(id)
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
	
	var fetchAsentamientosByIdMunicipio = function(id)
	{
		EstadoService.fetchAsentamientosByIdMunicipio(id)
		.then
		(
			function(d)
			{		
				$scope.asentamientos = JSON.parse(JSON.stringify(d));
			},
			function(errResponse)
			{
				console.error('Error al buscar asentamientos');
			}
		);
	}
		
	$scope.createEstablecimiento = function()
	{
		console.log($scope.establecimiento);
		EstablecimientoService.createEstablecimiento($scope.establecimiento)
		.then
		(
			function(d)
			{		
				$window.location.href = CONFIG.urlWebService +'/#/pst/establecimientos';
				
			},
			function(errResponse)
			{
				console.error('Error al buscar establecimiento');
			}
		);
		
	
			
//		.then
//		(
//			function(d)
//			{		
//				//validar
//				$window.location.href = CONFIG.urlWebService +'/#/pst/establecimientos';
//				console.log('establecimiento creado');
//			},
//			function(errResponse)
//			{
//				console.error('Error al crear asentamiento');
//			}
//		);
		console.log('crear establecimiento');
	}
	var getTiposVialidad = function()
	{
		EstablecimientoService.getTiposVialidad()
		.then
		(
			function(d)
			{		
				$scope.tiposVialidad = JSON.parse(JSON.stringify(d));
			},
			function(errResponse)
			{
				console.error('Error al crear asentamiento');
			}
		);
	}
	
	
	fetchEstadosWithPM();
	getTiposVialidad();
	var mapOptions = 
	{
        zoom: 5,
        center: new google.maps.LatLng(24.195065, -100.133534),
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        scrollwheel : false
    }
	//$scope.map = new google.maps.Map(document.getElementById('mapRegEst'), mapOptions);
	//$scope.map = new google.maps.Map( angular.element(document.querySelector('#establecimiento')) );
	$scope.map = new google.maps.Map( document.getElementById('mapRegEst'), mapOptions);
	
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
		$scope.establecimiento.latitud = marker.getPosition().lat();
		$scope.establecimiento.	longitud = marker.getPosition().lng();
		EstablecimientoService.createEstablecimiento($scope.establecimiento);
		$window.location.href = CONFIG.urlWebService +'/#/pst/establecimientos';
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
