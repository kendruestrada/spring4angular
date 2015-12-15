angular.module("app")
.factory('GestionSalasFactory',GestionSalasFactory );

GestionSalasFactory.$inject = ['$resource'];

function GestionSalasFactory($resource){
	return $resource('/salas/:id',{id :'@id'},{ 'edit':    {method:'PUT'}});
}