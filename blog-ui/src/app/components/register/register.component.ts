import { AuthService } from './../../service/auth/AuthenticateService';
import { NgxSpinnerService } from 'ngx-spinner';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from './../../service/user/user.service';
import { AlertService } from './../../service/alert/alert.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private alertService:AlertService,private userService:UserService) { }

  register = new FormGroup({
    emailAddress:new FormControl(null,[Validators.required,Validators.email]),
    userName:new FormControl(null,[Validators.required,Validators.minLength(5),Validators.maxLength(20),Validators.pattern('^[a-zA-Z0-9]*')]),
    password:new FormControl(null,[Validators.required,Validators.minLength(8),Validators.maxLength(20)]),
    confirmPassword:new FormControl(null,[Validators.required])
  },{ validators:this.checkPasswords});

  ngOnInit(): void { }

  checkPasswords(group: FormGroup){
    const password = group.get('password').value;
    const confirmPassword = group.get('confirmPassword').value;
    if(password==null || confirmPassword == null){
      return null;
    }
    if(password === confirmPassword){
      return null;
    }
    return {'isMatch':true};
  }

  public registerUser(){
    let body = {
      emailAddress:this.register.controls['emailAddress'].value,
      username:this.register.controls['userName'].value,
      password:this.register.controls['password'].value,
    }
    this.alertService.notification("Bekleyiniz...",true);
    this.userService.saveUser(body).subscribe((data)=> 
    {
      this.alertService.notification("Kayıt başarıyla tamamlandı. Hesabınızı doğrulamayı unutmayın.",true);
      this.register.reset();
    },(error)=> {
      if(error.errorCode == 1){
        this.alertService.notification("Bu email adresi alınmış.",false);
      } else if(error.errorCode == 2){
        this.alertService.notification("Bu kullanıcı adı alınmış..",false);
      } else {
        this.alertService.notification("Bir hata ile karşılaşıldı.",false);
      }
      
    })
  }
  public registerLinkedin(){
    var address = "https://blog.kayafirat.com"
    window.location.href="https://www.linkedin.com/oauth/v2/authorization?client_id=77s8v0hceim00y&redirect_uri="+address+"/join&response_type=code&scope=r_liteprofile%20r_emailaddress";
  }
  public registerGithub(){
    window.location.href="https://github.com/login/oauth/authorize?client_id=196172c3589b723bb46b";
  }


}
