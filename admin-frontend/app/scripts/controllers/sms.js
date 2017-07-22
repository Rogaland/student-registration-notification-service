'use strict';

/**
 * @ngdoc function
 * @name adminFrontendApp.controller:SmsCtrl
 * @description
 * # SmsCtrl
 * Controller of the adminFrontendApp
 */
angular.module('adminFrontendApp')
  .controller('SmsCtrl', function ($scope, Sms) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];



    var refreshSmsList = function() {
      Sms.query(function(sms) {
        $scope.smsList = sms;
        $scope.smsListCount = sms.length;
      });
    };

    refreshSmsList();

    $scope.emptyQueue = function() {
      Sms.delete();
      refreshSmsList();
    };

    $scope.sendQueue = function () {
      Sms.save();
      refreshSmsList();
    };

    $scope.refreshQueue = function () {
      refreshSmsList();
    };

    $scope.getMobile = function (mobile) {
      return mobile.replace('sMobileNumber=', '');
    };

    $scope.getMessage = function (message) {
      return message.replace('sMessage=', '');
    };

  });
