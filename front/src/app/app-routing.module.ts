import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ListComponent as ArticleListComponent } from "./features/articles/components/list/list.component";
import { ListComponent as ThemeListComponent } from "./features/themes/components/list/list.component";
import {FormComponent as ArticleFormComponent} from "./features/articles/components/form/form.component";
import {DetailComponent as ArticleDetailComponent} from "./features/articles/components/detail/detail.component";
import { AuthGuard } from './features/auth/guards/auth.guard';
import { UnauthGuard } from './features/auth/guards/unauth.guard';
import {RegisterComponent} from "./features/auth/register/register.component";
import {LoginComponent} from "./features/auth/login/login.component";
import {MeComponent} from "./features/me/components/me/me.component";


// TODO: put auth guards back once backend auth error is resolved

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent },
  { path: 'articles', component: ArticleListComponent}, //, canActivate: [AuthGuard]
  { path: 'articles/new', component: ArticleFormComponent}, //, canActivate: [AuthGuard]
  { path: 'articles/:id', component: ArticleDetailComponent}, //, canActivate: [AuthGuard]
  { path: 'themes', component: ThemeListComponent}, //, canActivate: [AuthGuard]
  { path: 'profile', component: MeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
