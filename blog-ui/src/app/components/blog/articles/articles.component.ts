import { PostDetail } from './../../../model/PostDetail';
import { Component, OnInit, Input } from '@angular/core';
import { DomSanitizer,SafeHtml } from '@angular/platform-browser';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent implements OnInit {

  @Input() public postDetail: PostDetail;
  @Input() public totalComment: number;
  
  constructor(private sanitizer:DomSanitizer) { }

  ngOnInit(): void { }

  public sanitize(html: string): SafeHtml {
    return this.sanitizer.bypassSecurityTrustHtml(html);
}
}
