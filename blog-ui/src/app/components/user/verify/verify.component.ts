import { Router, ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { UserService } from './../../../service/user/user.service';
import { AlertService } from './../../../service/alert/alert.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-verify',
  templateUrl: './verify.component.html',
  styleUrls: ['./verify.component.css']
})
export class VerifyComponent implements OnInit {

  public token:string = '';
  
  constructor(private userService:UserService,private alertService:AlertService,private titleService:Title,private route: Router,private router: ActivatedRoute) { 
    this.titleService.setTitle("Verify Account")
    this.token = this.router.snapshot.queryParamMap.get('token');

    if(this.token?.length < 15 || this.token == null) {
      this.route.navigate(["403"]);
      this.alertService.notification("Geçersiz işlem girişimi.",false);
    }
  }

  ngOnInit(): void { }
  
  verify(){
    this.userService.verifyAccount(this.token).subscribe(data => {
      this.route.navigate(["/"]);
      this.alertService.notification("Hesap başarıyla onaylandı.",true);
    }, error => {
      this.route.navigate(["403"]);
      this.alertService.notification("Beklenmedik bir hata oluştu, lütfen tekrar deneyiniz",false);
    })
  }
}
