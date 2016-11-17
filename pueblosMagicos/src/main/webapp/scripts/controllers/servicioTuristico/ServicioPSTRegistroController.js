'use strict';

App.controller('ServicioPSTRegistroController', ['$document', '$rootScope','$scope', '$routeParams', '$http', 'ServicioTuristicoService', '$window', 'CONFIG', 'EstadoService','EstablecimientoService', 
	function ($document, $rootScope, $scope, $routeParams, $http, ServicioTuristicoService, $window,  CONFIG, EstadoService, EstablecimientoService) 
{
	$scope.slider = {
		    value: 10
		};
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
		extensionTelefono: null,
		promedio: null
	}
	
	var getEstablecimientoByidPST = function()
	{
		EstablecimientoService.getEstablecimientoByidPST($routeParams.idEstablecimiento)
		.then
		(
			function(d)
			{		
				$scope.establecimientos = JSON.parse(JSON.stringify(d));
			},
			function(errResponse)
			{
				console.error('Error al buscar establecimientos');
			}
		);
	}
	
	var servicioTuristicoAlojamiento = 
	{
		stIdServicio: null,
		toIdtipoOperacion: null,
		taIdtipoAlojamiento: null,
		tsaIdtipoServicioAlojamiento: null
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
				$window.location.href = CONFIG.urlWebService +'/#/pst/serviciosTuristicos';
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
		$window.location.href = CONFIG.urlWebService +'/#/pst/serviciosTuristicos';
	}
	
	getEstablecimientoByidPST ();
	getTiposServiciosTuristicos();
	
}]);
