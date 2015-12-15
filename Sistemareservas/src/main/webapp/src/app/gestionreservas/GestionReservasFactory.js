angular.module("app")
.factory('GestionReservasFactory',GestionReservasFactory );

GestionReservasFactory.$inject = ['$resource'];

function GestionReservasFactory($resource){
	return $resource('/reservas/:id',{id :'@id'},{ 'edit':    {method:'PUT'}});
}