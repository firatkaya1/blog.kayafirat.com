import { AlertService } from './../../../service/alert/alert.service';
import { UserService } from './../../../service/user/user.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-notification-settings',
  templateUrl: './notification-settings.component.html',
  styleUrls: ['./notification-settings.component.css']
})
export class NotificationSettingsComponent implements OnInit {

  constructor(private userService:UserService,private alertService:AlertService) { }

  userNotificationPermission = new FormGroup({
    loginAttempt: new FormControl(),
    loginNotification:new FormControl(),
    passChange:new FormControl(),
    postNotification:new FormControl()
  }); 

  ngOnInit(): void { 
    this.getuserNotificationPermission();
  }

  getuserNotificationPermission(){
    this.userService.getUserNotificationPermissions().subscribe(
      (data) => 
      {
        this.userNotificationPermission.controls['loginAttempt'].setValue(data.loginAttempt);
        this.userNotificationPermission.controls['loginNotification'].setValue(data.loginNotification);
        this.userNotificationPermission.controls['passChange'].setValue(data.passChange);
        this.userNotificationPermission.controls['postNotification'].setValue(data.postNotification);
      })
  }

  ngSubmit() {
    let body = {
      loginAttempt: this.userNotificationPermission.controls['loginAttempt'].value,
      loginNotification: this.userNotificationPermission.controls['loginNotification'].value,
      passChange: this.userNotificationPermission.controls['passChange'].value,
      postNotification: this.userNotificationPermission.controls['postNotification'].value,
    }
    this.userService.setUserNotificationPermissions(body).subscribe((data)=> 
    {
      this.userNotificationPermission.controls['loginAttempt'].setValue(data.loginAttempt);
      this.userNotificationPermission.controls['loginNotification'].setValue(data.loginNotification);
      this.userNotificationPermission.controls['passChange'].setValue(data.passChange);
      this.userNotificationPermission.controls['postNotification'].setValue(data.postNotification);
     
      this.alertService.notification("Bildirim tercihleri başarıyla güncellendi.",true);
    },(error) => {
      this.alertService.notification("Beklenmedik bir hata meydana geldi.",false);
    } );

  }

}
