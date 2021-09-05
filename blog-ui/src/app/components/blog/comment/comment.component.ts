import { Comment } from './../../../model/Comment';
import { ActivatedRoute } from '@angular/router';
import { CommentService } from './../../../service/comment/comment.service';
import { AlertService } from './../../../service/alert/alert.service';
import { Component, Input, OnChanges, OnInit, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit,OnChanges {
  @Input() isFavourite: boolean;
  @Input() isHide: boolean;
  @Input() comments:Comment[] = [];
  @Input() totalPage:number;
  @Input() pageNumber:number = 1;
  @Output() pageNumberChange=new EventEmitter<number>();
  @Output() commentDetailID = new EventEmitter<number>();
  public totalPages:number[];
  private articleID:string;
  private votes:number[];
  public message:string = ""

  constructor(private alertService:AlertService,private commentService:CommentService,private route: ActivatedRoute) {
    this.votes = (localStorage.getItem("cv")) ?  JSON.parse(localStorage.getItem("cv")) : []
    this.route.paramMap.subscribe(params => {this.articleID = params.get('articleID');});
  }

  ngOnInit(): void { }
 
  ngOnChanges():void {
    this.totalPages = Array(this.totalPage).map((x)=>x);
  }
  updatePage(val:number){
    this.pageNumberChange.emit(val);
    this.alertService.notification("Sayfa değiştirildi.",true)
  }
  increaseVote(id:number,index:number){
    if(!this.isVoted(id)){
      this.commentService.increaseCommentVote(id).subscribe(
        (data) => {
          this.addLocalStorage(id);
          this.comments[index]["total_vote"] =this.comments[index]["total_vote"] + 1;
          this.alertService.notification("Yorum beğenildi.",true);
        });
    } else {
      this.alertService.notification("Aynı yorumu sadece bir kere beğenebilirsin.",false);
    }
  }
  isVoted(id:number){
    this.votes = (localStorage.getItem("cv")) ?  JSON.parse(localStorage.getItem("cv")) : []
    return (this.votes.includes(id)) ? true : false;
  }
  addLocalStorage(id:any){
    this.votes.push(id);
    localStorage.setItem("cv",JSON.stringify(this.votes));
  }
  sendComment(){
    this.commentService.sendComment(this.articleID,this.message).subscribe(
      (response)=>{
        let com:Comment = new Comment();
        com["id"]=response["id"];
        com["body"]=response["body"];
        com["total_vote"]=0;
        com["created_date"]=new Date();
        com["user_name"]=response["user_name"]
        this.comments.push(com);
        this.alertService.notification("Yorumunuz başarıyla gönderildi.",true)});
        this.message = ""

  }
  commentDetail(id:any){
    this.commentDetailID.emit(id);
  }


}
