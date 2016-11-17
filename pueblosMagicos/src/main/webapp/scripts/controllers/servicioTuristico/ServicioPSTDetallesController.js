'use strict';

App.controller('ServicioPSTDetallesController', ['$scope', '$routeParams', '$http', 'ServicioTuristicoService', '$window', 'CONFIG', function ($scope, $routeParams, $http, ServicioTuristicoService, $window,  CONFIG) 
{
	$scope.servicioTuristico = 
	{
		idServicioTuristico: null,
		EIdEstablecimiento: $routeParams.idEstablecimiento,
		nombre: null,
		aforo: null,
		tstIdtSt: null,
		VIdUsuario: null,
		precioMinimo: null,   
		precioMaximo: null,
		precioMedio: null,
		descripcion: null,
		sitioWeb: null,
		erIdEstadoRegistro: null,
		telefono: null,
		extTelefono: null,
		promedio: null
	}
	
	var servicioTuristicoAlojamiento = 
	{
		stIdServicio: null,
		toIdtipoOperacion: null,
		taIdtipoAlojamiento: null,
		tsaIdtipoServicioAlojamiento: null
	}
	
	var getServicioTuristicoById = function()
	{
		ServicioTuristicoService.getTiposServiciosTuristicos()
		.then
		(
			function(d)
			{		
				var e = d;
				$scope.servicioTuristico = JSON.parse(JSON.stringify(e));
			},
			function(errResponse)
			{
				console.error('Error al buscar PMs');
			}
		);
	}
	
	
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
		ServicioTuristicoService.createServicioTuristico($scope.servicioTuristico)		
		.then
		(
			function(d)
			{		
				//validar
			},
			function(errResponse)
			{
				console.error('Error al crear asentamiento');
			}
		);
		console.log('crear servicio');
	}
	
	 $scope.uploadFile = function(idServicioTuristico)
	 {
       var file = $scope.myFile;
       
       console.log('file is ' );
       console.dir(file);
       
       var uploadUrl = '/servicioTuristico/' + idServicioTuristico + '/uploadFotoServicioTuristico';
       fileUpload.uploadFileToUrl(file, uploadUrl);
    };
	
		
	$scope.submit = function()
	{
		ServicioTuristicoService.createServicioTuristico($scope.servicioTuristico);
	}
	
	$scope.servicioTuristico.idServicioTuristico = $routeParams.id;
	getServicioTuristicoById($scope.servicioTuristico.idServicioTuristico);
	
	
}]);