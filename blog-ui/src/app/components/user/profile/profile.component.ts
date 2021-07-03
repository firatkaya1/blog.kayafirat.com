import { User } from './../../../model/User';
import { UserService } from './../../../service/user/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  public user:User;

  constructor(private userService:UserService) { }

  ngOnInit(): void {
    this.userService.getUser().subscribe((data)=> {this.user = data; });
  }
  
}
