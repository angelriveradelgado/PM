'use strict';

App.controller('ServicioTuristicoController', ['$rootScope', '$scope', '$routeParams', '$http', 'TuristaService', 'EstablecimientoService', 'ServicioTuristicoService', 'PuebloMagicoService', '$window', 'CONFIG', 
	function ($rootScope, $scope, $routeParams, $http, TuristaService, EstablecimientoService, ServicioTuristicoService, PuebloMagicoService, $window,  CONFIG) 
{
	$scope.servicioTuristico = 
	{
		idServicioTuristico: null,
		nombre: null,
		aforo: null,
		tstIdtSt: null,
		precioMinimo: null,   
		precioMaximo: null,
		precioMedio: null,
		descripcion: null,
		sitioWeb: null,
		erIdEstadoRegistro: null,
		telefono: null,
		extensionTelefono: null,
		promedio: null,
		vidUsuario: null,
		eidEstablecimiento: null
	};
	
	$scope.establecimiento = 
	{
		idEstablecimiento : null,
		pstIdUsuario: null,
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
	
	$scope.evaluacionGenerica = 
	{
		comentario: null,
		TIdUsuario: null,
		sTIdServicioTuristico: null,
		aspectoEstablecimiento: 0,
		atencionCliente: 0,
		eficienciaServicio: 0,
		higieneEstablecimiento: 0,
		relacionPrecioCalidad: 0,
		accesibilidad: 0,
		comunicacion: 0,
		manejoIdiomas: 0,
		senalamientoInterno: 0,
		senalamientoExterno: 0
	}
	
	$scope.evaluacionAventura = 
	{
		equipamientoYMaterial: 0,
		informacionActividad: 0,
		informacionRiesgos: 0,
		condicionEquipo: 0,
		informacionRequisitos: 0,
		servicioMedico: 0,
		seguroVida: 0,
		supervision: 0,
		asistencia: 0,
		informacionReservaLugar: 0,
		acuerdoRiesgos: 0
	}
	
	$scope.evaluacionAlojamiento = 
	{
		instalacionesRecepcion: 0,
		servicioPortero: 0,
		servicioMaletero: 0,
		registroEntrada: 0,
		iluminacionHabitacion: 0,
		confortCama: 0,
		limpiezaBano: 0,
		mobiliario: 0,
		equipamientoElectronicos: 0,
		servicioLavadoPlanchado: 0
	}
	
	$scope.evaluacionesServicioTuristico = [];
	$scope.evaluacionesServicioTuristico.evaluacionAlojamiento = {};
	$scope.servicioTuristico.idServicioTuristico = $routeParams.id;
	$scope.numEvaluaciones = 0;
	$scope.first = 0;
	$scope.numReg = 15;
	$scope.evaluaciones = [];
	$scope.fotos = [];
	
	//estrellas calif
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

	$scope.getServicioTuristicoById = function(id)
	{
		ServicioTuristicoService.getServicioTuristicoById( id )
		.then
		(
			function(d)
			{		
				var e = d;
				$scope.servicioTuristico = JSON.parse(JSON.stringify(e));
				console.log($scope.servicioTuristico);
				$scope.getEstablecimientoById( $scope.servicioTuristico.eidEstablecimiento );
			},
			function(errResponse)
			{
				console.error('Error al buscar PMs');
			}
		);
	}
	
	$scope.getEstablecimientoById = function(idEstablecimiento)
	{
		EstablecimientoService.getEstablecimientoById( idEstablecimiento )
		.then
		(
			function(d)
			{		
				var e = d;
				$scope.establecimiento = JSON.parse(JSON.stringify(e));
				console.log($scope.establecimiento );
			},
			function(errResponse)
			{
				console.error('Error al buscar PMs');
			}
		);
	}
	
	$scope.getFotos = function( idServicioTuristico )
	{
		ServicioTuristicoService.getAllFotosByIdServicioTuristico( idServicioTuristico )
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
						idfotoServicioTuristico: ''
					}
					$scope.fotos[0].idfotoServicioTuristico = 'img/icons/turismo.png'
				}else
				{
					var i;
					for(i=0; i<$scope.fotos.length; i++)
					{
						$scope.fotos[i].idfotoServicioTuristico = CONFIG.urlWebService + '/servicioTuristico/foto/' + $scope.fotos[i].idfotoServicioTuristico;
					}
				}
				
				console.log($scope.fotos);
				console.log($scope.showFotos);
			},
			function(errResponse)
			{
				console.error('Error al buscar fotos');
			}
		);
	};
	
	$scope.getURL = function(idServicioTuristico)
	{
		var url = CONFIG.urlWebService + '/servicioTuristico/foto/' + idServicioTuristico;
		return url;
	}
	
	var iniMap = function()
	{
		console.log($scope.establecimiento.latitud,$scope.establecimiento.longitud);
		var mapOptions = 
		{
	        zoom: 8,
	        center: new google.maps.LatLng($scope.establecimiento.latitud,$scope.establecimiento.longitud),
	        mapTypeId: google.maps.MapTypeId.ROADMAP,
	        scrollwheel : false
	    }

	    $scope.map = new google.maps.Map(document.getElementById('map'), mapOptions);
		
		var marker = new google.maps.Marker(
		{
	        map: $scope.map,
	        position: new google.maps.LatLng($scope.establecimiento.latitud,$scope.establecimiento.longitud),
	        //icon: '/pueblosMagicos/img/icons/pm.jpg',
	        title: $scope.servicioTuristico.nombre
	    });

	}
	
	$scope.resizeMap = function()
	{
		//$scope.map.control.refresh();
		iniMap();
		//google.maps.event.trigger($scope.map, 'resize');
	}
	
	$scope.getEvaluacionesByIdServicioTuristicoAndLimit = function(idServicioTuristico, first, numReg)
	{
		ServicioTuristicoService.getEvaluacionesByIdServicioTuristicoAndLimit( idServicioTuristico, first, numReg )
		.then
		(
			function(d)
			{						
				
				var evaluaciones = {};
				evaluaciones = d;
				if( evaluaciones.length === 0)
				{
					$scope.end = true;
					console.log('null');
				}else
				{
					Array.prototype.push.apply($scope.evaluacionesServicioTuristico, JSON.parse(JSON.stringify(evaluaciones)));
					console.log($scope.evaluacionesServicioTuristico);
					$scope.numEvaluaciones = $scope.numEvaluaciones + evaluaciones.length;
					
					var i;
					for(i=0; i<$scope.evaluacionesServicioTuristico.length; i++)
					{
						
						if( $scope.servicioTuristico.tstIdtSt == 66 )
						{
							ServicioTuristicoService.getEvaluacionAventuraByIdEvaluacionAventura( $scope.evaluacionesServicioTuristico[i].idEvaluacion )
							.then
							(
								function(d)
								{		
									var e = d;
									$scope.evaluacionesServicioTuristico[i].evaluacionAventura = JSON.parse(JSON.stringify(e));
								},
								function(errResponse)
								{
									console.error('Error al buscar eval av');
								}
							);
						}else if( $scope.servicioTuristico.tstIdtSt == 6 )
						{
							$scope.getEvaluacionAlojamiento(i);
						}
					}
					
					
				}
			},
			function(errResponse)
			{
				console.error('Error al buscar eval');
			}
		);
	}
	
	$scope.getEvaluacionAlojamiento = function(x)
	{
		ServicioTuristicoService.getEvaluacionAlojamientoByIdEvaluacionAlojamiento( $scope.evaluacionesServicioTuristico[x].idEvaluacion )
		.then
		(
			function(d)
			{		
				var e = d;
				$scope.evaluacionesServicioTuristico[x].evaluacionAlojamiento = {};
				$scope.evaluacionesServicioTuristico[x].evaluacionAlojamiento = JSON.parse(JSON.stringify(e));
				console.log($scope.evaluacionesServicioTuristico[x].evaluacionAlojamiento);
			},
			function(errResponse)
			{
				console.error('Error al buscar eval al');
			}
		);
	}
	
	$scope.loadMoreEvaluaciones = function()
	{    			
		$scope.getEvaluacionesByIdServicioTuristicoAndLimit( $scope.servicioTuristico.idServicioTuristico, $scope.first, $scope.numReg  );
		$scope.first = $scope.first + $scope.numReg;
	}
	
	$scope.evaluar = function()
    {
   	 if(!$rootScope.globals.currentUser)
		 {
   		 console.log('sesion inactiva');
   		 $scope.errorCalificar = 'error';
   		 $scope.insertarCalificacion = 'error';
   		 $scope.mensajeErrorCalificar = 'Inicia sesión para calificar este servicio turistico';
		 }else if( $rootScope.globals.currentUser.tipoUsuarioIdtipoUsuario != 1 )
		 {
			 console.log('se puede hacer eval');
			 $scope.errorCalificar = 'error';
			 $scope.mensajeErrorCalificar = 'Inicia sesión como turista para calificar este servicio turistico';
		 }else 
		 {
			 TuristaService.getRegistroVisitaByIdUsuarioByIdServicioTuristico( $rootScope.globals.currentUser.idUsuario, $scope.servicioTuristico.idServicioTuristico, $scope.servicioTuristico.eidEstablecimiento )
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
			    		$scope.mensajeErrorCalificar = 'Descarga nuestra aplicación para dispositivos móviles y registra tu visita para poder evaluar este servicio turístico';
			    		return;
					}else
					{
						console.log(registroVisita);     
//						$scope.calificacion.idServicio =  $scope.servicioTuristico.idServicioTuristico,   		
//						$scope.calificacion.idUsuario = $rootScope.globals.currentUser.idUsuario,
//						$scope.calificacion.idregistroVisita = registroVisita[0].idregistroVisita;
//						console.log($scope.calificacion);	
						$scope.evaluacionGenerica.TIdUsuario = $rootScope.globals.currentUser.idUsuario;
						$scope.evaluacionGenerica.sTIdServicioTuristico = $scope.servicioTuristico.idServicioTuristico;
						
						TuristaService.getEvaluacionByIdUsuarioByIdServicioTuristico( $rootScope.globals.currentUser.idUsuario, $scope.servicioTuristico.idServicioTuristico )
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
						    		$scope.mensajeErrorCalificar = 'Ya has calificado este servicio turístico';
						    		return;
								}else
								{
									
									if( $scope.servicioTuristico.tstIdtSt == 66 )
									{
										ServicioTuristicoService.insertEvaluacionServicioTuristicoAventura( $scope.evaluacionGenerica, $scope.evaluacionAventura)
										.then
										(
											function(d)
											{	
												$scope.evaluacionGenerica.comentario='';
												$scope.errorCalificar = 'ok';
												$scope.insertarCalificacion = 'ok';
												$scope.mensajeCalInsertada = 'Evaluación registrada correctamente';
												setTimeout(location.reload(), 3000);
												
											},
											function(errResponse)
											{
												console.error('Error al insertar calificacion');
											}
										);
									}else if( $scope.servicioTuristico.tstIdtSt == 6 )
									{
										ServicioTuristicoService.insertEvaluacionServicioTuristicoAlojamiento( $scope.evaluacionGenerica, $scope.evaluacionAlojamiento)
										.then
										(
											function(d)
											{	
												$scope.evaluacionGenerica.comentario='';
												$scope.errorCalificar = 'ok';
												$scope.insertarCalificacion = 'ok';
												$scope.mensajeCalInsertada = 'Evaluación registrada correctamente';
												setTimeout(location.reload(), 3000);
												
											},
											function(errResponse)
											{
												console.error('Error al insertar calificacion');
											}
										);
									}
									
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
	
	$scope.getServicioTuristicoById($scope.servicioTuristico.idServicioTuristico);
	
	console.log($scope.servicioTuristico);
	$scope.getFotos($scope.servicioTuristico.idServicioTuristico);
	$scope.loadMoreEvaluaciones();
	
}]);