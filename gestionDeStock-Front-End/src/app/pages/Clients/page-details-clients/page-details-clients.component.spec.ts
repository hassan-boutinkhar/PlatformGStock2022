import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageDetailsClientsComponent } from './page-details-clients.component';

describe('PageDetailsClientsComponent', () => {
  let component: PageDetailsClientsComponent;
  let fixture: ComponentFixture<PageDetailsClientsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageDetailsClientsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageDetailsClientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
