import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl,Validators } from '@angular/forms';

@Component({
  selector: 'app-post-update',
  templateUrl: './post-update.component.html',
  styleUrls: ['./post-update.component.css']
})
export class PostUpdateComponent implements OnInit {

  public test:string = 'lan';

  constructor() { }

  postDetail = new FormGroup({
    postBody:new FormControl('',[])}); 

  ngOnInit(): void {
  }
  
  tikla(){
    console.log(this.postDetail.get('postBody').value)
  }
}
