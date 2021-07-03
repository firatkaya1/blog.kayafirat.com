import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsletterGeneralComponent } from './newsletter-general.component';

describe('NewsletterGeneralComponent', () => {
  let component: NewsletterGeneralComponent;
  let fixture: ComponentFixture<NewsletterGeneralComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsletterGeneralComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsletterGeneralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
