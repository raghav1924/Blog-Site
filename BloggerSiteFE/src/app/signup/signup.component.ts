import { Component } from '@angular/core';
import { HelpService } from '../services/help.service';
import { PageRouterService } from '../services/page-router.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})


export class SignupComponent {

phonePattern = '^[7-9][0-9]{9}$';
// passwordPattern = '^[A-Za-z@!#$%\d]{8,}$';
passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}$";
emailpattern='^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$'

  constructor(private help:HelpService,private router:PageRouterService){}
  // signupForm=new FormGroup({
  //   'email':new FormControl(),
  //   'name':new FormControl('',[Validators.required,Validators.minLength(4)]),
  //   'phoneNo':new FormControl(),
  //   'password':new FormControl(),
  // })
  signupForm=new FormGroup({
    'email':new FormControl('',[Validators.required,Validators.pattern(this.emailpattern)]),
    'name':new FormControl('',[Validators.required,Validators.minLength(3)]),
    'phoneNo':new FormControl('',[Validators.required,Validators.pattern(this.phonePattern)]),
    'password':new FormControl('',[Validators.required,Validators.pattern(this.passwordPattern)]),
  })

    get name(){
      return this.signupForm.get('name');
    }
    get email(){
      return this.signupForm.get('email');
    }
    get phoneNo(){
      return this.signupForm.get('phoneNo');
    }
    get password(){
      return this.signupForm.get('password');
    }



receivedData:any;
  sendSignUpData(){
    console.log(this.signupForm.value);
    this.help.register(this.signupForm.value).subscribe({next:data=>{
      this.receivedData=data;
      console.log(this.receivedData);
      this.router.navigateToLoginView();
    },error:e=>alert(`${e.message}\n${e.status}`)});

  }
}
