import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthLayoutComponent} from "./components/auth.layout/auth.layout.component";
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


const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [UnauthGuard] },
  { path: 'login', component: LoginComponent , canActivate: [UnauthGuard]},
  { path: 'register', component: RegisterComponent , canActivate: [UnauthGuard]},
  {
    path: '',
    component: AuthLayoutComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'articles', component: ArticleListComponent },
      { path: 'articles/new', component: ArticleFormComponent },
      { path: 'articles/:id', component: ArticleDetailComponent },
      { path: 'themes', component: ThemeListComponent },
      { path: 'profile', component: MeComponent }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
