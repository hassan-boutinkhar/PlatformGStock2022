import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCompenentComponent } from './user-compenent.component';

describe('UserCompenentComponent', () => {
  let component: UserCompenentComponent;
  let fixture: ComponentFixture<UserCompenentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserCompenentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserCompenentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
