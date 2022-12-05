import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageAjouterArticleComponent } from './page-ajouter-article.component';

describe('PageAjouterArticleComponent', () => {
  let component: PageAjouterArticleComponent;
  let fixture: ComponentFixture<PageAjouterArticleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageAjouterArticleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageAjouterArticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
