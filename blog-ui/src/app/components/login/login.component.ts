import { AuthService } from './../../service/auth/AuthenticateService';
import { AlertService } from './../../service/alert/alert.service';
import { UserService } from './../../service/user/user.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NgxSpinnerService }   from "ngx-spinner";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private code:string = "";

  constructor(private userService:UserService,
    private alertService:AlertService,
    private authenticateService:AuthService,
    private route: Router,) 
    { 
      
    }

  login = new FormGroup({
    username:new FormControl(null,[Validators.required]),
    password:new FormControl(null,[Validators.required,Validators.minLength(8)])
  });

  ngOnInit(): void {  }

  loginUser() {
    let body = {
      username:this.login.controls['username'].value,
      password:this.login.controls['password'].value,
    }
    this.userService.login(body).subscribe(data => 
    {
      localStorage.setItem('_hermonie',JSON.stringify(new Date()));
      this.authenticateService.setLoggedIn(true)
      this.route.navigateByUrl('/user');
      this.alertService.notification("Giriş başarılı, yönlendiriliyorsunuz...",true);
      
    }, error => 
    {
      this.alertService.notification("Kullanıcı adı veya şifreniz yanlış",false);

    })
  }

  loginLinkedin(){
    //var address = "https://localhost:4200"
    var address = "https://blog.kayafirat.com"
    window.location.href="https://www.linkedin.com/oauth/v2/authorization?client_id=77s8v0hceim00y&redirect_uri="+address+"/join&response_type=code&scope=r_liteprofile%20r_emailaddress";
  }

  loginGithub(){
    window.location.href="https://github.com/login/oauth/authorize?client_id=196172c3589b723bb46b";
  }
}
