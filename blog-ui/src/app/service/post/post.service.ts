import { PostDetail } from './../../model/PostDetail';
import { Post } from './../../model/Post';
import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  
  private BASE_URL:string = "http://localhost:8081/";
  //private BASE_URL:string = "https://api.kayafirat.com/blog/";
  constructor(private http:HttpClient) { }

  public getPosts(pageNumber:number){
    return this.http.get<Post[]>(this.BASE_URL.concat("post?size=10&page="+pageNumber));
  }

  public getPostsByKeyword(pageNumber:number,keyword:string){
    return this.http.get<Post[]>(this.BASE_URL.concat("post/search?keyword="+keyword+"&size=10&page="+pageNumber));
  }

  public getPostsByCategoryName(pageNumber:number,keyword:string){
    return this.http.get<Post[]>(this.BASE_URL.concat("post/category?categoryName="+keyword+"&size=10&page="+pageNumber));
  }

  public getPostById(id:string){
    return this.http.get<PostDetail>(this.BASE_URL.concat("post/").concat(id));
  }
}
