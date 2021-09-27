import { Component, OnInit } from '@angular/core';
import {  Router} from "@angular/router";
import { AlertService } from 'src/app/service/alert/alert.service';
import { AuthService } from 'src/app/service/auth/AuthenticateService';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-exit',
  templateUrl: './exit.component.html',
  styleUrls: ['./exit.component.css']
})
export class ExitComponent implements OnInit {

  constructor(private userService:UserService,private alertService: AlertService,private router: Router,private authService:AuthService) {
    setTimeout(() => {
      this.userService.logout()
      .subscribe(() => {
        this.alertService.notification("Çıkış yapıldı.",true)
        this.router.navigate([""]);
        this.authService.setLoggedIn(false);

      })
    }, 3000);
   }

  ngOnInit(): void {
  }

}
