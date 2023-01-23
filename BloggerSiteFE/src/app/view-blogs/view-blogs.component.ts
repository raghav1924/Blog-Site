import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HelpService } from '../services/help.service';
import Swal from 'sweetalert2';
import { PageRouterService } from '../services/page-router.service';

@Component({
  selector: 'app-view-blogs',
  templateUrl: './view-blogs.component.html',
  styleUrls: ['./view-blogs.component.css']
})
export class ViewBlogsComponent {

  arr:any=[];
  bigArr:any=[];
  receivedData:any;
  constructor(private db:HelpService,private router:PageRouterService){}
  ngOnInit(): void {
this.count1=0;
this.getAllUser();
  }


  count=true;
  oneditclick(i:any){
    this.count1++;
    if(localStorage.getItem('token')=='off'){
      this.router.navigateToLoginView();
    }
    i='backpage'+i;
    console.log(i);
    let can:any;
    can=document.getElementById(i);
    console.log(can)
    if(this.count){

      can.style.display="block";
      this.count=false;
    }else{
      can.style.display="none";
      this.count=true;

    }
  }



  // commentForm=new FormGroup({
  //   'title':new FormControl(),
  //   'comment':new FormControl(),
  //   'imageUrl':new FormControl(),
  // })
  comment="";

  turnonchat:boolean=false;
cont:number=0;

  getAllUser(){
    this.bigArr=[];
    this.db.getAllUser().subscribe(res=>{
        this.receivedData=res;
        console.log(res);
        this.arr= this.receivedData.blogList;
        console.log(this.arr)
        for (const p of this.receivedData) {
          console.log(p)
          for (const x of p.blogList) {
            console.log(x)
            this.bigArr[this.cont]=x;
            this.cont++;
          }
        }
        console.log(this.bigArr)
    })
  }

  sendComment(blog:any)
  {


  this.count1++;

    // this.updateForm.value.title=title;
      this.db.sendComment(blog,this.comment).subscribe({next:data=>{
        // this.router.navigateToHome();
        // this.getAllUser();
        window.location.reload();

        // this.receivedData=data;
        // console.log(data);
        // this.arr= this.receivedData.blogList;
      },error:e=>alert(`${e.message}\n${e.status}`)})
  }

  delete(blog:any){
    this.db.deleteblog(blog).subscribe(res=>this.getAllUser());
  }
count1=0;


  canDeactivate(){
    if(this.count1==0)
    return Swal.fire({
      title: 'Are you sure?',
      text: "You are leaving without making any comments",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes'
    }).then((result) =>(result.isConfirmed) ? true : false);
    else{
    console.log('else');
      return true
    }
  }


}
