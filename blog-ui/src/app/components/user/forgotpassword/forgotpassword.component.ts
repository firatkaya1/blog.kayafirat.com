import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { AlertService } from './../../../service/alert/alert.service';
import { UserService } from './../../../service/user/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.css']
})
export class ForgotpasswordComponent implements OnInit {

  public email:string = '';

  constructor(private userService:UserService,private alertService:AlertService,private titleService:Title,private route: Router) { 
    this.titleService.setTitle("Şifreni mi unuttun?")
  }

  ngOnInit(): void { }

  sendResetPassword(){
    this.userService.sendForgotPasswordMail(this.email).subscribe(data => {
      this.alertService.notification("Şifre sıfırlama isteği başarıyla yollandı. Lütfen email adresini kontrol et.",true);
      this.route.navigate(["/"]);
    })
  }
}
