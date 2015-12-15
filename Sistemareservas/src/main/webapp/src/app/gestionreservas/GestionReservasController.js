angular.module("app")
.controller('GestionReservasController',gestionReservas);

gestionReservas.$inject = ['$scope', 'GestionReservasFactory','$stateParams', 'GestionSalasFactory'];


function gestionReservas($scope, GestionReservas, $stateParams, GestionSalas){

	//declaraciones de variables y Modelo
	vm = this;
	$scope.formAgregar = {};
	$scope.formEditar = {};
	
	$scope.listado = [];
	$scope.salas = [];

	$scope.errores = [];
	
	
	//llamada iniciales
	refrescarlistado();
	obtenerSalas();
	
	
	//declaraciones de metodos
	function refrescarlistado(){
		$scope.listado = GestionReservas.query();
	}

	function obtenerSalas(){
		$scope.salas = GestionSalas.query();
	}	

	//esto deberia estar en un servicio
	function agregarErrores(dataResponse){
		if(dataResponse.data.errores){
			$scope.errores = dataResponse.data.errores; 
		}else{
			//no se respondio un error conocido se loguea la respuesta
			console.log(dataResponse);
			
		}
	}	
	
	
	
    //funciones del scope
	$scope.addReserva = function(data){
		gestion = new GestionReservas(data);
		gestion.$save(refrescarlistado,agregarErrores);
	} 
	
	$scope.editReserva = function(data){
		GestionReservas.edit({id: data.id},data,refrescarlistado, agregarErrores);
	} 
	
	$scope.deleteReserva = function(data){
		data.$delete(refrescarlistado);
	} 	
	
}