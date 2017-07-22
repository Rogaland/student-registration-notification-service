'use strict';

/**
 * @ngdoc service
 * @name adminFrontendApp.sms
 * @description
 * # sms
 * Factory in the adminFrontendApp.
 */
angular.module('adminFrontendApp')
  .factory('Sms', function ($resource) {
    return $resource('/admin/sms');
  });
