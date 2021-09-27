import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-publisher',
  templateUrl: './publisher.component.html',
  styleUrls: ['./publisher.component.css']
})
export class PublisherComponent implements OnInit {

  @Input() public body: String;
  @Input() public createdDate: Date;

  constructor() { }

  ngOnInit(): void {
  }

  public calculateReadTime(){
    const words = this.body?.trim().split(/\s+/).length;
    const time = Math.ceil(words / 255);
    return time+" min read"
  }

}
