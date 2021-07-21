import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-comment-update',
  templateUrl: './comment-update.component.html',
  styleUrls: ['./comment-update.component.css']
})
export class CommentUpdateComponent implements OnInit {

  public posts:any[] = []

  constructor(private postService:PostService) { 
    this.postService.getPosts().subscribe(data => {
      this.posts = data
      console.log(data)
    })
   }

  ngOnInit(): void { }

}
