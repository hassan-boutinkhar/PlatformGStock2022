import { TestBed } from '@angular/core/testing';

import { ArticleResolvServiceService } from './article-resolv-service.service';

describe('ArticleResolvServiceService', () => {
  let service: ArticleResolvServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ArticleResolvServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
