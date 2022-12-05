import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccsAlertComponent } from './succs-alert.component';

describe('SuccsAlertComponent', () => {
  let component: SuccsAlertComponent;
  let fixture: ComponentFixture<SuccsAlertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuccsAlertComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccsAlertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
