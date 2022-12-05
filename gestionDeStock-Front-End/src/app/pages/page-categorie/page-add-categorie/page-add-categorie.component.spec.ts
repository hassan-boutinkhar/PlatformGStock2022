import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageAddCategorieComponent } from './page-add-categorie.component';

describe('PageAddCategorieComponent', () => {
  let component: PageAddCategorieComponent;
  let fixture: ComponentFixture<PageAddCategorieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageAddCategorieComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageAddCategorieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
