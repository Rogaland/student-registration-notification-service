'use strict';

/**
 * @ngdoc function
 * @name adminFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the adminFrontendApp
 */
angular.module('adminFrontendApp')
  .controller('MainCtrl', function ($scope, AllReport, ActivationLink) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    $scope.sendActivationLink = function (cn, mobile) {

      ActivationLink.sendLink({id: cn, mobile: mobile});
    };

    AllReport.query(function(students) {
        console.log(students);
        $scope.students = students;
        $scope.studentCount = students.length;
    });

  });
