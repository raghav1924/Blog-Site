import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HelpService } from '../services/help.service';
import { PageRouterService } from '../services/page-router.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  constructor(private help:HelpService,private router:PageRouterService){}
  signupForm=new FormGroup({
    'title':new FormControl(),
    'content':new FormControl(),
    'imageUrl':new FormControl()
  })

  selectedFile?: File;
  onFileChange(event:any){
    this.selectedFile=event.target.files[0];
    console.log(event.target.value)
    console.log(event.target.localStorage)
    console.log(this.selectedFile);
    // alert(this.selectedFile);
  }

receivedData:any;
  sendBlogData(){
    // this.signupForm.value.imageUrl=this.selectedFile;
    console.log(this.signupForm.value);
    this.help.addBlog(this.signupForm.value).subscribe({next:data=>{
      this.receivedData=data;
      console.log("inside sendblog");
      console.log(this.receivedData);
    },error:e=>alert(`${e.message}\n${e.status}`)});

  }

}
