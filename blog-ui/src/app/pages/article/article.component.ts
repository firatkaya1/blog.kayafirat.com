import { AuthService } from './../../service/auth/AuthenticateService';
import { UserService } from './../../service/user/user.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { CommentService } from './../../service/comment/comment.service';
import { PostDetail } from './../../model/PostDetail';
import { PostService } from './../../service/post/post.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Title, Meta } from '@angular/platform-browser';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})
export class ArticleComponent implements OnInit {

  public postDetail:PostDetail;
  public articleID:string;
  public pageNumber:number = 1;
  public comments:Comment[] = [];
  public isHide:boolean=false;
  public isOpen:boolean=false;
  public totalPage:number = 0;
  public isFirst:boolean = true;
  public totalComment:number  = 0;
  public commentID:number = 0;

  constructor(private route: ActivatedRoute,
    private authenticateService:AuthService,
    private userService:UserService,
    private postService:PostService,
    private commentService:CommentService,
    private spinner: NgxSpinnerService,
    private titleService: Title,
    private metaService: Meta,) { 
    this.spinner.show();
    this.route.paramMap.subscribe(params => {this.articleID = params.get('articleID');});
    this.getPostDetail();
  }

  ngOnInit(): void {
    this.commentService.getTotalCommentsByPostId(this.articleID).subscribe((data) => {this.totalComment = data;} )
  }

  getPostDetail(){
    this.postService.getPostById(this.articleID).subscribe(
      (data) => 
      {this.postDetail = data;
        this.addMetaTags(this.postDetail)
        this.spinner.hide(); });
  }

  getComments(){
    if(this.isFirst){
      this.commentService.getCommentsByPostId(this.articleID,this.pageNumber)
      .subscribe((data)=> {
      this.comments = data["content"];
      this.isHide = true;
      this.totalPage=data["totalPages"];
      this.isFirst = false;
    });
    }

    this.isHide = true;
  }

  hideComments(){
    this.isHide=false;
  }

  changePage(page:number){
    this.pageNumber = page;
    this.isFirst = true;
    this.getComments();
  }

  commentDetails(id:any){
    this.commentID = id;
    this.isOpen = true;
  }

  closedCommentBar(val){
    this.isOpen = false;
    this.commentID = 0;
  }

  addMetaTags(post:PostDetail){
    if(this.authenticateService.getLoggedIn()) {
      this.userService.hasNotification().subscribe(data => {
        if(data != 0){
          this.titleService.setTitle(this.postDetail.post.title+"("+data+")");
        } else {
          this.titleService.setTitle(this.postDetail.post.title);
        }
      })
    }
    var values = [
      {name: 'keywords', content: post.meta.googleSEO.keywords},
      {name: 'description', content: post.meta.googleSEO.description},
      {name: 'author', content: '1firatkaya'},
      {name: 'og:locale', content: 'tr_TR'},
      {name: 'og:type', content: post.meta.facebookSEO.type},
      {name: 'og:title', content: post.meta.facebookSEO.title},
      {name: 'og:description', content: post.meta.facebookSEO.description},
      {name: 'og:site_name', content: post.meta.facebookSEO.siteName},
      {name: 'og:image', content: post.meta.facebookSEO.image},
      {name: 'twitter:card', content: post.meta.twitterSEO.card},
      {name: 'twitter:site', content: post.meta.facebookSEO.siteName},
      {name: 'twitter:title', content: post.meta.twitterSEO.title},
      {name: 'twitter:description', content: post.meta.twitterSEO.description},
      {name: 'twitter:image', content: post.meta.twitterSEO.image},
      {name: 'twitter:image:alt', content: post.meta.twitterSEO.title},
      {name: 'twitter:creator', content: post.meta.twitterSEO.creator},
      {name: 'robots', content: 'index, follow'}
    ]
    localStorage.setItem('dynamic-seo',JSON.stringify(values))
    this.metaService.addTags(values);
  }

  goUp(){
    window.scroll(0,0);
  }


}
