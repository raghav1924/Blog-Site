import { Component } from '@angular/core';
import { HelpService } from '../services/help.service';
import { FormControl, FormGroup } from '@angular/forms';
import { flatMap, noop } from 'rxjs';
import { Blog } from '../edit-blog/model/blog';

@Component({
  selector: 'app-myblogs',
  templateUrl: './myblogs.component.html',
  styleUrls: ['./myblogs.component.css']
})
export class MyblogsComponent {


  arr:any=[];
  receivedData:any;

  turnonedit:boolean=false;

  constructor(private db:HelpService){}
  ngOnInit(): void {

this.getUser();
  }

count=true;
  oneditclick(i:any,data:any){

    // this.updateForm..setValue(data)
    this.editBlog=data;

    i='backpage'+i;
    console.log(i);
    let can:any;
    can=document.getElementById(i);
    console.log(can)
    let cancard:any;
    cancard=document.getElementById(i);
    console.log(can)
    if(this.count){

      // cancard.style.display="block";
      can.style.display="block";
      this.count=false;
    }else{
      // cancard.style.height="auto";
      can.style.display="none";
      this.count=true;

    }
  }

  editBlog:Blog={};

  updateForm=new FormGroup({
    'title':new FormControl(),
    'content':new FormControl(),
    'imageUrl':new FormControl(),
  })

  getUser(){
    this.db.getUserDetails().subscribe(res=>{
        this.receivedData=res;
        console.log(res);
        this.arr= this.receivedData.blogList;
        console.log(this.arr)
    })
  }

  edit(title:any){
    this.updateForm.value.title=title;
      this.db.editBlog(this.updateForm.value).subscribe({next:data=>{
        this.updateForm.reset
        this.receivedData=data;
        console.log(data);
        this.arr= this.receivedData.blogList;
      },error:e=>alert(`${e.message}\n${e.status}`)})
  }

  delete(blog:any){
    this.db.deleteblog(blog).subscribe(res=>this.getUser());
  }

}
