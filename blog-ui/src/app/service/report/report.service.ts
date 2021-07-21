import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReportService {
 
  private BASE_URL:string = "http://localhost:8081/report";
  //private BASE_URL:string = "https://api.kayafirat.com/blog/report";
  constructor(private http:HttpClient) { }

  public sendReport(body){
    return this.http.post(this.BASE_URL,body);
  }

}
