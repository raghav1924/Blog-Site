import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HelpService } from '../services/help.service';
import { FormControl, FormGroup } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private help:HelpService,private router:Router){}
  loginForm=new FormGroup({
    'email':new FormControl(),
    'password':new FormControl(),
  })
receivedData:any;
  sendSignUpData(){
    console.log(this.loginForm.value);
    this.help.login(this.loginForm.value).subscribe({next:data=>{
      this.receivedData=data;
      console.log(this.receivedData);
      console.log(this.receivedData.userEmail)
      console.log(this.receivedData.token)
      console.log(this.receivedData.userName)
      localStorage.setItem('token',this.receivedData.token)
      localStorage.setItem('userEmail',this.receivedData.userName)
      Swal.fire(
        'login success',
        'Navigating To Your Blogs',
        'success'
      )
      this.router.navigateByUrl("/myblog");
    },error:e=>{
      Swal.fire(
        'Login Error',
        'Credentials Donot Match',
        'error'
      )
    }});
  }
}
