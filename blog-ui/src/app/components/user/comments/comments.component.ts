import { CommentService } from './../../../service/comment/comment.service';
import { Comment } from './../../../model/Comment';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  public comments:Comment[] = [];
  constructor(private commentService:CommentService) { }

  ngOnInit(): void {
    this.commentService.getUserComments().subscribe((data)=> {this.comments = data;})
  }

}
