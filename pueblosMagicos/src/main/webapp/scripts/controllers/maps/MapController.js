'use strict';

App.controller('MapController', ['$scope', 'PuebloMagicoService', function ($scope, PuebloMagicoService) 
{
	var mapOptions = 
	{
        zoom: 5,
        center: new google.maps.LatLng(24.195065, -100.133534),
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        scrollwheel : false
    }

    $scope.map = new google.maps.Map(document.getElementById('map'), mapOptions);

    $scope.markers = [];
    
    var infoWindow = new google.maps.InfoWindow();
    
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
   
    //var pms =  PuebloMagicoService.fetchAllPueblosMagicos();
    //$scope.fetchAllPueblosMagicos();
    /*var pms = $scope.fetchAllPueblosMagicos();
    
    var x;
    for(x in pms)
	{
    	console.log(pms[x].nombre);
	}*/
    
    //var obj = JSON.parse(pms);
    //console.log('pms0 ' + pms[0][nombre]);
    //var cities = new Array();
    //var cities = new List("city","desc","lat","long");
   // var citie = {city:'', desc:'', lat:null, long:null};
    //var cities = [];
    
    /*$(jQuery.parseJSON(JSON.stringify(pms))).each(function()
	{
    	console.log(this.nombre);
	});*/
    
    /*for (var i in pms)
    {
        citie.city = pms[i].nombre; 
        citie.desc = pms[i].descripcion;
        citie.lat = pms[i].latitud;
        citie.long = pms[i].longitud;
        cities.push(citie);
        console.log('nombre ' + i + ' valor' + pms[i].nombre);
    }
    
    for (i = 0; i < cities.length; i++)
    {
        createMarker(cities[i]);
        
    }*/
	
	
	
	//var self = this;
	$scope.pueblosMagicos = [];
	$scope.fetchAllPueblosMagicos = function()
	{
		PuebloMagicoService.fetchAllPueblosMagicos()
		.then
		(
			function(d)
			{
				$scope.pueblosMagicos = d;				
				console.log('self fetch pm okk');
				
				
				
				var pms = d;
			    var x;
			    /*for(x in pms)
				{
			    	console.log(pms[x].nombre);
			    	console.log(pms[x].latitud);
				}*/
			    var city = {};
			    var cities = [];
			   
			    var nombre;
			    for (var i in pms)
			    {
			        city.city = pms[i].nombre; 
			        nombre = pms[i].nombre;
			        city.desc = pms[i].descripcion;
			        city.lat = pms[i].latitud;
			        city.long = pms[i].longitud;
			        cities[i]=city;
			        //console.log('nombre ' + i + ' valor' + pms[i].nombre);
			        //console.log('nombreObj ' + i + ' valor' + city.city);
			        //console.log('nombreLista ' + i + ' valor' + cities[i].long);
			        createMarker(cities[i]);
			    }
			    
			    /*for (var i in cities)
			    {
			    	console.log(cities[i].city);
			        createMarker(cities[i]);
			        
			    }*/
				
				
				
				
				
			},
			function(errResponse)
			{
				console.error('Error al buscar pms');
			}
		);
		
	};
	
	
	$scope.fetchAllPueblosMagicos();
	

}]);