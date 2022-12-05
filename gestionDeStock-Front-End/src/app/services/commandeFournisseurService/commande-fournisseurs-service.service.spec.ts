import { TestBed } from '@angular/core/testing';

import { CommandeFournisseursServiceService } from './commande-fournisseurs-service.service';

describe('CommandeFournisseursServiceService', () => {
  let service: CommandeFournisseursServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommandeFournisseursServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
