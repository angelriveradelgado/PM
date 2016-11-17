'use strict';

App.controller('PuebloMagicoController', ['$rootScope', '$scope', '$routeParams', '$http', 'TuristaService', 'PuebloMagicoService', '$window', 'CONFIG', 
	function ($rootScope, $scope, $routeParams, $http, TuristaService, PuebloMagicoService, $window,  CONFIG) 
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

		PuebloMagicoService.getPuebloMagicoByNombre($scope.nombrePuebloMagico)
		.then
		(
			function(d)
			{			
				var PM = d;
				$scope.puebloMagico = JSON.parse(JSON.stringify(PM));
				if(PM.nombre != $scope.nombrePuebloMagico)
				{
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

	     $scope.calificar = function()
	     {
	    	 if(!$rootScope.globals.currentUser)
			 {
	    		 console.log('sesion inactiva');
	    		 $scope.errorCalificar = 'error';
	    		 $scope.insertarCalificacion = 'error';
	    		 $scope.mensajeErrorCalificar = 'Inicia sesión para calificar este Pueblo Mágico';
			 }else if( $rootScope.globals.currentUser.tipoUsuarioIdtipoUsuario != 1 )
			 {
				 console.log('se puede hacer eval');
				 $scope.errorCalificar = 'error';
	    		 $scope.mensajeErrorCalificar = 'Inicia sesión como turista para calificar este Pueblo Mágico';
			 }else 
			 {
				 TuristaService.getRegistrosVisitaByIdUsuarioByIdPuebloMagico( $rootScope.globals.currentUser.idUsuario, $scope.puebloMagico.idPuebloMagico )
				.then
				(
					function(d)
					{		
						var e = d;
						var registroVisita = JSON.parse(JSON.stringify(d));
						console.log(registroVisita);
						if(registroVisita.length==0)
						{
							$scope.errorCalificar = 'error';
							$scope.insertarCalificacion = 'error';
				    		$scope.mensajeErrorCalificar = 'Descarga nuestra aplicación para dispositivos móviles y registra tu visita para poder evaluar este Pueblo Mágico';
				    		return;
						}else
						{
							console.log(registroVisita);
							$scope.calificacion.idPuebloMagico = $scope.puebloMagico.idPuebloMagico,   		
							$scope.calificacion.idUsuario = $rootScope.globals.currentUser.idUsuario,
							$scope.calificacion.idregistroVisita = registroVisita[0].idregistroVisita;
							console.log($scope.calificacion);		
							
							TuristaService.getCalificacionByIdUsuarioByIdPuebloMagico( $rootScope.globals.currentUser.idUsuario, $scope.puebloMagico.idPuebloMagico )
							.then
							(
								function(d)
								{		
									var e = d;
									var calificacion = JSON.parse(JSON.stringify(e));
									if(calificacion.length!=0)
									{
										$scope.errorCalificar = 'error';
										$scope.insertarCalificacion = 'error';
							    		$scope.mensajeErrorCalificar = 'Ya has calificado este Pueblo Mágico';
							    		return;
									}else
									{
										PuebloMagicoService.insertCalificacionPuebloMagico( $scope.calificacion )
										.then
										(
											function(d)
											{	
												$scope.calificacion.comentario='';
												$scope.calificacion.calificacion=0;
												$scope.errorCalificar = 'ok';
												$scope.insertarCalificacion = 'ok';
												$scope.mensajeCalInsertada = 'Calificación registrada correctamente';
												setTimeout(location.reload(), 3000);
												
											},
											function(errResponse)
											{
												console.error('Error al insertar calificacion');
											}
										);
									}
								},
								function(errResponse)
								{
									console.error('Error al buscar direccion');
								}
							);
							
						}
					},
					function(errResponse)
					{
						console.log('no se ha registrado visita');
					}
				);
			 }
	    		 
	     }
	
	

}]);



App.controller('PuebloMagicoHeader', ['$scope', '$routeParams', '$http', 'PuebloMagicoService', '$window', 'CONFIG', function ($scope, $routeParams, $http, PuebloMagicoService, $window,  CONFIG) 
{
	$scope.nombrePuebloMagico = $routeParams.nombrePuebloMagico;
	console.log('header');
}]);

