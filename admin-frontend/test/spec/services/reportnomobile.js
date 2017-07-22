'use strict';

describe('Service: reportNoMobile', function () {

  // load the service's module
  beforeEach(module('adminFrontendApp'));

  // instantiate service
  var reportNoMobile;
  beforeEach(inject(function (_reportNoMobile_) {
    reportNoMobile = _reportNoMobile_;
  }));

  it('should do something', function () {
    expect(!!reportNoMobile).toBe(true);
  });

});
