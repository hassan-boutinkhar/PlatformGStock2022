import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MvstocksComponent } from './mvstocks.component';

describe('MvstocksComponent', () => {
  let component: MvstocksComponent;
  let fixture: ComponentFixture<MvstocksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MvstocksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MvstocksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
