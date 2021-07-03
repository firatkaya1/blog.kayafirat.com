import { AlertService } from './../../../service/alert/alert.service';
import { UserService } from './../../../service/user/user.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-visibility',
  templateUrl: './visibility.component.html',
  styleUrls: ['./visibility.component.css']
})
export class VisibilityComponent implements OnInit {

  constructor(private userService:UserService,private alertService:AlertService) { }

  userVisibility = new FormGroup({
    contact: new FormControl(),
    birthdate:new FormControl(),
    github:new FormControl(),
    linkedin:new FormControl()
  }); 

  ngOnInit(): void { 
    this.getUserVisibility();
  }

  getUserVisibility(){
    this.userService.getUserPermissions().subscribe(
      (data) => 
      {
        this.userVisibility.controls['contact'].setValue(data.contact);
        this.userVisibility.controls['birthdate'].setValue(data.birthdate);
        this.userVisibility.controls['github'].setValue(data.github);
        this.userVisibility.controls['linkedin'].setValue(data.linkedin);

      })
  }

  ngSubmit() {
    let body = {
      contact: this.userVisibility.controls['contact'].value,
      birthdate: this.userVisibility.controls['birthdate'].value,
      github: this.userVisibility.controls['github'].value,
      linkedin: this.userVisibility.controls['linkedin'].value,
    }
    this.userService.setUserPermissions(body).subscribe((data)=> 
    {
      this.userVisibility.controls['contact'].setValue(data.contact);
      this.userVisibility.controls['birthdate'].setValue(data.birthdate);
      this.userVisibility.controls['github'].setValue(data.github);
      this.userVisibility.controls['linkedin'].setValue(data.linkedin);
      this.alertService.notification("Görünürlük tercihleri başarıyla güncellendi.",true);
    },(error) => {
      this.alertService.notification("Beklenmedik bir hata meydana geldi.",false);
    } );
  }


}
