import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerficationDialogueComponent } from './verfication-dialogue.component';

describe('VerficationDialogueComponent', () => {
  let component: VerficationDialogueComponent;
  let fixture: ComponentFixture<VerficationDialogueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerficationDialogueComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VerficationDialogueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
