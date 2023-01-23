import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanDeactivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { ViewBlogsComponent } from '../view-blogs/view-blogs.component';

@Injectable({
  providedIn: 'root'
})
export class DeactiveGuard implements CanDeactivate<unknown> {
  canDeactivate(
    component: ViewBlogsComponent,
    currentRoute: ActivatedRouteSnapshot,
    currentState: RouterStateSnapshot,
    nextState?: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return component.canDeactivate();
  }

}
