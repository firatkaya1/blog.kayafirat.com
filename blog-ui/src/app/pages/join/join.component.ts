import { AuthService } from './../../service/auth/AuthenticateService';
import { NgxSpinnerService } from 'ngx-spinner';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from './../../service/user/user.service';
import { AlertService } from './../../service/alert/alert.service';
import { Title } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-join',
  templateUrl: './join.component.html',
  styleUrls: ['./join.component.css']
})
export class JoinComponent implements OnInit {

  public isLoginOpen:boolean = false;
  private code:string = "";

  constructor(private titleService:Title,private alertService:AlertService,
    private userService:UserService,
    private route: Router,
    private router: ActivatedRoute,private spinner: NgxSpinnerService,private authenticateService:AuthService) 
    { 
      this.code = this.router.snapshot.queryParamMap.get('code');
      if(this.code){
        if(this.code.length < 30){
          this.sendGithub(this.code);
        } else {
          this.sendLinkedin(this.code);
        }
        this.spinner.show();
      } else {
        this.spinner.hide();
      }

    }
  ngOnInit(): void {
    this.titleService.setTitle("Giriş Yap | Kayıt Ol");
  }

  public show(isOpen:boolean){
    this.isLoginOpen = isOpen ? false : true;
  }

  sendLinkedin(val:string){
    this.userService.linkedinOauth(val).subscribe(response => {
      this.spinner.hide();
      localStorage.setItem('_hermonie',JSON.stringify(new Date()));
      this.authenticateService.setLoggedIn(true)
      this.route.navigateByUrl('/user');
      this.alertService.notification("Giriş başarılı, yönlendiriliyorsunuz...",true);
    });
  }

  sendGithub(val:string){
    this.userService.githubOauth(val).subscribe(response => {
      this.spinner.hide();
      localStorage.setItem('_hermonie',JSON.stringify(new Date()));
      this.authenticateService.setLoggedIn(true)
      this.route.navigateByUrl('/user');
      this.alertService.notification("Giriş başarılı, yönlendiriliyorsunuz...",true);
    });
  }

}
