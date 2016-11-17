'use strict';

var App = angular.module
(	'magicApp', 
	['ngRoute',
	 'infinite-scroll',
	 'ngCookies',
	 'pascalprecht.translate',
	 'ngAnimate',
	 'ngSanitize',
	 'ui.bootstrap']
).constant("CONFIG", 
{
//	"urlWebService":"http://localhost:8080/pueblosMagicos"
	"urlWebService":"http://148.204.86.18:8080/pueblosMagicos"
});

App.config( function ($routeProvider, $httpProvider) 
{
    $routeProvider
    .when('/', 
	{
      templateUrl: 'views/main.html'
    })
    .when('/login', 
	{
      templateUrl: 'views/login.html'
    })
    .when('/perfil', 
	{
      templateUrl: 'views/perfilUsuario.html'
    })
    .when('/registro', 
	{
      templateUrl: 'views/registro.html'
    })
    .when('/registroPST', 
	{
      templateUrl: 'views/PST/registroPST.html'
    })
    .when('/pueblo/:nombrePuebloMagico', 
	{
      templateUrl: 'views/puebloMagicoInfo.html'
    })
    .when('/pueblo/:nombrePuebloMagico/atractivosTuristicos', 
	{
      templateUrl: 'views/atractivosTuristicos.html'
    })
    .when('/atractivoTuristico/:id', 
	{
      templateUrl: 'views/atractivoTuristico.html'
    })
    .when('/pueblo/:nombrePuebloMagico/serviciosTuristicos', 
	{
      templateUrl: 'views/serviciosTuristicos.html'
    })
    .when('/servicioTuristico/:id', 
	{
      templateUrl: 'views/servicioTuristico.html'
    })    
    .when('/pueblo/:nombrePuebloMagico/serviciosEmergencias', 
	{
      templateUrl: 'views/serviciosEmergencias.html'
    })
    .when('/servicioEmergencias/:id', 
	{
      templateUrl: 'views/servicioEmergencias.html'
    })
    
    //vistas del pst
    .when('/pst/serviciosPST', 
	{
      templateUrl: 'views/PST/mainPST.html'
    })
    .when('/pst', 
	{
      templateUrl: 'views/PST/inicio.html'
    })
    .when('/pst/establecimientos', 
	{
      templateUrl: 'views/PST/establecimientosPST.html'
    })
    .when('/pst/establecimientos/detalles/:id', 
	{
      templateUrl: 'views/PST/establecimientoDetalles.html'
    })
    .when('/pst/registroEstablecimiento', 
	{
      templateUrl: 'views/PST/registroEstablecimiento.html'
    })
    .when('/pst/editarEstablecimiento/:id', 
	{
      templateUrl: 'views/PST/editarEstablecimiento.html'
    })
    
    .when('/pst/serviciosTuristicos', 
	{
      templateUrl: 'views/PST/serviciosTuristicos.html'
    })
    .when('/pst/serviciosTuristicos/detalles/:id', 
	{
      templateUrl: 'views/PST/servicioTuristicoDetalles.html'
    })
    .when('/pst/:idEstablecimiento/registroServicioTuristico', 
	{
      templateUrl: 'views/PST/registroServicioTuristico.html'
    })
    .when('/pst/editarServicioTuristico/:id', 
	{
      templateUrl: 'views/PST/editarServicioTuristico.html'
    })
    
    ///////////////////
    //vistas del admin
    .when('/gestion', 
	{
      templateUrl: 'views/administrador/inicio.html'
    })
    .when('/gestion/pueblosMagicos', 
	{
      templateUrl: 'views/administrador/pueblosMagicos.html'
    })
     .when('/gestion/registroPuebloMagico', 
	{
      templateUrl: 'views/administrador/puebloMagicoRegistro.html' 
    })
    
    .when('/gestion/atractivosTuristicos', 
	{
      templateUrl: 'views/administrador/atractivosTuristicos.html'
    })
    .when('/gestion/:idPuebloMagico/registroAtractivoTuristico', 
	{
      templateUrl: 'views/administrador/registroAtractivoTuristico.html'
    })
    .when('/gestion/atractivosTuristicos/detalles/:id', 
	{
      templateUrl: 'views/administrador/atractivoTuristicoDetalles.html'
    })
    .when('/gestion/editarAtractivoTuristico/:id', 
	{
      templateUrl: 'views/administrador/editarAtractivoTuristico.html'
    })
    
    .when('/gestion/serviciosEmergencias', 
	{
      templateUrl: 'views/administrador/serviciosEmergencias.html' 
    })
   
    ///////////////////
    //vistas del validador
    .when('/validacion', 
	{
      templateUrl: 'views/validador/inicio.html' 
    })
    
    .when('/validacion/prestadores', 
	{
      templateUrl: 'views/validador/pst.html' 
    })
    .when('/validacion/prestadores/validar/:idPST', 
	{
      templateUrl: 'views/validador/validacionPST.html' 
    })
    
    .when('/validacion/establecimientos', 
	{
      templateUrl: 'views/validador/establecimientos.html' 
    })
    .when('/validacion/establecimientos/validar/:idEstablecimiento', 
	{
      templateUrl: 'views/validador/validacionEstablecimiento.html' 
    })
    
    .when('/validacion/serviciosTuristicos', 
	{
      templateUrl: 'views/validador/serviciosTuristicos.html' 
    })
    .when('/validacion/serviciosTuristicos/validar/:idServicioTuristico', 
	{
      templateUrl: 'views/validador/validacionServicioTuristico.html' 
    })

    
    
    
    
    .when('/404', 
	{
      templateUrl: 'views/404.html'
    })
    .when('/accecoDenegado', 
	{
      templateUrl: 'views/accesoDenegado.html'
    })
    .when('/prueba', 
	{
      templateUrl: '/prueba.html'
    })
    .when('/upload', 
	{
      templateUrl: 'views/upload.html'
    })
    .otherwise(
	{
      redirectTo: '/'
	});
    
    //$locationProvider.html5Mode(true);
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
})
.run(['$rootScope', '$location', '$cookieStore', '$http', function ($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
//        if ($rootScope.globals.currentUser) {
//            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
//        }
 
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
        	console.log( $location.path() );
        	console.log( $rootScope.globals.currentUser );
//            if ( ( $location.path() !== '/#/login' || $location.path() !== '/') && !$rootScope.globals.currentUser) {
//                $location.path('/');
//            }
        	
        //revisa si hay una sesion activa
		if( $rootScope.globals.currentUser  )
		{
			 if( $location.path() == '/login' )
    		 {
    			 $location.path('/');
    		 }
		}
       	 
			//vistas del pst
	    	 if ( !$rootScope.globals.currentUser || $rootScope.globals.currentUser.tipoUsuarioIdtipoUsuario != 2 ) 
	    	 {
	    		 if( $location.path().indexOf('/pst') > -1)
	    		 {
	    			 $location.path('/');
	    		 }
	         }
	    	 
	    	 //vistas del validador
	    	 if ( !$rootScope.globals.currentUser || $rootScope.globals.currentUser.tipoUsuarioIdtipoUsuario != 3 ) 
	    	 {
	    		 if( $location.path().indexOf('/validacion') > -1)
	    		 {
	    			 $location.path('/');
	    		 }
	         }
	    	 
	    	 //vistas del admin
	    	 if ( !$rootScope.globals.currentUser || $rootScope.globals.currentUser.tipoUsuarioIdtipoUsuario != 4 ) 
	    	 {
	    		 if( $location.path().indexOf('/gestion') > -1)
	    		 {
	    			 $location.path('/');
	    		 }
	         }
    	 
    });
}]);
//$locationProvider
//.html5Mode(true);

App.config(['$translateProvider','CONFIG',function ($translateProvider, CONFIG) 
{
    $translateProvider.useUrlLoader(  CONFIG.urlWebService + '/messageBundle');
//    $translateProvider.useStorage('UrlLanguageStorage');
    $translateProvider.preferredLanguage('es');
    $translateProvider.fallbackLanguage('es');
}]);
