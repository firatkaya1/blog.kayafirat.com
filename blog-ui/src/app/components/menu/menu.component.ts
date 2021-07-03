import { AuthService } from './../../service/auth/AuthenticateService';
import { UserService } from './../../service/user/user.service';
import { AlertService } from './../../service/alert/alert.service';
import { Component, OnChanges, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  public isMobileMenuOpen:boolean;
  public height:Number;
  public isTurkish:boolean = true;
  public hasNotification:Number = -1;
  public isAuthenticate:boolean = false;

  constructor(private translate: TranslateService,private alertService:AlertService,private userService:UserService,private authService:AuthService) {
    if(localStorage.getItem("language")==null){
      localStorage.setItem("language","tr");
      translate.setDefaultLang('tr');
      this.isTurkish=true;
    } else {
      translate.setDefaultLang(localStorage.getItem("language"));
      this.isTurkish = localStorage.getItem("language")==="tr" ? true : false; 
    }
  }

  ngOnInit(): void {
    this.authService.isLoggedIn$.subscribe(isLoggedIn => this.isAuthenticate = isLoggedIn)
    this.authService.getLoggedIn();

    if(this.isAuthenticate){
      this.userService.hasNotification().subscribe((data)=>{this.hasNotification = data;})
    }
    
  }
  public openMenu(){
    if(this.isMobileMenuOpen){
      this.isMobileMenuOpen = false;
      this.height = 0;
    } else {
      this.isMobileMenuOpen = true;
      this.height = 100;
    }
  }
  
  public changeLanguage(language:string){
    if(language === "tr"){
      this.isTurkish = true;
      this.translate.setDefaultLang("tr");
      this.alertService.notification("Dil değiştirildi.",true);
    } else {
      this.isTurkish = false;
      this.translate.setDefaultLang("en");
      this.alertService.notification("Language changed.",true);
    }
    
  }

  public logout(){
    this.userService.logout().subscribe(data => {
      this.alertService.notification("Başarıyla çıkış yapıldı.",true);
      this.authService.setLoggedIn(false);
      this.isAuthenticate = false;
    }, error => {
      this.alertService.notification("Beklenmedik bir hatayla karşılaşıldı",false);
      this.authService.setLoggedIn(false);
    })
    localStorage.removeItem('_hermonie')
  }

}
