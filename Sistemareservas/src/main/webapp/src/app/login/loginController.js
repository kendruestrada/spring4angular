angular.module("app")
.controller('loginController', login)
		
login.$inject = ['$rootScope', '$scope', '$http', '$state', 'SeguridadFactory'];
		
function login($rootScope, $scope, $http, $state, SeguridadFactory) {

    //llamamos a authenticar inmediatamente al ingresar
	//sin creadenciales de maneera que al ingresar quese
	//no autenticado
	SeguridadFactory.autenticar();

	//formulario que contendra el usuario y el password
	$scope.credentials = {};
	
	//funciones login y logout
	$scope.login = function() {
		SeguridadFactory.autenticar($scope.credentials)
		.then(function(){
			console.log("Login succeeded");
			$state.go('gestionreservas');
		},
		//error en login
		function(){
			console.log("Login failed");
		}
		);
		

	};

	$scope.logout = function() {
		$http.post('logout', {}).success(function() {
			$rootScope.authenticated = false;
			$location.path("/");
		}).error(function(data) {
			console.log("Logout failed")
			$rootScope.authenticated = false;
		});
	}

}