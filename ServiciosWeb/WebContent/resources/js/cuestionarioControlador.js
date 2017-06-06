/**
 * juan diego goez durango - diego.goez@udea.edu.co
 * Controlador para el manejo de las preguntas
 */

var cuestionario = angular.module('modCuestionario',[]);
cuestionario.controller('contCuestionario',
		function($scope){
	$scope.preguntas = [
		{
			id:1,
			texto: 'Pregunta 1',
			respuestaValida: 1,
			respuesta: null,
			estado:'',
			respuestas: [{id: 1, texto: 'Respuesta 1.1'},
						 {id: 2, texto: 'Respuesta 1.2'},
						 {id: 3, texto: 'Respuesta 1.3'}]
		},
		{
			id:2,
			texto: 'Pregunta 2',
			respuestaValida: 2,
			respuesta: null,
			estado:'',
			respuestas: [{id: 1, texto: 'Respuesta 2.1'},
						 {id: 2, texto: 'Respuesta 2.2'},
						 {id: 3, texto: 'Respuesta 2.3'}]
		},
		{
			id:3,
			texto: 'Pregunta 3',
			respuestaValida: 3,
			respuesta: null,
			estado:'',
			respuestas: [{id: 1, texto: 'Respuesta 3.1'},
						 {id: 2, texto: 'Respuesta 3.2'},
						 {id: 3, texto: 'Respuesta 3.3'}]
		}
	];
	
	$scope.respuestasCorrectas = 0;
	$scope.estadoUsuario = '';
	
	/**
	 * funcion para dar la calificacion
	 */
	$scope.calificar = function(){
		$scope.respuestasCorrectas = 0;
		angular.forEach($scope.preguntas, function(item){
			if(item.respuesta == item.respuestaValida){
				$scope.respuestasCorrectas++;
				item.estado = 'ok';
			}else{
				item.estado = 'error';
			}
		});
		estadoUsuario();
	}
	
	$scope.validar = function(pregunta){
		if (pregunta.respuestaValida == pregunta.respuesta) {
			$scope.respuestasCorrectas++;
			pregunta.estado = 'ok';
		}else{
			if (pregunta.estado=='ok' && $scope.respuestasCorrectas > 0) {
				$scope.respuestasCorrectas--;
				pregunta.estado = 'error';
			}
		}
		estadoUsuario();
	}
	
	function estadoUsuario(){
		var total = $scope.respuestasCorrectas/$scope.preguntas.length;
		
		if (total==0) {
			$scope.estadoUsuario = 'looser';
		}else if(total==1){
			$scope.estadoUsuario = 'guru';
		}else{
			$scope.estadoUsuario = 'poor';
		}
	}
});