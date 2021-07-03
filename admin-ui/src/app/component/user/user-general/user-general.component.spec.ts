import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserGeneralComponent } from './user-general.component';

describe('UserGeneralComponent', () => {
  let component: UserGeneralComponent;
  let fixture: ComponentFixture<UserGeneralComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserGeneralComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserGeneralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
