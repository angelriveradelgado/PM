'use strict';

App.controller('PuebloMagicoController', ['$scope', '$routeParams', '$http', 'PuebloMagicoService', '$window', 'CONFIG', function ($scope, $routeParams, $http, PuebloMagicoService, $window,  CONFIG) 
{
	console.log('desc');
	$scope.iniMap = function()
	{
		//$scope.map.control.refresh();
		google.maps.event.trigger($scope.map, 'resize');
	}
	 var createMarker = function (info)
	    {
	        
	        var marker = new google.maps.Marker(
			{
	            map: $scope.map,
	            position: new google.maps.LatLng(info.lat, info.long),
	            animation: google.maps.Animation.DROP,
	            icon: '/pueblosMagicos/img/icons/pm.jpg',
	            title: info.city
	        });
	        marker.content = '<div class="infoWindowContent">' + info.desc + '</div>';
	        
	        google.maps.event.addListener(marker, 'click', function()
			{
	            infoWindow.setContent('<h2>' + marker.title + '</h2>' + marker.content);
	            infoWindow.open($scope.map, marker);
	        });
	        
	        $scope.markers.push(marker);
	        
	    }     
	
	$scope.nombrePuebloMagico = $routeParams.nombrePuebloMagico;
	
//	var PM = {};
//	PM = PuebloMagicoService.getPuebloMagicoByNombre($scope.nombrePuebloMagico);
//	console.log(PM);
//	
//	$scope.puebloMagico.descripcion = {};
//	$scope.puebloMagico.descripcion = PM.descripcion;
//	console.log($scope.puebloMagico);
//
//	console.log($scope.puebloMagico.descripcion);
	
	

//	$scope.getFotoPuebloMagicoByIdPM = function(idPM, x, i)
//	{
		PuebloMagicoService.getPuebloMagicoByNombre($scope.nombrePuebloMagico)
		.then
		(
			function(d)
			{			
				var PM = d;
				console.log(PM);
				if(PM.nombre != $scope.nombrePuebloMagico)
				{
					console.log(PM);
					$window.location.href = CONFIG.urlWebService +'/#/';
				}
				$scope.descripcion = PM.descripcion;
				$scope.latitud = PM.latitud;
				$scope.longitud = PM.longitud;
				
				var mapOptions = 
				{
			        zoom: 8,
			        center: new google.maps.LatLng($scope.latitud, $scope.longitud),
			        mapTypeId: google.maps.MapTypeId.ROADMAP,
			        scrollwheel : false
			    }

			    $scope.map = new google.maps.Map(document.getElementById('map'), mapOptions);

			    $scope.markers = [];
			    
			    var infoWindow = new google.maps.InfoWindow();
			    
				var city = {};
				city.city = PM.nombre; 
		        city.nombre = PM.nombre;
		        city.desc = PM.descripcion;
		        city.lat = PM.latitud;
		        city.long = PM.longitud;
		        //console.log('nombre ' + i + ' valor' + pms[i].nombre);
		        //console.log('nombreObj ' + i + ' valor' + city.city);
		        //console.log('nombreLista ' + i + ' valor' + cities[i].long);
		        console.log(city);
		        createMarker(city);
				
				
			},
			function(errResponse)
			{
				console.error('Error al buscar PM');
				return null;
			}
		);
		
//	};
	
	

}]);



App.controller('PuebloMagicoHeader', ['$scope', '$routeParams', '$http', 'PuebloMagicoService', '$window', 'CONFIG', function ($scope, $routeParams, $http, PuebloMagicoService, $window,  CONFIG) 
{
	$scope.nombrePuebloMagico = $routeParams.nombrePuebloMagico;
	console.log('header');
}]);

