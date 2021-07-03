import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl,Validators } from '@angular/forms';

@Component({
  selector: 'app-newsletter-add',
  templateUrl: './newsletter-add.component.html',
  styleUrls: ['./newsletter-add.component.css']
})
export class NewsletterAddComponent implements OnInit {

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
