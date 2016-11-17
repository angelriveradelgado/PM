'use strict';

App.controller('EstablecimientoEditController', ['$document', '$rootScope','$scope', '$routeParams', '$http', 'EstablecimientoService', '$window', 'CONFIG', 'EstadoService', 
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

	var getEstablecimientoById = function(id)
	{
		EstablecimientoService.getEstablecimientoById(id)		
		.then
		(
			function(d)
			{		
				var e = d
				$scope.establecimiento = JSON.parse(JSON.stringify(e));
				$scope.latitud = $scope.establecimiento.latitud;
				$scope.longitud = $scope.establecimiento.longitud;
				console.log($scope.establecimiento);
				console.log($scope.establecimiento.latitud);
				var mapOptions = 
				{
			        zoom: 8,
			        center: new google.maps.LatLng( $scope.establecimiento.latitud,$scope.establecimiento.longitud ),
			        mapTypeId: google.maps.MapTypeId.ROADMAP,
			        scrollwheel : false
			    }
				$scope.map = new google.maps.Map( document.getElementById('map'), mapOptions);
				
				var marker = new google.maps.Marker(
				{
			        map: $scope.map,
			        position: new google.maps.LatLng( $scope.establecimiento.latitud,$scope.establecimiento.longitud ),
			        draggable:true,
			        animation: google.maps.Animation.DROP
			    });
				
			},
			function(errResponse)
			{
				console.error('Error al buscar establecimiento');
			}
		);
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
				$scope.asentamientos = JSON.parse(JSON.stringify(d));
			},
			function(errResponse)
			{
				console.error('Error al crear asentamiento');
			}
		);
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
	
	$scope.establecimiento.idEstablecimiento = $routeParams.id;
	getEstablecimientoById($scope.establecimiento.idEstablecimiento);
	fetchEstadosWithPM();
	getTiposVialidad();
	
	$scope.submit = function()
	{
		$scope.establecimiento.latitud = marker.getPosition().lat();
		$scope.establecimiento.	longitud = marker.getPosition().lng();
		EstablecimientoService.createEstablecimiento($scope.establecimiento);
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
