import { AuthService } from './../auth/AuthenticateService';
import { AlertService } from './../alert/alert.service';
import {  CanActivate,  ActivatedRouteSnapshot,  RouterStateSnapshot,  Router} from "@angular/router";
import { Injectable } from "@angular/core";
import { map, take } from 'rxjs/operators';

@Injectable()
export class LoginGuard implements CanActivate {

  private hasPermission = false;

  constructor(private authservice: AuthService, private router: Router, private alertService:AlertService) {
    
  }

  canActivate(route: ActivatedRouteSnapshot,state: RouterStateSnapshot){
    return this.authservice.isLoggedIn
    .pipe(take(1),map(isAuth => {
      if(isAuth){
        return isAuth
      } else {
        this.router.navigate(["403"]);
        this.alertService.notification("Sayfaya erişim için sisteme giriş yapmalısınız!",false);
        return !isAuth
      }
    }))

  }
}