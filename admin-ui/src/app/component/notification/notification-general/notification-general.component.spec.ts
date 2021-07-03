import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NotificationGeneralComponent } from './notification-general.component';

describe('NotificationGeneralComponent', () => {
  let component: NotificationGeneralComponent;
  let fixture: ComponentFixture<NotificationGeneralComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NotificationGeneralComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NotificationGeneralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
