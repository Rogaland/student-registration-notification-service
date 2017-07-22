'use strict';

describe('Service: reportAll', function () {

  // load the service's module
  beforeEach(module('adminFrontendApp'));

  // instantiate service
  var reportAll;
  beforeEach(inject(function (_reportAll_) {
    reportAll = _reportAll_;
  }));

  it('should do something', function () {
    expect(!!reportAll).toBe(true);
  });

});
