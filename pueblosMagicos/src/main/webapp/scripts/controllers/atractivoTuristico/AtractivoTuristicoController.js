'use strict';

App.controller('AtractivoTuristicoController', ['$rootScope', '$scope', '$routeParams', '$http', 'TuristaService', 'PuebloMagicoService', 'AtractivoTuristicoService', '$window', 'CONFIG', 
	function ($rootScope, $scope, $routeParams, $http, TuristaService, PuebloMagicoService, AtractivoTuristicoService, $window,  CONFIG) 
{
	var self = this;
	self.nombrePuebloMagico;
	$scope.numAtractivos = 0;
	$scope.end = true;
	$scope.atractivoTuristico = {
			idAtractivoTuristico: null,  
    		nombre: null,  
    		descripcion: null, 
    		latitud: null, 
    		longitud: null, 
    		aidUsuario: null,
    		tidUsuario: null,
    		taIdtipoAtractivo: null, 
    		idAdministrador: null, 
    		aidAsentamiento: null,
    		erIdEstadoRegistro: null, 
    		promedio: null,
    		direccion: null    		
	};
	
	$scope.calificacion=
	{
		idAtractivo: null,   		
		idUsuario: null,
		calificacion: null,
		comentario: null,
		idregistroVisita: null
	}
	
	$scope.calificaciones = [];
	console.log($scope.calificaciones);
	//estrellas calif
	 $scope.rate = 3;
	 $scope.max = 5;
	 $scope.isReadonly = true;
	 
	 $scope.hoveringOver = function(value) 
	 {
		 $scope.overStar = value;
		 $scope.percent = 100 * (value / $scope.max);
	 };
	 $scope.ratingStates = [
	    {stateOn: 'glyphicon-ok-sign', stateOff: 'glyphicon-ok-circle'},
	    {stateOn: 'glyphicon-star', stateOff: 'glyphicon-star-empty'},
	    {stateOn: 'glyphicon-heart', stateOff: 'glyphicon-ban-circle'},
	    {stateOn: 'glyphicon-heart'},
	    {stateOff: 'glyphicon-off'}
	  ];
	  ////////////////
	
	$scope.atractivoTuristico.idAtractivoTuristico = $routeParams.id;
	

	$scope.getAtractivoTuristicoById = function()
	{
		AtractivoTuristicoService.getAtractivoTuristicoById( $scope.atractivoTuristico.idAtractivoTuristico )
		.then
		(
			function(d)
			{	
				var e = d;
				$scope.atractivoTuristico = JSON.parse(JSON.stringify(e));
				console.log($scope.atractivoTuristico);
				$scope.getCalificacionesByIdAT();
//				iniMap();
				var mapOptions = 
				{
			        zoom: 8,
			        center: new google.maps.LatLng($scope.atractivoTuristico.latitud,$scope.atractivoTuristico.longitud),
			        mapTypeId: google.maps.MapTypeId.ROADMAP,
			        scrollwheel : false
			    }

			    $scope.map = new google.maps.Map(document.getElementById('map'), mapOptions);
				
				var marker = new google.maps.Marker(
				{
			        map: $scope.map,
			        position: new google.maps.LatLng($scope.atractivoTuristico.latitud,$scope.atractivoTuristico.longitud),
			        //icon: '/pueblosMagicos/img/icons/pm.jpg',
			        title: $scope.atractivoTuristico.nombre
			    });
			},
			function(errResponse)
			{
				console.error('Error al buscar PMs');
			}
		);
	}
	
	$scope.getFotos = function(idAtractivoTuristico)
	{
		AtractivoTuristicoService.getAllFotosAtractivoTuristicoByIdAT( idAtractivoTuristico )
		.then
		(
			function(d)
			{		
				var e = d;
				$scope.fotos = JSON.parse(JSON.stringify(e));
				if( $scope.fotos.length == 0 )
				{
					$scope.fotos[0] = 
					{
						idfotoAtractivoTuristico: ''
					}
					$scope.fotos[0].idfotoAtractivoTuristico = 'img/icons/turismo.png'
				}else
				{
					var i;
					for(i=0; i<$scope.fotos.length; i++)
					{
						$scope.fotos[i].idfotoAtractivoTuristico = CONFIG.urlWebService + '/atractivoTuristico/foto/' + $scope.fotos[i].idfotoAtractivoTuristico;
					}
				}
			},
			function(errResponse)
			{
				console.error('Error al buscar fotos');
			}
		);
	};
	
	$scope.getDireccionByIdAtractivoTuristico = function()
	{
		AtractivoTuristicoService.getDireccionByIdAtractivoTuristico( $scope.atractivoTuristico.idAtractivoTuristico )
		.then
		(
			function(d)
			{		
				var e = d;
				$scope.atractivoTuristico.direccion = e.direccion;
			},
			function(errResponse)
			{
				console.error('Error al buscar direccion');
			}
		);
	};
	
	$scope.getCalificacionesByIdAT = function()
	{
		AtractivoTuristicoService.getCalificacionesByIdAT( $scope.atractivoTuristico.idAtractivoTuristico )
		.then
		(
			function(d)
			{		
				var e = d;
				$scope.calificaciones = JSON.parse(JSON.stringify(e));
				console.log($scope.calificaciones);
			},
			function(errResponse)
			{
				console.error('Error al buscar direccion');
			}
		);
	};
	
	$scope.getURL = function(idFotoAtractivoTuristico)
	{
		var url = CONFIG.urlWebService + '/atractivoTuristico/foto/' + idFotoAtractivoTuristico;
		return url;
	}
	
	var iniMap = function()
	{
		var mapOptions = 
		{
	        zoom: 8,
	        center: new google.maps.LatLng($scope.atractivoTuristico.latitud,$scope.atractivoTuristico.longitud),
	        mapTypeId: google.maps.MapTypeId.ROADMAP,
	        scrollwheel : false
	    }

	    $scope.map = new google.maps.Map(document.getElementById('map'), mapOptions);
		
		var marker = new google.maps.Marker(
		{
	        map: $scope.map,
	        position: new google.maps.LatLng($scope.atractivoTuristico.latitud,$scope.atractivoTuristico.longitud),
	        //icon: '/pueblosMagicos/img/icons/pm.jpg',
	        title: $scope.atractivoTuristico.nombre
	    });

	}
	
	$scope.resizeMap = function()
	{
		//$scope.map.control.refresh();
		//iniMap();
		google.maps.event.trigger($scope.map, 'resize');
	}
	
	 $scope.scrollTo = function (hash) 
	 {
		 $location.hash(hash);
     };
     
     $scope.calificar = function()
     {
    	 if(!$rootScope.globals.currentUser)
		 {
    		 console.log('sesion inactiva');
    		 $scope.errorCalificar = 'error';
    		 $scope.insertarCalificacion = 'error';
    		 $scope.mensajeErrorCalificar = 'Inicia sesión para calificar este atractivo turistico';
		 }else if( $rootScope.globals.currentUser.tipoUsuarioIdtipoUsuario != 1 )
		 {
			 console.log('se puede hacer eval');
			 $scope.errorCalificar = 'error';
    		 $scope.mensajeErrorCalificar = 'Inicia sesión como turista para calificar este atractivo turistico';
		 }else 
		 {
			 TuristaService.getRegistrosVisitaByIdUsuarioByIdAtractivoTuristico( $rootScope.globals.currentUser.idUsuario, $scope.atractivoTuristico.idAtractivoTuristico )
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
			    		$scope.mensajeErrorCalificar = 'Descarga nuestra aplicación para dispositivos móviles y registra tu visita para poder evaluar este atractivo turístico';
			    		return;
					}else
					{
						console.log(registroVisita);
						$scope.calificacion.idAtractivo = $scope.atractivoTuristico.idAtractivoTuristico,   		
						$scope.calificacion.idUsuario = $rootScope.globals.currentUser.idUsuario,
						$scope.calificacion.idregistroVisita = registroVisita[0].idregistroVisita;
						console.log($scope.calificacion);		
						
						TuristaService.getCalificacionByIdUsuarioByIdAtractivoTuristico( $rootScope.globals.currentUser.idUsuario, $scope.atractivoTuristico.idAtractivoTuristico )
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
						    		$scope.mensajeErrorCalificar = 'Ya has calificado este atractivo turístico';
						    		return;
								}else
								{
									AtractivoTuristicoService.insertCalificacionAtractivoTuristico( $scope.calificacion )
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
	
	
	$scope.getAtractivoTuristicoById();
	
	$scope.getFotos($scope.atractivoTuristico.idAtractivoTuristico);
	console.log($scope.atractivoTuristico.idAtractivoTuristico);
	$scope.getDireccionByIdAtractivoTuristico();
	
}]);