'use strict';

/**
 * @ngdoc service
 * @name adminFrontendApp.reportAll
 * @description
 * # reportAll
 * Factory in the adminFrontendApp.
 */
angular.module('adminFrontendApp')
  .factory('AllReport', function ($resource) {
      return $resource('/admin/students');
  });
