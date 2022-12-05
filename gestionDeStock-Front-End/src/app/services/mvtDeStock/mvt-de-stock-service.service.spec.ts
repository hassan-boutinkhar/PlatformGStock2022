import { TestBed } from '@angular/core/testing';

import { MvtDeStockServiceService } from './mvt-de-stock-service.service';

describe('MvtDeStockServiceService', () => {
  let service: MvtDeStockServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MvtDeStockServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
