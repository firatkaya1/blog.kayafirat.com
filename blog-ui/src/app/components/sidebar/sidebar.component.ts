import { User } from './../../model/User';
import { UserService } from './../../service/user/user.service';
import { Component, OnChanges, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  public hasNotification:Number = -1;
  public user: User = {} as User;
  public selectedImage:File;

  constructor(private userService:UserService) { }

  ngOnInit(): void {
    this.userService.hasNotification().subscribe((data)=> {this.hasNotification = data;});
    this.userService.getUser().subscribe((data)=> {this.user  = data})
  }

  onFileChanged(event) {
    this.selectedImage = event.target.files[0]
    console.log(this.selectedImage)
    this.userService.updateUserProfilPhoto(this.selectedImage).subscribe(data => {});

  }
}
