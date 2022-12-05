import { TestBed } from '@angular/core/testing';

import { CommandeClientServiceService } from './commande-client-service.service';

describe('CommandeClientServiceService', () => {
  let service: CommandeClientServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommandeClientServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
