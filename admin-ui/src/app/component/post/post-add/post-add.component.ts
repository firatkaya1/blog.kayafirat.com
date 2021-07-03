import { PostService } from './../../../services/post.service';
import { ICategory } from './../../../model/Tag';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl,Validators } from '@angular/forms';

@Component({
  selector: 'app-post-add',
  templateUrl: './post-add.component.html',
  styleUrls: ['./post-add.component.css']
})
export class PostAddComponent implements OnInit {


  public test:string = 'lan';
  public categories:ICategory[] = [];
  public selectedCategories:ICategory[] = [];
  public categoryName:string = '';
  public categoryColor:string= '#000f';
  public categoryTextColor:string = '#000f';

  constructor(private postService:PostService) {
    this.postService.getCategories().subscribe(data => {
      this.categories = data
    })

   }

    postDetail = new FormGroup({
      title: new FormControl(null,[ Validators.required, Validators.email ]),
      header: new FormControl(null,[ Validators.required]),
      isPublish: new FormControl(false,[ Validators.required]),
      googleDescription: new FormControl(null,[ Validators.required ]),
      googleImage: new FormControl(null,[ Validators.required ]),
      googleTitle: new FormControl(null,[ Validators.required ]),
      googleTag: new FormControl(null,[ Validators.required ]),
      googleKeywords: new FormControl(null,[ Validators.required ]),
      twitterDescription: new FormControl(null,[ Validators.required ]),
      twitterImage: new FormControl(null,[ Validators.required ]),
      twitterTitle: new FormControl(null,[ Validators.required ]),
      twitterTag: new FormControl(null,[ Validators.required ]),
      twitterCard: new FormControl(null,[ Validators.required ]),
      twitterCreator: new FormControl("Fırat Kaya",[ Validators.required ]),
      facebookDescription: new FormControl(null,[ Validators.required ]),
      facebookImage: new FormControl(null,[ Validators.required ]),
      facebookTitle: new FormControl(null,[ Validators.required ]),
      facebookTag: new FormControl(null,[ Validators.required ]),
      facebookAuthor: new FormControl("Fırat Kaya",[ Validators.required ]),
      facebookSitename: new FormControl("https://blog.kayafirat.com",[ Validators.required ]),
      facebookUrl: new FormControl(null,[ Validators.required ]),
      facebookType: new FormControl(null,[ Validators.required ]),
      postBody:new FormControl(null,[ Validators.requiredTrue ])}); 

  ngOnInit(): void { }
  
  sendPost(){
    var body = this.postDetail.value
    body.categories = this.selectedCategories.map(c => c.categoryId)
    this.postService.savePost(body).subscribe(() => {
      this.clear()
    })
    
  }
  setCategory(id){
    var addedCategory = this.categories.find(c => c.categoryId == id)
    if(this.selectedCategories.findIndex(c => c.categoryId == id) == -1){
      this.selectedCategories.push(addedCategory)
    }
  }
  deleteCategory(id){
    this.selectedCategories = this.selectedCategories.filter(c => c.categoryId != id)
  }
  clear(){
    this.postDetail.reset();
    this.selectedCategories = []
  }

}
