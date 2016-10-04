'use strict';

App.controller('TuristaController', ['$scope', 'Turista', function($scope, Turista) {
          var self = this;
          self.turista= new Turista();
          
          self.turistas=[];
              
          self.fetchAllTurista = function(){
        	  self.turistas = Turista.query();
          };
           
          self.createTurista = function(){
        	  self.turista.$save(function(){
        		  self.fetchAllTuristas();
        	  });
          };
		  
          self.updateTurista = function(){
        	  self.turista.$update(function(){
    			  self.fetchAllTuristas();
    		  });
          };

         self.deleteTurista = function(identity){
        	 var turista = Turista.get({id:identity}, function() {
        		 turista.$delete(function(){
        			  console.log('Deleting turista with id ', identity);
        			  self.fetchAllTuristas();
        		  });
        	 });
          };

          self.fetchAllTurista();

          self.submit = function() {
              if(self.turista.id==null){
                  console.log('Saving New turista', self.turista);    
                  self.createTurista();
              }else{
    			  console.log('Upddating turista with id ', self.turista.id);
                  self.updateTurista();
                  console.log('Turista updated with id ', self.turista.id);
              }
              self.reset();
          };
              
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.turistas.length; i++){
                  if(self.turistas[i].id === id) {
                     self.turista = angular.copy(self.turistas[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(id){
              console.log('id to be deleted', id);
              if(self.turista.id === id) {//If it is the one shown on screen, reset screen
                 self.reset();
              }
              self.deleteTurista(id);
          };

          
          self.reset = function(){
              self.turista= new Turista();
              $scope.myForm.$setPristine(); //reset Form
          };

      }]);
