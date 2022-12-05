import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageNoveauCommandeClientComponent } from './page-noveau-commande-client.component';

describe('PageNoveauCommandeClientComponent', () => {
  let component: PageNoveauCommandeClientComponent;
  let fixture: ComponentFixture<PageNoveauCommandeClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageNoveauCommandeClientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageNoveauCommandeClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
