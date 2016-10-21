'use strict';

var App = angular.module
(	'magicApp', 
	['ngRoute',
	 'infinite-scroll',
	 'ngCookies',
	 'pascalprecht.translate']
).constant("CONFIG", 
{
	"urlWebService":"http://localhost:8080/pueblosMagicos"
//	"urlWebService":"http://148.204.86.18:8080/pueblosMagicos"
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
      templateUrl: 'views/puebloMagico.html'
    })
    .when('/puebloMagico', 
	{
      templateUrl: 'views/puebloMagico.html'
    })
    .when('/gestionPueblosMagicos', 
	{
      templateUrl: 'views/administrador/mainAdmin.html'
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
    
    
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
});
//$locationProvider
//.html5Mode(true);

App.config(['$translateProvider','CONFIG',function ($translateProvider, CONFIG) 
{
    $translateProvider.useUrlLoader( 'http://localhost:8080/pueblosMagicos' + '/messageBundle');
//    $translateProvider.useStorage('UrlLanguageStorage');
    $translateProvider.preferredLanguage('es');
    $translateProvider.fallbackLanguage('es');
}]);
