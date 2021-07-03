import { AlertService } from './../../../service/alert/alert.service';
import { UserService } from './../../../service/user/user.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-mail-settings',
  templateUrl: './mail-settings.component.html',
  styleUrls: ['./mail-settings.component.css']
})
export class MailSettingsComponent implements OnInit {

  constructor(private userService:UserService,private alertService:AlertService) { }

  userMailPermission = new FormGroup({
    loginAttempt: new FormControl(),
    loginNotification:new FormControl(),
    passChange:new FormControl(),
    postNotification:new FormControl()
  }); 

  ngOnInit(): void { 
    this.getuserMailPermission();
  }

  getuserMailPermission(){
    this.userService.getUserMailPermissions().subscribe(
      (data) => 
      {
        this.userMailPermission.controls['loginAttempt'].setValue(data.loginAttempt);
        this.userMailPermission.controls['loginNotification'].setValue(data.loginNotification);
        this.userMailPermission.controls['passChange'].setValue(data.passChange);
        this.userMailPermission.controls['postNotification'].setValue(data.postNotification);
      })
  }

  ngSubmit() {
    let body = {
      loginAttempt: this.userMailPermission.controls['loginAttempt'].value,
      loginNotification: this.userMailPermission.controls['loginNotification'].value,
      passChange: this.userMailPermission.controls['passChange'].value,
      postNotification: this.userMailPermission.controls['postNotification'].value,
    }
    this.userService.setUserMailPermissions(body).subscribe((data)=> 
    {
      this.userMailPermission.controls['loginAttempt'].setValue(data.loginAttempt);
      this.userMailPermission.controls['loginNotification'].setValue(data.loginNotification);
      this.userMailPermission.controls['passChange'].setValue(data.passChange);
      this.userMailPermission.controls['postNotification'].setValue(data.postNotification);
     
      this.alertService.notification("Mail tercihleri başarıyla güncellendi.",true);
    },(error) => {
      this.alertService.notification("Beklenmedik bir hata meydana geldi.",false);
    } );

  }
}
