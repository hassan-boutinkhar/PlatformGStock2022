import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageListCommandeFournisseurComponent } from './page-list-commande-fournisseur.component';

describe('PageListCommandeFournisseurComponent', () => {
  let component: PageListCommandeFournisseurComponent;
  let fixture: ComponentFixture<PageListCommandeFournisseurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageListCommandeFournisseurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageListCommandeFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
