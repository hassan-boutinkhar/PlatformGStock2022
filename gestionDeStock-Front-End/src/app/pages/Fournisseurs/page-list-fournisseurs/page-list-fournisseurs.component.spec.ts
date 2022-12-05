import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageListFournisseursComponent } from './page-list-fournisseurs.component';

describe('PageListFournisseursComponent', () => {
  let component: PageListFournisseursComponent;
  let fixture: ComponentFixture<PageListFournisseursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageListFournisseursComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageListFournisseursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
