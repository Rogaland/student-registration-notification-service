'use strict';

describe('Service: UpdateMobile', function () {

  // load the service's module
  beforeEach(module('studentRegistrationNotificationServiceApp'));

  // instantiate service
  var UpdateMobile;
  beforeEach(inject(function (_UpdateMobile_) {
    UpdateMobile = _UpdateMobile_;
  }));

  it('should do something', function () {
    expect(!!UpdateMobile).toBe(true);
  });

});
