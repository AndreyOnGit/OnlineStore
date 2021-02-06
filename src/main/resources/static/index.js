angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';
    $scope.authorized = false;


    $scope.fillTable = function (current_page = 1) {
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                page: current_page
            }
        }).then(function (response) {
            $scope.ProductPage = response.data;

            let minPageIndex = current_page - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = current_page + 2;
            if (maxPageIndex > $scope.ProductPage.totalPages) {
                maxPageIndex = $scope.ProductPage.totalPages;
            }

            $scope.PaginationArray = $scope.generatePageIndices(minPageIndex, maxPageIndex);
        });
    };

     $scope.generatePageIndices = function(firstPage,lastPage){
        let arr = [];
        for (let i = firstPage; i<lastPage+1; i++){
            arr.push(i);
            }
        return arr;
     }


    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.showCart = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.Cart = response.data;
        });
    };

    $scope.putProductIntoCart = function (productId) {
        $http({
          url: contextPath + '/api/v1/cart/add/' + productId,
          method: 'GET'
        }).then(function (response) {
           $scope.showCart();
        });
    };

     $scope.removeProductFromCart = function (productId) {
            $http({
              url: contextPath + '/api/v1/cart/' + productId,
              method: 'DELETE'
            }).then(function (response) {
                $scope.showCart();
            });
     };

     $scope.clearCart = function () {
             $http.get(contextPath + '/api/v1/cart/clear')
                 .then(function (response) {
                     $scope.showCart();
                 });
     };

     $scope.checkout = function () {
                  $http.get(contextPath + '/api/v1/cart/checkout')
                      .then(function (response) {
                      });
          };

     $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $scope.clientname =  $scope.user.username;
                    $scope.user.username = null;
                    $scope.user.password = null;
                    $scope.authorized = true;
                }
            }, function errorCallback(response) {
                window.alert("Error");
            });
    };



    $scope.showCart();
    $scope.fillTable()

});