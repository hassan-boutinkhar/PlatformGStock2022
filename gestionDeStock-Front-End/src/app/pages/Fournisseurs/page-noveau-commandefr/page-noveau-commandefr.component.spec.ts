import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageNoveauCommandefrComponent } from './page-noveau-commandefr.component';

describe('PageNoveauCommandefrComponent', () => {
  let component: PageNoveauCommandefrComponent;
  let fixture: ComponentFixture<PageNoveauCommandefrComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageNoveauCommandefrComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageNoveauCommandefrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
