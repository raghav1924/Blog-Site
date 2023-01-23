import { Component, Input } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { PageRouterService } from '../services/page-router.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  @Input()
  login:boolean=false;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );



  constructor(private breakpointObserver: BreakpointObserver,private route:PageRouterService) {}
  logout(){
    localStorage.setItem('token','off');
    localStorage.setItem('userName','off');


    // this.check=false;
      this.route.navigateToLoginView();
  }

  loggedin(){
    if(localStorage.getItem('token')=='off'){
      this.login=false
    }
    else{
      this.login=true
    }
  }
}
