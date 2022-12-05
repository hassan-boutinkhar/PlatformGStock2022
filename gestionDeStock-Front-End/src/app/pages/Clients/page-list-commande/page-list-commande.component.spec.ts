import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageListCommandeComponent } from './page-list-commande.component';

describe('PageListCommandeComponent', () => {
  let component: PageListCommandeComponent;
  let fixture: ComponentFixture<PageListCommandeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageListCommandeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageListCommandeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
