var appCliente = angular.module('clientes',['ngRoute','ngCookies']);

appCliente.service('clientes',function($http){
	this.listaClientes = function(){
		return $http({
			url:'http://localhost:8080/EjemploJersey/juan/Cliente',
			method:'GET'
		});
	}
	this.guardar = function(cliente){
		return $http({
			url:'http://localhost:8080/EjemploJersey/juan/Cliente',
			method:'POST',
			params:{
				identificacion: cliente.cedula,
				nombres: cliente.nombre,
				apellidos: cliente.apellidos,
				correo: cliente.email,
				usuarioCrea: 'juan'
			}
		
		});
	}
});

appCliente.service('usuarios',function($http,$cookies,$location){
	this.autenticar = function(usuario,passwd){
		return $http({
			url:'http://localhost:8080/EjemploJersey/juan/usuario',
			method:'GET',
			params:{
				login: usuario,
				pws: passwd,
			}
		
		});
	},
	
	this.validarEstado = function(){
		if(typeof($cookies.nombreUsuario)=='undefined' ||
				$cookies.nombreUsuario==""){
			$location.url("/");
			return false;
		}
		if($location.url()=='/'){
			
		}
	} 
});

appCliente.controller('listaClientes',function($scope,$location,clientes,usuarios){
	if(usuarios.validarUsuario()){
	
		clientes.listaClientes().then(function success(data){
			$scope.listaCliente = data.data.clienteJersey;
		});
	}
		$scope.agregar = function(){
			$location.url("/nuevo");
		}
	
});


appCliente.controller('Login',function($scope, $location, $cookies, usuarios){
	
	$scope.nombreUsuario = '';
	$scope.passwd = '';
	
	$scope.login = function(){
		usuarios.autenticar($scope.nombreUsuario,
				$scope.passwd).then(
			function success(data){
				if (data.data!='') {
					alert(data.data);
					$scope.nombreUsuario = '';
					$scope.passwd = '';
					return;
				}
				$cookies.nombreUsuario = $scope.nombreUsuario;
				$location.url("/listaClientes");
			},
			function failure(data){
				alert(data.data);
			}
	);
	}
});

appCliente.controller('cliente',
		function($scope,$location,clientes){
	$scope.cliente = {
			nombre:'',
			apellidos:'',
			cedula:'',
			email:''
	};
	
	$scope.guardar = function(){
		clientes.guardar($scope.cliente).then(
				function success(data){
					alert('Cliente creado');
					$location.url('/listaClientes');
				});
	}
	
});

appCliente.config(['$routeProvider',function($routeProvider){

	$routeProvider.when('/listaClientes',{
		templateUrl: 'listaCliente.html',
		controller:'listaClientes'
	});
	
	$routeProvider.when('/',{
		templateUrl: 'login.html',
		controller:'Login'
	});
	
	$routeProvider.when('/nuevo',{
		templateUrl: 'cliente.html',
		controller:'cliente'
	});
}]);

appCliente.run(function($rootScope, usuarios){
	$rootScope.$on('$routeChangeStart',function(){
		usuarios.validarEstado();
	})
});