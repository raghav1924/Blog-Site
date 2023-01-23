import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class PageRouterService {

  constructor(private rt:Router) { }
  navigateToHome(){
    this.rt.navigate([""]);
  }
  navigateToLoginView() { this.rt.navigate(["login"]); }
  navigateToEdit() { this.rt.navigate(["ediblog"]); }
}
