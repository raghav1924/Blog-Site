import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HelpService {

  authAppUrl="http://localhost:5555/authApp/";
  userApp="http://localhost:5555/userApp/";


  constructor(private httpclient:HttpClient) { }




  register(data:any){
    return this.httpclient.post(this.authAppUrl+"register",data);
  }
  login(data:any){
    return this.httpclient.post(this.authAppUrl+"login",data);
  }



  addBlog(data:any){
    console.log('inside addtouserfavorite');
    console.log(localStorage.getItem('token'));
    let httpHeaders= new HttpHeaders({
      'Authorization': 'Bearer '+localStorage.getItem('token')
    });
    let requestQption= {headers: httpHeaders};
    console.log(requestQption);
      return this.httpclient.post(this.userApp+"addBlog",data,requestQption);
  }
  getUserDetails(){
    console.log('inside GETUSERDETAILS SERVICE')
    let httpHeaders= new HttpHeaders({
      'Authorization': 'Bearer '+localStorage.getItem('token')
    });
    let requestQption= {headers: httpHeaders};
    console.log(requestQption);
    return this.httpclient.get(this.userApp+"getUser",requestQption);
  }

  getAllUser(){
    return this.httpclient.get(this.userApp+"getAllUser");
  }

  editBlog(data:any){
    let httpHeaders= new HttpHeaders({
    'Authorization': 'Bearer '+localStorage.getItem('token')
  });
  let requestQption= {headers: httpHeaders};
  console.log(requestQption);
    return this.httpclient.put(this.userApp+"updateBlog",data,requestQption)
  }

sendComment(blog:any,formData:any){
  let httpHeaders= new HttpHeaders({
    'Authorization': 'Bearer '+localStorage.getItem('token')
  });
  let requestQption= {headers: httpHeaders};
  console.log(blog);
  return this.httpclient.post(this.userApp+"updateComment/"+formData,blog)
}

  deleteblog(blog:any){
    let httpHeaders= new HttpHeaders({
    'Authorization': 'Bearer '+localStorage.getItem('token')
  });
  let requestQption= {headers: httpHeaders,body:blog};
  console.log(requestQption);
    return this.httpclient.delete(this.userApp+"deleteBlog",requestQption);

  }

  deleteUserFromMySql(data:any){
      return this.httpclient.delete(this.authAppUrl="deleteUser/"+data);
  }
  deleteUserFromMongo(data:any){
      return this.httpclient.delete(this.userApp="deleteUser/"+data);
  }
}
