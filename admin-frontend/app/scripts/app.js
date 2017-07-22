'use strict';

/**
 * @ngdoc overview
 * @name adminFrontendApp
 * @description
 * # adminFrontendApp
 *
 * Main module of the application.
 */
angular
  .module('adminFrontendApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/sms', {
        templateUrl: 'views/sms.html',
        controller: 'SmsCtrl',
        controllerAs: 'sms'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
