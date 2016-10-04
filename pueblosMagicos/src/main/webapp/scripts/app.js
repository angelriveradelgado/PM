'use strict';

var App = angular.module
(	'magicApp', 
	['ngRoute',
	 'ngCookies',
	 'pascalprecht.translate']
);

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
    .otherwise(
	{
      redirectTo: '/'
	});
    
    
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
});

App.config(['$translateProvider',function ($translateProvider) 
{
    $translateProvider.useUrlLoader('http://localhost:8080/pueblosMagicos/messageBundle');
//    $translateProvider.useStorage('UrlLanguageStorage');
    $translateProvider.preferredLanguage('es');
    $translateProvider.fallbackLanguage('es');
}]);
