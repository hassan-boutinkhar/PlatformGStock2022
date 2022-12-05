import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageDetailsCommandesFounisseurComponent } from './page-details-commandes-founisseur.component';

describe('PageDetailsCommandesFounisseurComponent', () => {
  let component: PageDetailsCommandesFounisseurComponent;
  let fixture: ComponentFixture<PageDetailsCommandesFounisseurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageDetailsCommandesFounisseurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageDetailsCommandesFounisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
