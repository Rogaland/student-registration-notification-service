'use strict';

/**
 * @ngdoc service
 * @name adminFrontendApp.ActivationLink
 * @description
 * # ActivationLink
 * Factory in the adminFrontendApp.
 */
angular.module('adminFrontendApp')
  .factory('ActivationLink', function ($resource) {
    return $resource('/admin/activation/link/send', {id:'@id', mobile: '@mobile'},
      {sendLink: {method:'POST'}}
    );
  });
