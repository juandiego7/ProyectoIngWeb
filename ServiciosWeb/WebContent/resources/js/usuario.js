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
    	/*alert($location.url());
    	if($location.url() == '' || $location.url() == '/' || $location.url() == '#acerca' || $location.url() == '#dispositivos' || $location.url() == '/registerUser' || $location.url() == '/#acerca' || $location.url() == '/#dispositivos'){
			
    		if(typeof($cookies.username) == 'undefined' || $cookies.username == ''){
    			
    		}
    		//$location.url($location.url());
			return true;
		}*/
    	//alert($cookies.username);
    	if($location.url() == '/registerUser'){
    		return true;
    	}
    	
    	if(typeof($cookies.username) == 'undefined' || $cookies.username == ''){
			$location.url('/login');
			return false;
		}
			
		if($location.url() == '/login'){
			$location.url('/user');
			return true;
		}
		
		if($location.url() == '/cerrar'){
			$cookies.username = '';
	    	//$cookieStore.remove("username");
	    	//alert('AJA');
	    	$location.url('/');
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
    
    this.reservarDispositivo = function (username,startDate,endDate,status,code,copy) {
        return $http({
            url: 'http://localhost:8080/ServiciosWeb/prestamos/loan/register',
            method: 'POST',
            params:{
            	username:username,
            	startDate:startDate,
            	endDate:endDate,
            	status:status,
                code:code,
                copy:copy
            }
        });
    };
    
    this.getPrestamosUser = function (typeId,numberId) {
        return $http({
            url: 'http://localhost:8080/ServiciosWeb/prestamos/loan/search',
            method: 'GET',
            params:{
            	typeId:typeId,
            	numberId:numberId
            }
        });
    };
    
    this.getPrestamosAll = function () {
        return $http({
            url: 'http://localhost:8080/ServiciosWeb/prestamos/loan',
            method: 'GET',
        });
    };
    
    this.deletePrestamo = function (username,startDate,code,copy) {
        return $http({
            url: 'http://localhost:8080/ServiciosWeb/prestamos/loan/delete',
            method: 'DELETE',
            params:{
            	username:username,
            	startDate:startDate,
                code:code,
                copy:copy
            }
        });
    };
    
});

appCliente.controller('pruebaController', function ($scope,$location) {
	//$location.url("/login")
});

appCliente.controller('dispositivoController', function ($scope,$location,$routeParams, $filter,$cookies, devices, loans) {
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
    
     $scope.getPrestamos = function(device){
    	 //alert('dss');
    	 //alert(device.status);
    	 $scope.dispositivo = device;
    	 
    	 
    	 $location.url('/dates?code='+device.deviceId.code+'&copy='+device.deviceId.copy+'&name='+device.name);
     }
     
     
     var code = $routeParams.code;
     var copy = $routeParams.copy;
     var name = $routeParams.name;
     
     $scope.device = {
    		 			"code":code,
    		 			"copy":copy,
    		 		    "name":name
    		 		 };
     $scope.date =	$filter('date')(new Date(),'yyyy/MM/dd');
     
     if(code != null && copy != null && code != '' && copy != '' && code != 'undefined' && copy != 'undefined'){
   	 
         loans.getPrestamosDispositivo(code, copy, $scope.date).then(
             function success(data) {
            	 $scope.hours = [8,9,10,11,12,13,14,15,16,17];
                 if (data.data != '' && data.data != null) {
                	 $scope.loans = data.data.loanW;
                	 if(typeof(data.data.loanW[0]) == 'undefined'){
                		 $scope.loans = data.data;
                	 }
  
                 	$scope.hoursBusy = [];	
                 	angular.forEach($scope.loans,function(dat){
                 		
                 		$scope.date = new Date(dat.loanId.startDate);//$filter('date')(new Date(),'yyyy/MM/dd');
                 		$scope.end = new Date(dat.endDate);
                 		var h = $scope.end.getHours()-$scope.date.getHours();
                 		for(var i=0;i<h;i++) {
                 			$scope.hoursBusy.push($scope.date.getHours()+i);
                 		}
                    });
                 	$scope.hoursD = [8,9,10,11,12,13,14,15,16,17];
                 	angular.forEach($scope.hoursD,function(hourFree){
                 		angular.forEach($scope.hoursBusy,function(hourBusy){
                 			if(hourFree == hourBusy){
                     			var index = $scope.hours.indexOf(hourFree);
                         		$scope.hours.splice(index, 1);
                 			}
                 		});
                     });
                 	
                 	
                 	
                 	//this.actLista();	
                 	/*angular.forEach(data.data,function(dat){
                         $scope.dispositivos.push(dat);
                     });*/
                 }
                 $scope.rangos = [];
              	angular.forEach($scope.hours,function(inicio){
              		var temp = inicio;
              		angular.forEach($scope.hours,function(fin){
              			
              				if(fin - temp == 0){
                      			rango={
                              			"inicio":inicio,
                              			"fin":fin + 1
                              		};
                      			if(fin-inicio < 8){
                      				$scope.rangos.push(rango);
                      			}
                      		temp = temp + 1;
                  		}
              			
                     });
                 });
              	
             },
             function failure(data) {
                 alert("falla " + data.data);
             }
          ); 
         
     }
     
     $scope.actLista = function(){
    	 angular.forEach($scope.dates,function(dat){
    		 alert(dat.loanId.device.startDate);
    		 $scope.date = new Date(dat.loanId.device.startDate);//$filter('date')(new Date(),'yyyy/MM/dd');
          	 alert('H: '+$scope.date.getHours());
         });
     }
     
     //     
   
    
	  
 	
 	//$location.url("/login")
 	$scope.reservar = function (dispositivo,r) {
 		$scope.inicio = $filter('date')(new Date(),'yyyy/MM/dd');
 		$scope.inicio = $scope.inicio + " " + r.inicio;
 		
 		$scope.fin = $filter('date')(new Date(),'yyyy/MM/dd');
 		$scope.fin = $scope.fin + " " + r.fin;
 		
 		
 		alert("$scope.inicio "+ $scope.inicio + " $scope.fin "+ $scope.fin);
         //alert(dispositivo.deviceId.code);
         loans.reservarDispositivo($cookies.username,$scope.inicio,$scope.fin,"RESERVADO",dispositivo.code,dispositivo.copy).then(
                 function success(data) {
                     if (data.data != null) {
                    	 alert(data.data.message);
                     	if(data.data.type == "ok"){
                     		$location.url("/loans");
                     	}
                     	$scope.inicio = '';
                     	$scope.fin = '';
                     	$scope.device = '';                             
                      }
                  },
                 function failure(data) {
                     alert("falla " + data.data);
                 }
         );
 	}

    
    
});

appCliente.controller('prestamoController', function ($scope,$location,$cookies,$filter, loans) {
	
	if($cookies.role=="I"){
		loans.getPrestamosUser($cookies.typeId,$cookies.numberId).then(
	            function success(data) {

	                if (data.data != '') {
	                	$scope.listaLoans = data.data.loanW;
	                	
	                	/*angular.forEach(data.data,function(dat){
	                        $scope.dispositivos.push(dat);
	                    });*/
	                }
	            },
	            function failure(data) {
	                alert("falla " + data.data);
	            }
	    );
	
	}else{
		loans.getPrestamosAll().then(
	            function success(data) {

	                if (data.data != '') {
	                	$scope.listaLoans = data.data.loanW;
	                	
	                	/*angular.forEach(data.data,function(dat){
	                        $scope.dispositivos.push(dat);
	                    });*/
	                }
	            },
	            function failure(data) {
	                alert("falla " + data.data);
	            }
	    );

	}
	
	

 
    
    $scope.borrar = function (loan) {
    	
    	$scope.inicio = $filter('date')(loan.loanId.startDate,'yyyy/MM/dd HH');
// 		$scope.inicio = $scope.inicio + " " + r.inicio;
    	
         loans.deletePrestamo(loan.loanId.username.username,$scope.inicio,loan.loanId.device.deviceId.code,loan.loanId.device.deviceId.copy).then(
                 function success(data) {
                     if (data.data != null) {
                    	 alert(data.data.message);
                     	if(data.data.type == "ok"){
                     		var index = $scope.listaLoans.indexOf(loan);
                     		$scope.listaLoans.splice(index, 1);
                     		
                     		$location.url("/loans"); //redirección a principal
                     		
                     	}
                     	                             
                      }
                  },
                 function failure(data) {
                     alert("falla " + data.data);
                 }
         );
 	}
    
	
});

appCliente.controller('usuarioController', function ($scope, $cookies, $location, $log, users) {

    $scope.username = '';
    $scope.typeId = '';
    $scope.numberId = '';
    $scope.name = '';
    $scope.lastName = '';
    $scope.email = '';
    $scope.password = '';
    $scope.rPassword = '';
    $scope.role = 'I';
    $scope.manager = null;
    $scope.code = '';
    $scope.copy = '';
    
    $scope.nombre = $cookies.name ;
    $scope.u = $cookies.username;
    $scope.rol = $cookies.role;
    
    $scope.login = function () {
        users.autenticar($scope.username, $scope.password).then(
                function success(data) {
                        $scope.password = '';
                        if (data.data != '' && data.data !== null) {
                        	//alert(JSON.stringify(data.data));
                            $scope.nombre = $scope.username;
                            $cookies.username = $scope.username;
                            $cookies.role = data.data.role;
                            $cookies.name = data.data.name;
                            $cookies.typeId = data.data.typeId;
                            $cookies.numberId = data.data.numberId;
                            console.log($scope.nombre);
                            //alert($scope.nombre);
                            $location.url("/user")
                            //$window.location.href = "http://localhost:8080/PrestamosDispositivos/usuario.html";
                        } else {
                            alert("Usuario o contraseña incorrecto");                            
                        }
                },
                function failure(data) {
                    alert("falla " + data.data);
                }
        );
    };
    
    $scope.getNav = function(){
    	var nav = '';
    	if($scope.rol == 'A'){
    		nav = 'navbarAdmin.html';
    	}else if($scope.rol == 'I'){
    		nav = 'navbarInv.html';
    	}else{
    		nav = 'navbarLogin.html';
    	}
    	return nav;
    }

    $scope.registrar = function () {
    	//alert($scope.password + ' ' + $scope.rPassword);
    	if($scope.password != $scope.rPassword){
    		alert('Las contraseñas no coinciden');
    		return;
    	}
    	if($scope.rol == 'A' ){
    		$scope.manager = $scope.u;
    		$scope.role = 'A';
    	}
    	//alert($scope.rol + ' ' + $scope.manager);
        users.registrar(
                $scope.username, $scope.typeId, $scope.numberId,
                $scope.name, $scope.lastName, $scope.email,
                $scope.password, $scope.role, $scope.manager).then(
                function success(data) {
                	if(data.data.type != 'error'){
	                    $scope.username = '';
	                    $scope.typeId = '';
	                    $scope.numberId = '';
	                    $scope.name = '';
	                    $scope.lastName = '';
	                    $scope.email = '';
	                    $scope.password = '';
	                    $scope.rPassword = '';
	                    $scope.role = '';
	                    $scope.manager = null;
	                    $location.url("/login")
                    }
                	alert(data.data.message);
                    //$window.location.href = "http://localhost:8080/PrestamosDispositivos/login.html";
                },
                function failure(data) {
                    alert('Error: ' + data.data.message);
                }
        );
    };

});

appCliente.config(['$routeProvider',function($routeProvider){

	$routeProvider.when('/',{
		templateUrl: 'main.html',
		controller:'usuarioController'
	});
	
	$routeProvider.when('/login',{
		templateUrl: 'login.html',
		controller:'usuarioController'
	});
	
	$routeProvider.when('/devices',{
		templateUrl: 'devices.html',
		controller:'dispositivoController'
	});
	
	$routeProvider.when('/loans',{
		templateUrl: 'loans.html',
		controller:'prestamoController'
	});
	
	$routeProvider.when('/registerUser',{
		templateUrl: 'regUsuario.html',
		controller:'usuarioController'
	});
	
	$routeProvider.when('/registerDevice',{
		templateUrl: 'regDispositivo.html',
		controller:'usuarioController'
	});
	
	$routeProvider.when('/user',{
		templateUrl: 'user.html',
		controller:'usuarioController'
	});
	
	$routeProvider.when('/dates',{
		templateUrl: 'dates.html',
		controller:'dispositivoController'
	});	
	
}]);

appCliente.run(function($rootScope, users){
	$rootScope.$on('$routeChangeStart', function(){
		users.validarEstado();
	})
});