import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { CommentbarComponent } from './commentbar.component';

describe('CommentbarComponent', () => {
  let component: CommentbarComponent;
  let fixture: ComponentFixture<CommentbarComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ CommentbarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
