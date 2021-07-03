import { BehaviorSubject } from 'rxjs';
import { UserService } from './../user/user.service';
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
  })
export class AuthService  {

    public isLoggedIn= new BehaviorSubject<boolean>(false);
    isLoggedIn$ = this.isLoggedIn.asObservable();

    constructor(private userService:UserService) {}

    public setLoggedIn(value) {
        this.isLoggedIn.next(value);
    }
    
    public getLoggedIn():boolean {
        if(localStorage.getItem('_hermonie')!=null && !this.isLoggedIn.getValue()){
            this.userService.isAuthenticate().subscribe(data => {
                this.setLoggedIn(true);
            })
        }
        return this.isLoggedIn.getValue();
    }
}