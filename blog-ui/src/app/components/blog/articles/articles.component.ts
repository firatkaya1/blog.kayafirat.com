import { PostDetail } from './../../../model/PostDetail';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent implements OnInit {

  @Input() public postDetail: PostDetail;
  @Input() public totalComment: number;

  constructor() { }

  ngOnInit(): void { }

}
