'use strict';

/**
 * @ngdoc service
 * @name adminFrontendApp.reportNoMobile
 * @description
 * # reportNoMobile
 * Factory in the adminFrontendApp.
 */
angular.module('adminFrontendApp')
  .factory('NoMobileReport', function ($resource) {
      return $resource('/admin/srns/report/no/mobile');
  });
