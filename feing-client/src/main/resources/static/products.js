angular.module('app', []).controller('indexController', function ($scope, $http) {

    $scope.fillTable = function (current_page = 1) {
        $http({
            url: 'http://localhost:8190/products',
            method: 'GET'
        }).then(function (response) {
            $scope.ProductList = response.data;
        });
    };

     $scope.fillTable()
});