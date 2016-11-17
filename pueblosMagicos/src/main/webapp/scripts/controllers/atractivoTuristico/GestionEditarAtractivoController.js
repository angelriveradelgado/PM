'use strict';

App.controller('GestionEditarAtractivoController', ['$document', 'FileUploadService', '$rootScope','$scope', '$routeParams', '$http', 'PuebloMagicoService', 'AtractivoTuristicoService', '$window', 'CONFIG', 'EstadoService', 
	function ($document, FileUploadService, $rootScope, $scope, $routeParams, $http, PuebloMagicoService, AtractivoTuristicoService, $window,  CONFIG, EstadoService) 
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
					$scope.fotos[0].url = 'img/icons/turismo.png'
				}else
				{
					var i;
					for(i=0; i<$scope.fotos.length; i++)
					{
						$scope.fotos[i].url = CONFIG.urlWebService + '/atractivoTuristico/foto/' + $scope.fotos[i].idfotoAtractivoTuristico;
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
	
	
	$scope.getAtractivoTuristicoById();
	
	$scope.getFotos($scope.atractivoTuristico.idAtractivoTuristico);
	console.log($scope.atractivoTuristico.idAtractivoTuristico);
	$scope.getDireccionByIdAtractivoTuristico();
	
	
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

		console.log('crear establecimiento');
	}
	
	
	getTiposAtractivoTuristico();
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
	
	$scope.upload = function()
	{
		var uploadUrl = CONFIG.urlWebService +  '/atractivoTuristico/' + $scope.atractivoTuristico.idAtractivoTuristico + '/uploadFotoAtractivoTuristico';
	       
		FileUploadService.uploadFileToUrl($scope.myFile, uploadUrl)
		.success(function()
	   {
			$scope.fotos = [];
		    $scope.getFotos($scope.atractivoTuristico.idAtractivoTuristico);
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
//			    $scope.getFotos($scope.atractivoTuristico.idAtractivoTuristico);
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
	
	$scope.updateAtractivoTuristico = function(  )
	{
		console.log($scope.atractivoTuristico);
		AtractivoTuristicoService.updateAtractivoTuristico( $scope.atractivoTuristico )
		.then
		(
			function(d)
			{		
				setTimeout(location.reload(), 3000);
				$scope.getAtractivoTuristicoById();
			},
			function(errResponse)
			{
				console.error('Error al actualizar atractivo');
			}
		);
	}
	
	$scope.deleteFotoAtractivoTuristico = function(  )
	{
		console.log($scope.seletedItem);
		AtractivoTuristicoService.deleteFotoAtractivoTuristico( $scope.seletedItem )
		.then
		(
			function(d)
			{		
				setTimeout(location.reload(), 3000);
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
       console.dir($scope.atractivoTuristico.idAtractivoTuristico);
       
       var uploadUrl = CONFIG.urlWebService +  '/atractivoTuristico/' + $scope.atractivoTuristico.idAtractivoTuristico + '/uploadFotoAtractivoTuristico';
       console.dir(uploadUrl);
       FileUploadService.uploadFileToUrl(file, uploadUrl);
       
       setTimeout(location.reload(), 3000); 
    };
	    
}]);
