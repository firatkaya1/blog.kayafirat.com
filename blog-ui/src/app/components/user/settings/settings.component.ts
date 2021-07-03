import { UserService } from './../../../service/user/user.service';
import { User } from './../../../model/User';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  public user:User;

  constructor(private userService:UserService) { }

  userProfile = new FormGroup({
    username: new FormControl(),
    contactAddress:new FormControl(),
    email:new FormControl(),
    birthDate:new FormControl(),
    githubAddress:new FormControl(''),
    linkedinAddress:new FormControl('')
  }); 

  ngOnInit(): void {
    this.userService.getUser().subscribe((data)=> 
    { 
      this.user = data;
      this.user.contactAddress = (data.contactAddress) ? data.contactAddress : '';
      this.user.linkedinAddress = (data.birthDate) ? data.birthDate : '';
      this.user.githubAddress = (data.githubAddress) ? data.githubAddress : '';
      this.user.linkedinAddress = (data.linkedinAddress) ? data.linkedinAddress : '';

      this.userProfile.controls['username'].setValue(this.user?.username);
      this.userProfile.controls['contactAddress'].setValue(this.user?.contactAddress);
      this.userProfile.controls['email'].setValue(this.user?.email);
      this.userProfile.controls['birthDate'].setValue(this.user?.birthDate);
      this.userProfile.controls['githubAddress'].setValue(this.user?.githubAddress);
      this.userProfile.controls['linkedinAddress'].setValue(this.user.linkedinAddress);
    });
  }

  ngOnSubmit():void {
    let body = { 
      username: (this.userProfile.controls['username'].value) ? this.userProfile.controls['username'].value : " ",
      email:this.userProfile.controls['email'].value,
      birthDate:this.userProfile.controls['birthDate'].value,
      contactAddress: this.userProfile.controls['contactAddress'].value,
      githubAddress: this.userProfile.controls['githubAddress'].value,
      linkedinAddress: this.userProfile.controls['linkedinAddress'].value,
    }
    this.userService.setUserProfile(body).subscribe((data)=> 
    {

    });
  }

}
