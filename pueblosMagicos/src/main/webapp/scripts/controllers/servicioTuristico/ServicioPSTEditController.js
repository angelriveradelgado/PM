'use strict';

App.controller('ServicioPSTEditController', ['$document', 'FileUploadService', '$rootScope','$scope', '$routeParams', '$http', 'PuebloMagicoService', 'ServicioTuristicoService', 'EstablecimientoService', '$window', 'CONFIG', 'EstadoService', 
	function ($document, FileUploadService, $rootScope, $scope, $routeParams, $http, PuebloMagicoService, ServicioTuristicoService, EstablecimientoService, $window,  CONFIG, EstadoService) 
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
	
	$scope.servicioTuristico.idServicioTuristico = $routeParams.id;
	

	$scope.getServicioTuristicoById = function()
	{
		ServicioTuristicoService.getServicioTuristicoById( $scope.servicioTuristico.idServicioTuristico )
		.then
		(
			function(d)
			{		
				var e = d;
				$scope.servicioTuristico = JSON.parse(JSON.stringify(e));
				console.log($scope.servicioTuristico);
//				$scope.iniMap();
				$scope.getEstablecimientoById();
			},
			function(errResponse)
			{
				console.error('Error al buscar PMs');
			}
		);
	}
	
	$scope.getEstablecimientoById = function()
	{
		console.log($scope.servicioTuristico.eidEstablecimiento);
		EstablecimientoService.getEstablecimientoById( $scope.servicioTuristico.eidEstablecimiento )
		.then
		(
			function(d)
			{		
				var e = d;
				$scope.establecimiento = JSON.parse(JSON.stringify(e));
				console.log($scope.establecimiento );
				$scope.getDireccionByIdEstablecimiento();
			},
			function(errResponse)
			{
				console.error('Error al buscar PMs');
			}
		);
	}
	
	$scope.getFotos = function(idServicioTuristico)
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
					$scope.fotos[0].url = 'img/icons/turismo.png'
				}else
				{
					var i;
					for(i=0; i<$scope.fotos.length; i++)
					{
						$scope.fotos[i].url = CONFIG.urlWebService + '/servicioTuristico/foto/' + $scope.fotos[i].idfotoServicioTuristico;
					}
				}
			},
			function(errResponse)
			{
				console.error('Error al buscar fotos');
			}
		);
	};
	
	$scope.getDireccionByIdEstablecimiento = function()
	{
		EstablecimientoService.getDireccionByIdEstablecimiento( $scope.servicioTuristico.eidEstablecimiento )
		.then
		(
			function(d)
			{		
				var e = d;
				$scope.servicioTuristico.direccion = e.direccion;
				console.log($scope.servicioTuristico);
			},
			function(errResponse)
			{
				console.error('Error al buscar direccion');
			}
		);
	};
	
	$scope.getURL = function(idFotoServicioTuristico)
	{
		var url = CONFIG.urlWebService + '/servicioTuristico/foto/' + idFotoServicioTuristico;
		return url;
	}
	
	$scope.iniMap = function()
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
		//iniMap();
		google.maps.event.trigger($scope.map, 'resize');
	}
	
	 $scope.scrollTo = function (hash) 
	 {
		 $location.hash(hash);
    };
	
	
	$scope.getServicioTuristicoById();
	
	$scope.getFotos($scope.servicioTuristico.idServicioTuristico);
	console.log($scope.servicioTuristico.idServicioTuristico);
	
	
	var getTiposServiciosTuristicos = function()
	{
		ServicioTuristicoService.getTiposServiciosTuristicos()
		.then
		(
			function(d)
			{		
				var e = d;
				$scope.tiposServicio = JSON.parse(JSON.stringify(e));
			},
			function(errResponse)
			{
				console.error('Error al buscar PMs');
			}
		);
	}
	
		
	$scope.createServicioTuristico = function()
	{
		console.log($scope.servicioTuristico);
		ServicioTuristicoService.createServicioTuristico($scope.servicioTuristico);

		console.log('crear establecimiento');
	}
	
	
	getTiposServiciosTuristicos();
	$scope.iniMap();
	
	$scope.submit = function()
	{
		console.log($scope.servicioTuristico);
		//$scope.puebloMagico.idMunicipio = $scope.idMunicipio;
		$scope.createServicioTuristico($scope.servicioTuristico);
		//$window.location.href = CONFIG.urlWebService +'/#/gestion/atractivosTuristicos';
	}
	
	$scope.upload = function()
	{
		var uploadUrl = CONFIG.urlWebService +  '/servicioTuristico/' + $scope.servicioTuristico.idServicioTuristico + '/uploadFotoServicioTuristico';
	       
		FileUploadService.uploadFileToUrl($scope.myFile, uploadUrl)
		.success(function()
	   {
			$scope.fotos = [];
		    $scope.getFotos($scope.servicioTuristico.idServicioTuristico);
       })
    
       .error(function()
	   {
       });
//		.then
//		(
//			function(d)
//			{		
//				
//				$scope.fotos = [];
//			    $scope.getFotos($scope.servicioTuristico.idServicioTuristico);
//			},
//			function(errResponse)
//			{
//				console.error('Error al buscar municipios');
//			}
//		);
	}
	
	var self = this;
	$scope.setSelectedItem = function(item)
	{
		console.log(item);
		$scope.seletedItem = item;
		console.log($scope.seletedItem);
	}
	
	$scope.updateServicioTuristico = function(  )
	{
		console.log($scope.servicioTuristico);
		ServicioTuristicoService.updateServicioTuristico( $scope.servicioTuristico )
		.then
		(
			function(d)
			{		
//				location.reload(); 
				$scope.getServicioTuristicoById();
			},
			function(errResponse)
			{
				console.error('Error al actualizar atractivo');
			}
		);
	}
	
	$scope.deleteFotoServicioTuristico = function(  )
	{
		console.log($scope.seletedItem);
		ServicioTuristicoService.deleteFotoServicioTuristico( $scope.seletedItem )
		.then
		(
			function(d)
			{		
				location.reload(); 
			},
			function(errResponse)
			{
				console.error('Error al buscar municipios');
			}
		);
	}
	
	$scope.uploadFile = function()
	{
       var file = $scope.myFile;
       
       console.log('file is ' );
       console.dir(file);
       console.dir($scope.servicioTuristico.idServicioTuristico);
       
       var uploadUrl = CONFIG.urlWebService +  '/servicioTuristico/' + $scope.servicioTuristico.idServicioTuristico + '/uploadFotoServicioTuristico';
       console.dir(uploadUrl);
       FileUploadService.uploadFileToUrl(file, uploadUrl);
       
       location.reload(); 
    };
	    
}]);
