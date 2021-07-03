import { Router, ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { AlertService } from './../../../service/alert/alert.service';
import { UserService } from './../../../service/user/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-unsubscribe',
  templateUrl: './unsubscribe.component.html',
  styleUrls: ['./unsubscribe.component.css']
})
export class UnsubscribeComponent implements OnInit {

  public token:string = '';
  
  constructor(private userService:UserService,private alertService:AlertService,private titleService:Title,private route: Router,private router: ActivatedRoute) { 
    this.titleService.setTitle("Unsubscribe")
    this.token = this.router.snapshot.queryParamMap.get('token');

    if(this.token?.length < 15 || this.token == null) {
      this.route.navigate(["403"]);
      this.alertService.notification("Geçersiz işlem girişimi.",false);
    }
  }

  ngOnInit(): void { }
  
  unsubscribe(){
    this.userService.unSubscribe(this.token).subscribe(data => {
      this.route.navigate(["/"]);
      this.alertService.notification("Mail üyeliğinden başarıyla çıkıldı.",true);
    }, error => {
      this.route.navigate(["403"]);
      this.alertService.notification("Beklenmedik bir hata oluştu, lütfen tekrar deneyiniz",false);
    })
  }
}
