import { CommentService } from './../../../service/comment/comment.service';
import { Vote } from './../../../model/Vote';
import { Component, OnInit, Input, Output, OnChanges } from '@angular/core';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-commentbar',
  templateUrl: './commentbar.component.html',
  styleUrls: ['./commentbar.component.css']
})
export class CommentbarComponent implements OnInit,OnChanges {
  
  @Output() public isClose = new EventEmitter<boolean>();;
  @Input() public isOpen: boolean;
  @Input() public commentID: number;
  public oldCommentID:number;
  public pageNumber:number = 1;
  public totalPages:number;
  public totalElements:number;
  public votes:Vote[] = []
  constructor(private commentService:CommentService) { }

  ngOnInit(): void { }

  ngOnChanges(): void {
    this.votes.length = 0;
    this.pageNumber = 1;
    this.totalPages = 1;
    this.getVotes();

  }

  getVotes(){
    if(this.commentID !== 0){
      this.commentService.getCommentVotes(this.commentID,this.pageNumber).subscribe((data)=>
      {
        this.votes = this.votes.concat(data["content"]);
        this.totalPages=data["totalPages"];
        this.totalElements=data["totalElements"];

      });
    }
  }

  close(){
    this.isOpen=false;
    this.isClose.emit(true)
  }

  increasePageNumber(){
    this.pageNumber = this.pageNumber + 1;
    this.getVotes();
  }

}
