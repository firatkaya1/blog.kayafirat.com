import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentGeneralComponent } from './comment-general.component';

describe('CommentGeneralComponent', () => {
  let component: CommentGeneralComponent;
  let fixture: ComponentFixture<CommentGeneralComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommentGeneralComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentGeneralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
