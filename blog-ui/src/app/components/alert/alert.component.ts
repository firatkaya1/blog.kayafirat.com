import { Alert } from './../../model/Alert';
import { AlertService } from './../../service/alert/alert.service';
import { Component, OnInit, Input } from '@angular/core';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent implements OnInit {


  alertSubscription: Subscription;
  alerts: Alert[] = [];

  constructor(private alertService: AlertService) { }

  ngOnInit(): void {
    this.alertSubscription = this.alertService.onAlert().subscribe(alert => {
              alert.id = (this.alerts.length > 0) ? this.alerts.slice(-1).pop().id + 1 : 1;
              this.alerts.push(alert);
              setTimeout(() => {
                this.closeNotify(alert.id);
              }, 40000);
    });
  }

  public closeNotify(id:number){
    this.alerts = this.alerts.filter(alert => alert.id !== id);
  }

}
