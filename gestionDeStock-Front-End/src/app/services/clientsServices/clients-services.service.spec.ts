import { TestBed } from '@angular/core/testing';

import { ClientsServicesService } from './clients-services.service';

describe('ClientsServicesService', () => {
  let service: ClientsServicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientsServicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
