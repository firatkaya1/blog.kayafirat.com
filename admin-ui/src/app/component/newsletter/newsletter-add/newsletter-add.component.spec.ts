import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsletterAddComponent } from './newsletter-add.component';

describe('NewsletterAddComponent', () => {
  let component: NewsletterAddComponent;
  let fixture: ComponentFixture<NewsletterAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsletterAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsletterAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
