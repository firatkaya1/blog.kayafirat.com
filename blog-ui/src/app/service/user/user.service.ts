import { NotificationPermission } from './../../model/NotificationPermission';
import { Visibility } from './../../model/Visibility';
import { User } from './../../model/User';
import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  //private BASE_URL:string = "http://localhost:8081/user";
  //private BASE_URL2:string = "http://localhost:8081";
  private BASE_URL:string = "https://api.kayafirat.com/blog/user";
  private BASE_URL2:string = "https://api.kayafirat.com/blog";

  constructor(private http:HttpClient) { }

  public getUser(){
    return this.http.get<User>(this.BASE_URL.concat('/profile'));
  }
  public getUserPermissions(){
    return this.http.get<Visibility>(this.BASE_URL.concat('/permissions/profile'));
  }
  public getUserNotificationPermissions(){
    return this.http.get<NotificationPermission>(this.BASE_URL2.concat('/notification/permissions'));
  }

  public getUserMailPermissions(){
    return this.http.get<NotificationPermission>(this.BASE_URL.concat('/permissions/mail'));
  }

  public getUserNotifications(pageNumber:any){
    return this.http.get(this.BASE_URL2.concat('/notification?page=').concat(pageNumber));
  }

  public hasNotification(){
    return this.http.get<Number>(this.BASE_URL2.concat('/notification/has'));
  }

  public setUserNotificationPermissions(body){
    return this.http.post<NotificationPermission>(this.BASE_URL2.concat('/notification/permissions'),body);
  }

  public setUserMailPermissions(body){
    return this.http.post<NotificationPermission>(this.BASE_URL.concat('/permissions/mail'),body);
  }

  public setUserPermissions(body){
    return this.http.post<Visibility>(this.BASE_URL.concat('/permissions/profile'),body);
  }

  public setUserProfile(body){
    return this.http.post(this.BASE_URL.concat('/profile'),body);
  }

  public setAllNotificationRead(){
    return this.http.post(this.BASE_URL2.concat('/notification/read'),'');
  }

  public setNotificationRead(id:number){
    return this.http.post(this.BASE_URL2.concat('/notification/read/'+id),'');
  }

  public deleteAllNotifications(){
    return this.http.delete(this.BASE_URL2.concat('/notification'))
  }

  public deleteNotification(id:number){
    return this.http.delete(this.BASE_URL2.concat('/notification/'+id))
  }
  
  public saveUser(body){
    return this.http.post(this.BASE_URL,body);
  }

  public login(body){
    return this.http.post(this.BASE_URL.concat("/login"),body);
  }

  public isAuthenticate(){
    return this.http.get(this.BASE_URL.concat("/auth"));
  }

  public updateUserProfilPhoto(image:File) {
    let formData = new FormData();
    formData.append('file', image);
    return this.http.post(this.BASE_URL.concat("/photo"),formData);
  }

  public logout(){
    return this.http.post(this.BASE_URL.concat("/logout"),"");
  }
  
  public linkedinOauth(code:string){
    return this.http.post(this.BASE_URL.concat("/oauth/linkedin?code=").concat(code),"");
  } 

  public githubOauth(code:string){
    return this.http.post(this.BASE_URL.concat("/oauth/github?code=").concat(code),"");
  }
  
  public sendForgotPasswordMail(email:string) {
    return this.http.post(this.BASE_URL.concat("/forgot"),{"email":email});
  }

  public sendResetPasswordMail(email:string,token:string) {
    const body = { email:email,token:token } 
    return this.http.post(this.BASE_URL.concat("/reset"),body);
  }

  public unSubscribe(token:string){
    return this.http.post(this.BASE_URL.concat("/unsubscribe"),{token:token});
  }

  public verifyAccount(token:string){
    return this.http.post(this.BASE_URL.concat("/verify"),{token:token});
  }

}
