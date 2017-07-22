'use strict';

describe('Service: ActivationLink', function () {

  // load the service's module
  beforeEach(module('adminFrontendApp'));

  // instantiate service
  var ActivationLink;
  beforeEach(inject(function (_ActivationLink_) {
    ActivationLink = _ActivationLink_;
  }));

  it('should do something', function () {
    expect(!!ActivationLink).toBe(true);
  });

});
