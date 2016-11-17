'use strict';

      
 App.controller('myCtrl', ['$scope', 'FileUploadService', 'CONFIG', function($scope, fileUpload, CONFIG){
    $scope.uploadFile = function(){
       var file = $scope.myFile;
       
       console.log('file is ' );
       console.dir(file);
       
       var uploadUrl = CONFIG.urlWebService +  "/servicioTuristico/4/uploadFotoServicioTuristico";
       fileUpload.uploadFileToUrl(file, uploadUrl);
    };
 }]);
			