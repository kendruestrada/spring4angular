angular.module("app")
.factory('SeguridadFactory',SeguridadFactory );

SeguridadFactory.$inject = ['$http','$q'];

function SeguridadFactory($http,$q){
	
	 var factory = {}; 
	 factory.autenticar = function(credentials) {
		 
		 //construye una promesa con la funcion de autenticacion
         return $q(promesaAutenticar);
         
         //autentica poniendo la credenciales en el header e
         //intentando accedr a una URI de la aplicacion
         //si puede acceder quere decir ya ya esta autenticado.
         function promesaAutenticar(resolve, reject){
				var headers = credentials ? {
					authorization : "Basic "
							+ btoa(credentials.username + ":"
									+ credentials.password)
				} : {};
	
				$http.get('/reservas', {
					headers : headers
				}).success(function(data) {
					resolve(true);
				}).error(function() {
					reject(false);
				});
         };
	 };

	return factory;
	
}


