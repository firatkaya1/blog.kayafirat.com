import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable }              from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  //private BASE_URL = "https://api.kayafirat.com/blog/";
  private BASE_URL = "http://localhost:8081/";


  constructor(private http:HttpClient) { }

  savePost(body:object)  {
    return this.http.post<any>(this.BASE_URL.concat("post"),body);
  }

  getCategories(){
    return this.http.get<any>(this.BASE_URL.concat("category"))
  }

  getPosts(){
    return this.http.get<any>(this.BASE_URL.concat("post/all"))
  }
}