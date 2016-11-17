'use strict';

App.controller('EstablecimientoDetallesController', ['$document', '$rootScope','$scope', '$routeParams', '$http', 'EstablecimientoService', '$window', 'CONFIG', 'EstadoService', 
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
			        center: new google.maps.LatLng($scope.establecimiento.latitud,$scope.establecimiento.longitud),
			        mapTypeId: google.maps.MapTypeId.ROADMAP,
			        scrollwheel : false
			    }
				$scope.map = new google.maps.Map( document.getElementById('map'), mapOptions);
				
				var marker = new google.maps.Marker(
				{
			        map: $scope.map,
			        position: new google.maps.LatLng($scope.establecimiento.latitud,$scope.establecimiento.longitud),
			        animation: google.maps.Animation.DROP
			    });
				
			},
			function(errResponse)
			{
				console.error('Error al buscar establecimiento');
			}
		);
	}
	
	
	///////////////script
	$scope.establecimiento.idEstablecimiento = $routeParams.id;
	getEstablecimientoById($scope.establecimiento.idEstablecimiento);
	
	
}]);