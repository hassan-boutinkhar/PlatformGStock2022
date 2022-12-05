import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageAjouteClientComponent } from './page-ajoute-client.component';

describe('PageAjouteClientComponent', () => {
  let component: PageAjouteClientComponent;
  let fixture: ComponentFixture<PageAjouteClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageAjouteClientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageAjouteClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
