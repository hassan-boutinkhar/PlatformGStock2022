import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageAjouteFournisseurComponent } from './page-ajoute-fournisseur.component';

describe('PageAjouteFournisseurComponent', () => {
  let component: PageAjouteFournisseurComponent;
  let fixture: ComponentFixture<PageAjouteFournisseurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageAjouteFournisseurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageAjouteFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
