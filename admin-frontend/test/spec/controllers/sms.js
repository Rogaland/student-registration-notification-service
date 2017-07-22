'use strict';

describe('Controller: SmsCtrl', function () {

  // load the controller's module
  beforeEach(module('adminFrontendApp'));

  var SmsCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SmsCtrl = $controller('SmsCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SmsCtrl.awesomeThings.length).toBe(3);
  });
});
