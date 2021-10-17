import { Title } from '@angular/platform-browser';
import { AlertService } from './../../service/alert/alert.service';
import { Post } from './../../model/Post';
import { PostService } from './../../service/post/post.service';
import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService }   from "ngx-spinner";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public posts:Post[] = [];
  public postsCategory:Post[] = [];
  public postsKeyword:Post[] = [];
  public pageNumber:number = 1;
  public pageNumberofCategory:number = 1;
  public pageNumberofSearch:number = 1;
  public globalCategoryKeyword:string;
  public globalSearchKeyword:string;
  public totalPages:number = 0;

  constructor(private postService:PostService,private alertService:AlertService,private spinner: NgxSpinnerService,private titleService:Title) { 
    this.titleService.setTitle("Blog | kayafirat.com");

    this.spinner.show();
  }

  ngOnInit(): void {
    this.getPosts();
  }

  getPosts(){
    this.postService.getPosts(this.pageNumber).subscribe((data) => 
    { 
      this.posts = this.posts.concat(data["content"]); 
      this.totalPages =data["totalPages"];
      this.globalCategoryKeyword = "";
      this.globalSearchKeyword = "";
      this.spinner.hide();
    });  
  }

  updatePage(val:number){
    if(val === 1){
      this.pageNumber = this.pageNumber + 1;
      this.getPosts();
    } else if(val == 2){
      this.pageNumberofCategory = this.pageNumberofCategory + 1;
      this.getPostsByCategoryName(this.globalCategoryKeyword);
    } else if (val == 3){
      this.pageNumberofSearch = this.pageNumberofSearch + 1;
      this.getPostsByKeyword(this.globalSearchKeyword);
    }

  }

  getPostsByKeyword(keyword:string){
    this.spinner.show();

    if(keyword.trim() != "" ){
      this.postService.getPostsByKeyword(this.pageNumberofSearch,keyword).subscribe((data) => 
    { 
      this.postsKeyword = this.postsKeyword.concat(data["content"]);
      this.totalPages =data["totalPages"];
      if(data["totalElements"] === 0) {
        this.alertService.notification(keyword+" kelimesinde toplam 0 adet sonuç bulundu.",false);
      } else {
        this.alertService.notification("Toplam "+data["totalElements"]+" adet sonuç bulundu.",true);
      }
      this.posts = this.postsKeyword;
      this.spinner.hide();

    }); 
    } else {
      this.alertService.notification("Aramak istenilen alana kelime giriniz.",false);
      this.spinner.hide();
    }
    if(this.globalSearchKeyword != keyword){
      this.postsKeyword = [];
    }
    this.globalSearchKeyword = keyword;
    this.globalCategoryKeyword = "";
  }

  getPostsByCategoryName(keyword:string){
    this.spinner.show();
    this.postService.getPostsByCategoryName(this.pageNumberofCategory,keyword).subscribe((data) => 
    { 
      this.postsCategory = this.postsCategory.concat(data["content"]);
      this.totalPages = data["totalPages"];
      if(data["totalElements"] === 0) {
        this.alertService.notification(keyword+" kelimesinde toplam 0 adet sonuç bulundu.",false);
      } else {
        this.alertService.notification("Toplam "+data["totalElements"]+" adet sonuç bulundu.",true);
      }
      this.posts = this.postsCategory;
      this.spinner.hide();

    }); 
    if(this.globalCategoryKeyword != keyword){
      this.postsCategory = [];
    }
    this.globalCategoryKeyword = keyword;
    this.globalSearchKeyword = "";
  }

  clear(){
    this.pageNumber = 1;
    this.getPosts();
  }

}
