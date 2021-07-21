import { Comment } from './../../model/Comment';
import { Vote } from './../../model/Vote';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private BASE_URL:string = "http://localhost:8081/comment";
  // private BASE_URL:string = "https://api.kayafirat.com/blog/comment";
  constructor(private http:HttpClient) { }

  public getCommentsByPostId(id:any,pageNumber:any){
    return this.http.get<Comment>(this.BASE_URL.concat("?id="+id+"&page=").concat(pageNumber));
  }

  public getTotalCommentsByPostId(id:any){
    return this.http.get<number>(this.BASE_URL.concat("/total/"+id));
  }

  public increaseCommentVote(id:any){
    return this.http.post<Comment>(this.BASE_URL.concat("/vote/").concat(id),null);
  }

  public sendComment(postID:any,message:string){
    let body = {
      postId:postID,
      body:message
    }
    return this.http.post<Comment>(this.BASE_URL,body);
  }

  public getCommentVotes(id:any,pageNumber:any){
    return this.http.get<Vote[]>(this.BASE_URL.concat("/vote/").concat(id).concat("?page=").concat(pageNumber))
  }

  public getUserComments(){
    return this.http.get<Comment[]>(this.BASE_URL.concat('/user'))
  }
}
