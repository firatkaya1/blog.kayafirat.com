import { Alert } from './../../model/Alert';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AlertService {
 
  private subject = new Subject<Alert>();

  // enable subscribing to alerts observable
  onAlert(): Observable<Alert> {
      return this.subject.asObservable();
  }
  notification(message: string,isSuccess:boolean) {
      this.alert(new Alert({message,isSuccess,isClose:true}));
  }
  alert(alert: Alert) {
      this.subject.next(alert);
  }

}
