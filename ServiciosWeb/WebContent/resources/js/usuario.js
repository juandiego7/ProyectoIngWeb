var appCliente = angular.module('usuario', ['ngRoute', 'ngCookies']);

appCliente.service('users', function ($http, $cookies, $location) {
    this.autenticar = function (username, password) {
        return $http({
            url: 'http://localhost:8080/ServiciosWeb/prestamos/user/login',
            method: 'GET',
            params: {
                username: username,
                password: password
            }

        }); 
    };

    this.registrar = function (username, typeId, numberId, name, lastName, email, password, role, manager) {
        return $http({
            url: 'http://localhost:8080/ServiciosWeb/prestamos/user/register',
            method: 'POST',
            params: {
                username: username,
                typeId: typeId,
                numberId: numberId,
                name: name,
                lastName: lastName,
                email: email,
                password: password,
                role: role,
                manager: manager
            }
        });
    };
    
    this.validarEstado = function(){
    	if($location.url() == '/registerUser'){
			$location.url('/registerUser');
			return true;
		}
    	if(typeof($cookies.username) == 'undefined' || $cookies.username == ''){
			$location.url('/login');
			return false;
		}else
			
		if($location.url() == '/login'){
			$location.url('/');
			return true;
		}
	}
});

appCliente.service('devices', function ($http, $cookies, $location) {
    this.getDispositivos = function () {
        return $http({
            url: 'http://localhost:8080/ServiciosWeb/prestamos/device/all',
            method: 'GET'
        });
    };
});

appCliente.service('loans', function ($http, $cookies, $location) {
    this.getPrestamosDispositivo = function (code,copy,date) {
        return $http({
            url: 'http://localhost:8080/ServiciosWeb/prestamos/loan/get',
            method: 'GET',
            params:{
                code:code,
                copy:copy,
                date:date
            }
        });
    };
});

appCliente.controller('pruebaController', function ($scope,$location) {
	//$location.url("/login")
});

appCliente.controller('dispositivoController', function ($scope,$location,devices) {
	//$location.url("/login")
	//$scope.getDispositivos = function () {
        //$scope.dispositivos = [];
        devices.getDispositivos().then(
                function success(data) {

                    if (data.data != '') {
                    	$scope.listaDevices = data.data.device;
                    	
                    	/*angular.forEach(data.data,function(dat){
                            $scope.dispositivos.push(dat);
                        });*/
                    }
                },
                function failure(data) {
                    alert("falla " + data.data);
                }
        );
    //};
    
    
    
});

appCliente.controller('prestamoController', function ($scope,$location) {
	//$location.url("/login")
	$scope.reservar = function (dispositivo) {
        $scope.prestamos = [];
        $scope.date = new Date();
        alert(dispositivo.deviceId.code);
        usuarios.getPrestamosDispositivo(dispositivo.deviceId.code,dispositivo.deviceId.copy,$scope.date).then(
                function success(data) {

                    if (data.data != '') {
                        angular.forEach(data.data,function(dat){
                            $scope.prestamos.push(dat);
                            alert(dat.status);
                        });
                    }
                    $location.url("/")
                    //$window.location.href = "http://localhost:8080/PrestamosDispositivos/reserva.html";
                },
                function failure(data) {
                    alert("falla " + data.data);
                }
        );
    };
});

appCliente.controller('usuarioController', function ($scope, $cookies, $location, $log, users) {

    $scope.username = '';
    $scope.typeId = '';
    $scope.numberId = '';
    $scope.name = '';
    $scope.lastName = '';
    $scope.email = '';
    $scope.password = '';
    $scope.role = '';
    $scope.manager = '';
    $scope.code = '';
    $scope.copy = '';

    $scope.login = function () {
        users.autenticar($scope.username, $scope.password).then(
                function success(data) {
                    if (data.data != '') {
                        $scope.password = '';
                        if (data.data.type != "error") {
                            
                            $scope.nombre = $scope.username;
                            $cookies.username = $scope.username;
                            console.log($scope.nombre);
                            alert($scope.nombre);
                            $location.url("/")
                            //$window.location.href = "http://localhost:8080/PrestamosDispositivos/usuario.html";
                        } else {
                            alert(data.data.message);                            
                        }
                    }

                },
                function failure(data) {
                    alert("falla " + data.data);
                }
        );
    };

    $scope.registrar = function () {
        users.registrar(
                $scope.username, $scope.typeId, $scope.numberId,
                $scope.name, $scope.lastName, $scope.email,
                $scope.password, $scope.role, $scope.manager).then(
                function success(data) {
                    $scope.username = '';
                    $scope.typeId = '';
                    $scope.numberId = '';
                    $scope.name = '';
                    $scope.lastName = '';
                    $scope.email = '';
                    $scope.password = '';
                    $scope.role = '';
                    $scope.manager = '';
                    $location.url("/")
                    //$window.location.href = "http://localhost:8080/PrestamosDispositivos/login.html";
                },
                function failure(data) {
                    alert("Error");
                }
        );
    };

});

appCliente.config(['$routeProvider',function($routeProvider){

	$routeProvider.when('/',{
		templateUrl: 'main.html',
		controller:'pruebaController'
	});
	
	$routeProvider.when('/login',{
		templateUrl: 'login.html',
		controller:'usuarioController'
	});
	
	$routeProvider.when('/devices',{
		templateUrl: 'devices.html',
		controller:'dispositivoController'
	});
	
	$routeProvider.when('/registerUser',{
		templateUrl: 'regUsuario.html',
		controller:'usuarioController'
	});
	
	$routeProvider.when('/registerDevice',{
		templateUrl: 'regDispositivo.html',
		controller:'usuarioController'
	});
	
}]);

appCliente.run(function($rootScope, users){
	$rootScope.$on('$routeChangeStart', function(){
		users.validarEstado();
	})
});

