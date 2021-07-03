import { AlertService } from './../../../service/alert/alert.service';
import { ReportService } from './../../../service/report/report.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  styleUrls: ['./not-found.component.css']
})
export class NotFoundComponent implements OnInit {

  constructor(private reportService:ReportService,private alertService:AlertService) { }

  customForm = new FormGroup({
    name:new FormControl(null,[Validators.required]),
    email:new FormControl(null,[Validators.required,Validators.email]),
    message:new FormControl(null,[Validators.required])
  });

  ngOnInit(): void {
  }

  send(){
    const body = {
      name :this.customForm.controls['name'].value,
      email :this.customForm.controls['email'].value,
      reportMessage :this.customForm.controls['message'].value,
      reportCode :404,
    }
    this.reportService.sendReport(body).subscribe(response => 
    {
      this.alertService.notification("Mesajınız başarıyla iletildi.",true);
      this.customForm.reset();
    }, 
    error => {
      this.alertService.notification("Beklenmedik bir hatayla karşılaşıldı.",false);
    })
  }

}
