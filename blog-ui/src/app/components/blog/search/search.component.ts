import { Component, OnInit, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  @Output() keyword = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
  }

  search(val:string){
    this.keyword.emit(val);
  }
  
}
