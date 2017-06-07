var appCliente = angular.module('usuario', ['ngRoute', 'ngCookies']);

appCliente.service('usuario', function ($http, $cookies, $location) {
    this.autenticar = function (username, password) {
        return $http({
            url: 'http://localhost:8080/PrestamosDispositivos/api/usuario',
            method: 'GET',
            params: {
                username: username,
                password: password
            }

        }); 
    };

    this.registrar = function (username, typeId, numberId, name, lastName, email, password, role, manager) {
        return $http({
            url: 'http://localhost:8080/PrestamosDispositivos/api/usuario',
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

    this.getDispositivos = function () {
        return $http({
            url: 'http://localhost:8080/PrestamosDispositivos/api/dispositivo',
            method: 'GET'
        });
    };
    
    this.getPrestamosDispositivo = function (code,copy,date) {
        return $http({
            url: 'http://localhost:8080/PrestamosDispositivos/api/prestamo/dispositivo',
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
	$location.url("/login")
});

appCliente.controller('usuarioController', function ($scope, $cookies, $window, $log, usuarios) {

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
        usuarios.autenticar($scope.username, $scope.password).then(
                function success(data) {
                    if (data.data != '') {
                        $scope.password = '';
                        if (data.data == "S") {
                            
                            $scope.nombre = $scope.username;
                            $cookies.username = $scope.username;
                            console.log($scope.nombre);
                            alert($scope.nombre);
                            $window.location.href = "http://localhost:8080/PrestamosDispositivos/usuario.html";
                        } else {
                            alert("Usuario incorrecto");
                        }
                    }

                },
                function failure(data) {
                    alert("falla " + data.data);
                }
        );
    };

    $scope.registrar = function () {
        usuarios.registrar(
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
                    $window.location.href = "http://localhost:8080/PrestamosDispositivos/login.html";
                },
                function failure(data) {
                    alert("Error");
                }
        );
    };
    
    $scope.getDispositivos = function () {
        $scope.dispositivos = [];
        usuarios.getDispositivos().then(
                function success(data) {

                    if (data.data != '') {
                        angular.forEach(data.data,function(dat){
                            $scope.dispositivos.push(dat);
                        });
                    }
                },
                function failure(data) {
                    alert("falla " + data.data);
                }
        );
    };
    
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
                    $window.location.href = "http://localhost:8080/PrestamosDispositivos/reserva.html";
                },
                function failure(data) {
                    alert("falla " + data.data);
                }
        );
    };

});

appCliente.config(['$routeProvider',function($routeProvider){

	$routeProvider.when('/',{
		templateUrl: 'index.html',
		controller:'pruebaController'
	});
	
	$routeProvider.when('/login',{
		templateUrl: 'login.html',
		controller:'usuarioController'
	});
	
}]);

