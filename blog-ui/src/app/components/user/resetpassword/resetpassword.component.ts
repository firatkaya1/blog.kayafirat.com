import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from './../../../service/alert/alert.service';
import { UserService } from './../../../service/user/user.service';
import { Component, OnInit } from '@angular/core';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.css']
})
export class ResetpasswordComponent implements OnInit {

  public nPassword:string = '';
  public code:string = '';

  constructor(private userService:UserService,private alertService:AlertService,private router: ActivatedRoute,private route: Router,private title:Title) {
    title.setTitle("Şifreni sıfırla");
    this.code = this.router.snapshot.queryParamMap.get('code');
    const jwtExtract  = JSON.parse(JSON.stringify(jwt_decode(this.code)));
    var current_time = new Date().getTime() / 1000; 

    if(this.code?.length < 15 || this.code == null || current_time > jwtExtract.exp) {
      this.route.navigate(["403"]);
      this.alertService.notification("Link süresi dolmuş ya da kullanılmış. Lütfen yeni bir link talep ediniz.",false);
    }
  }

  ngOnInit(): void { }

  sendResetPassword(){
    this.userService.sendResetPasswordMail(this.nPassword,this.code).subscribe(data => {
      this.alertService.notification("Şifreniz başarıyla sıfırlandı.",true);
      this.route.navigate(["/"]);
    }, error => {
      this.alertService.notification(error.errorMessage,false);

    })
  }
}
