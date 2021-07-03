import { Post } from './../../../model/Post';
import { Component, OnInit, Output, Input } from '@angular/core';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  @Input() public posts: Post[] = [];
  @Output() categoryName = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
  }

  search(val:string){
    this.categoryName.emit(val);
  }


}
