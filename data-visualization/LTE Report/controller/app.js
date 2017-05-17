function parseDate(dateStr) {
    var date = dateStr.split('/');
    var month = date[0] - 1; //january is 0 in js;
    var day = date[1];
    var year = date[2];
    return new Date(year, month, day);
}
var myApp = angular.module('shrunkApp', ['ngAnimate', 'ui.bootstrap', 'ngRoute', 'chart.js']);
myApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/epa.html'
        , controller: 'indexCtrl'
    }).when('/epa', {
        templateUrl: 'views/epa.html'
        , controller: 'indexCtrl'
    }).when('/imei', {
        templateUrl: 'views/imei.html'
        , controller: 'indexCtrl'
    }).when('/volte', {
        templateUrl: 'views/volte.html'
        , controller: 'indexCtrl'
    }).otherwise({
        redirectTo: 'views/epa.html'
        , controller: 'indexCtrl'
    })
}]);
myApp.filter('beginWith', function () {
    return function (data, start) {
        return data.slice(start);
    }
});
myApp.service('GetData', function ($http) {
    var self = this;
    self.getTableJson = function () {
        var promise1 = $http.get('../data/table.json').then(function (response) {
            return response.data;
        });
        return promise1;
    }
});
myApp.service('SelectDate', function () {
    var self = this;
    self.getNewArray = function (dataArray, dateStart, dateEnd) {
        var newArray = [];
        var index;
        for (index in dataArray) {
            var thisobj = dataArray[index];
            var date = parseDate(thisobj.date);
            var sd = dateStart.getDate();
            var sm = dateStart.getMonth() - 1;
            var sy = dateStart.getFullYear();
            var ed = dateEnd.getDate();
            var em = dateEnd.getMonth() - 1;
            var ey = dateEnd.getFullYear();
            if (date >= new Date(sy, sm, sd) && date <= new Date(ey, em, ed)) {
                newArray.push(thisobj);
            }
        }
        return newArray;
    }
});
myApp.controller('indexCtrl', ['$scope', '$http', 'GetData', function ($scope, $http, GetData) {
    var closesidenav = document.getElementById("closesidenav")
        , openicon = document.getElementById("openicon")
        , sidebar = document.getElementById("sidebar-wrapper")
        , shrunk = document.getElementById("shrunk")
        , dialogoverlay = document.getElementById('dialogoverlay')
        , navlink = document.querySelectorAll(".navlink")
        , winW = window.innerWidth
        , winH = window.innerHeight;

    function openNav() {
        sidebar.style.marginLeft = "0px";
        shrunk.style.width = winW + "px";
        shrunk.style.marginLeft = "340px";
        dialogoverlay.style.display = "block";
        dialogoverlay.style.height = winH + "px";
        dialogoverlay.style.width = winW + "px";
        document.body.style.height = winH + "px";
        document.body.style.overflow = "hidden";
    };

    function closeNav() {
        sidebar.style.marginLeft = "-340px";
        shrunk.style.marginLeft = "0";
        dialogoverlay.style.display = "none";
        document.body.style.overflowY = "scroll";
    };
    openicon.onclick = function () {
        openNav();
    };
    for (i = 0; i < navlink.length; i++) {
        navlink[i].onclick = function () {
            closeNav();
        };
    }
    dialogoverlay.onclick = function () {
        closeNav();
    };
    $scope.tableData = [];
    GetData.getTableJson().then(function (data) {
        $scope.tableData = data;
    });
}]);
myApp.controller('viewController', ['$scope', '$routeParams', function ($scope, $rootScope, $routeParams) {
    //    $scope.result = $rootScope.results[$routeParams.id];
}]);
myApp.controller('epaCtrl', ['$scope', function ($scope) {}]);
myApp.controller('voLTE', ['$scope', 'SelectDate', function ($scope, SelectDate) {
    $scope.pageSize = 10;
    $scope.currentPage = 1;
    $scope.filteredData = [];
    $scope.newDataset = function () {
        $scope.filteredData = SelectDate.getNewArray($scope.tableData, $scope.dateStart, $scope.dateEnd);
        $scope.tableData = $scope.filteredData;
    }
}]);
myApp.controller('imeiCtrl', ['$scope', 'SelectDate', 'GetData', function ($scope, SelectDate, GetData) {
    $scope.filteredData = [];
    $scope.newDataset = function () {
        $scope.filteredData = SelectDate.getNewArray($scope.tableData, $scope.dateStart, $scope.dateEnd);
        $scope.tableData = $scope.filteredData;
        $scope.dateArr = [];
        for (var i = 0; i < $scope.tableData.length; i++) {
            $scope.dateArr.push($scope.tableData[i].date);
        }
        $scope.kpi1Arr = [];
        for (var i = 0; i < $scope.tableData.length; i++) {
            $scope.kpi1Arr.push($scope.tableData[i].kpi1);
        }
        $scope.kpi2Arr = [];
        for (var i = 0; i < $scope.tableData.length; i++) {
            $scope.kpi2Arr.push($scope.tableData[i].kpi2);
        }
        $scope.colors = ['#45b7cd', '#ff6384', '#ff8e72'];
        $scope.onClick = function (points, evt) {
            console.log(points, evt);
        };
        var linecanvas = document.getElementById("line")
            , barcanvas = document.getElementById("bar");
        if ($scope.chartType == "bar") {
            $scope.barlabels = $scope.dateArr;
            $scope.barseries = ['KPI 1', 'KPI 2'];
            $scope.bardata = [$scope.kpi1Arr, $scope.kpi2Arr];
            $scope.labels = null;
            $scope.series = null;
            $scope.data = null;
            linecanvas.style.position = "fixed";
            linecanvas.style.zIndex = "-20";
            barcanvas.style.position = "relative";
            barcanvas.style.zIndex = "0";
        }
        if ($scope.chartType == "line") {
            $scope.labels = $scope.dateArr;
            $scope.series = ['KPI 1', 'KPI 2'];
            $scope.data = [$scope.kpi1Arr, $scope.kpi2Arr];
            $scope.barlabels = null;
            $scope.barseries = null;
            $scope.bardata = null;
            barcanvas.style.position = "fixed";
            barcanvas.style.zIndex = "-20";
            linecanvas.style.position = "relative";
            linecanvas.style.zIndex = "0";
        }
        $scope.datasetOverride = [{
            yAxisID: 'y-axis-1'
        }, {
            yAxisID: 'y-axis-2'
        }];
        $scope.options = {
            scales: {
                yAxes: [
                    {
                        id: 'y-axis-1'
                        , type: 'linear'
                        , display: true
                        , position: 'left'
                }
                    , {
                        id: 'y-axis-2'
                        , type: 'linear'
                        , display: false
                        , position: 'right'
                }
              ]
            }
        };
    }
}]);