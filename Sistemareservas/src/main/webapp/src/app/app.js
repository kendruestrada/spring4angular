// Creación del módulo Principal
//se importa el modulo ui.router
angular.module("app", ['ui.router','ngResource'])

.config(function($stateProvider, $urlRouterProvider,$httpProvider) {
	
	//se agrega esta cabecera a todas las solicitudes http 
	//para que el browser no solicite el usuario y password.	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';	
	
	//Por defecto se mostrara el login, que contiene el listado
	 $urlRouterProvider.otherwise("/login");

	  //definicion de estados 
	  $stateProvider
	    .state('login', {
		      url: "/login" ,
		      templateUrl: "/src/app/login/login.html",
		      controller: 'loginController'
	    })	  
	  
	    .state('gestionreservas', {
		      url: "/gestionreservas" ,
		      templateUrl: "/src/app/gestionreservas/principal.tpl.html",
		      controller: 'GestionReservasController'
	    })
	    .state('gestionreservas.nuevo', {
		      url: "/nuevo" ,
		      templateUrl: "/src/app/gestionreservas/nuevo.tpl.html",
		    })
	    .state('gestionreservas.editar', {
		      url: "/editar/:id" ,
		      templateUrl: "/src/app/gestionreservas/editar.tpl.html",
		      //necesitamos tener obtener el objeto de la BD antes de editarlo y esto lo haremos 
		      //creando un nuevo controller este sera hijo del controller del mantenedor , por lo tanto tiene acceso
		      // a su scope
		      controller: function(GestionReservasFactory, $stateParams, $scope,$filter) {
		    	  aux = GestionReservasFactory.get({id: $stateParams.id},
		    		function(){
		    		  //Para poder trabajar con el tipo date de html 5 
		    		  //se deben transformar las fechas en un objeto Date
			    	  aux.fechaInicio = new Date(aux.fechaInicio);
			    	  aux.fechaFin = new Date(aux.fechaFin);
			    	  $scope.formEditar = aux;
		    	  	}	  
		    	  
		    	  );
		      }
		 })
		 ;
})

