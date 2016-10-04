'use strict';

App.factory('UrlLanguageStorage', ['$location', function ($location) 
{
    return {
        set: function (name, value){},
        get: function (name) 
        {
            return $location.search()['lang']
        }
    };
}]);