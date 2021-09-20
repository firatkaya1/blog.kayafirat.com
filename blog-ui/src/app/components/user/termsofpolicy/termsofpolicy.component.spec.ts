import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { TermsofpolicyComponent } from './termsofpolicy.component';

describe('TermsofpolicyComponent', () => {
  let component: TermsofpolicyComponent;
  let fixture: ComponentFixture<TermsofpolicyComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ TermsofpolicyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TermsofpolicyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
