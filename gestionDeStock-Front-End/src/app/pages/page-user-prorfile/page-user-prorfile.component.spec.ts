import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageUserProrfileComponent } from './page-user-prorfile.component';

describe('PageUserProrfileComponent', () => {
  let component: PageUserProrfileComponent;
  let fixture: ComponentFixture<PageUserProrfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageUserProrfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageUserProrfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
