import { TestBed } from '@angular/core/testing';

import { FournisseurServicesService } from './fournisseur-services.service';

describe('FournisseurServicesService', () => {
  let service: FournisseurServicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FournisseurServicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
