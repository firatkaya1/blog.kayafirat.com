import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent implements OnInit {

  public isShow:boolean = true
  private BASE_URL:string = "https://api.kayafirat.com/blog/post";

  constructor(public router:Router,private http:HttpClient) { 
    this.http.get<any>(this.BASE_URL+"/config/status").subscribe((data) => { this.isShow = data.status == "true"})
  }

  ngOnInit(): void {
  }

}
