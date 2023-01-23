import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { MyblogsComponent } from './myblogs/myblogs.component';
import { UserComponent } from './user/user.component';
import { PagenotefoundComponent } from './pagenotefound/pagenotefound.component';
import { ViewBlogsComponent } from './view-blogs/view-blogs.component';
import { EditBlogComponent } from './edit-blog/edit-blog.component';
import { AuthGuard } from './services/auth.guard';
import { DeactiveGuard } from './services/deactive.guard';

const routes: Routes = [
  {path: 'register', component:SignupComponent},
  {path: 'login', component:LoginComponent},
  {path: 'myblog', component:MyblogsComponent,canActivate:[AuthGuard]},
  {path: 'viewblogs', component:ViewBlogsComponent,canDeactivate:[DeactiveGuard]},
  {path: 'user', component:UserComponent,canActivate:[AuthGuard]},
  {path: 'editblog', component:EditBlogComponent,canActivate:[AuthGuard]},
  {path: '', redirectTo:'/viewblogs',pathMatch:'full'},
  {path: '**', component:PagenotefoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
