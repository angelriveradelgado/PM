'use strict';

App.controller('LanguageController', ['$scope', '$translate', '$location', function ($scope, $translate, $location) 
{
	var self = this;
    this.changeLanguage = function (locale) 
    {    	
    	console.log('cambie ' + locale);
        $translate.use(locale);
        $location.search('lang', locale);
        
        //window.location.href = $location.absUrl();
        //window.location.reload();
    };
}]);