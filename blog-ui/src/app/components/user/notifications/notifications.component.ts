import { AlertService } from './../../../service/alert/alert.service';
import { Notification } from './../../../model/Notification';
import { UserService } from './../../../service/user/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit {

  public notifications:Notification[] = [];
  public pageNumber:number = 1;
  public totalPages:number = -1;
  public totalElements:number = 0;
  constructor(private userService:UserService,private alertService:AlertService) { }

  ngOnInit(): void {
    this.getNotifications();
  }

  increasePageNumber(){
    this.pageNumber = this.pageNumber + 1;
    this.getNotifications();

  }

  private getNotifications(){
    this.userService.getUserNotifications(this.pageNumber).subscribe(
      (data)=> 
      {
        if(this.pageNumber==1){
          this.notifications = data['content'];
        } else {
          this.notifications=this.notifications.concat(data['content']);
        }
        this.totalPages = data['totalPages'];
        this.totalElements = data['totalElements'];
      });
  }

  openNotification(val:Notification,index:number){
    
    this.userService.setNotificationRead(val.id).subscribe((data)=> 
    {
      val.is_Read = true;
      this.notifications[index]=val;
    })
  }

  allRead(){
    this.notifications.forEach((notify)=> {notify.is_Read = true});
    this.userService.setAllNotificationRead().subscribe((data)=>{this.alertService.notification("Tüm bildirimleri okudun.",true)})
  }

  allDelete(){
    this.userService.deleteAllNotifications().subscribe((data)=> {
      this.notifications = [];
      this.alertService.notification("Tüm bildirimler silindi.",true);
    })
  }

  deleteNotification(id:number,index:number){
    this.userService.deleteNotification(id).subscribe((data)=> {
        this.notifications.splice(index,1);
        this.alertService.notification("Bildirim silindi.",true);
    })
  }

}
