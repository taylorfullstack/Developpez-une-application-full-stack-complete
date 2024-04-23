import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ListComponent as ArticleListComponent } from "./features/articles/components/list/list.component";
import { ListComponent as ThemeListComponent } from "./features/themes/components/list/list.component";
import {FormComponent as ArticleFormComponent} from "./features/articles/components/form/form.component";
import {DetailComponent as ArticleDetailComponent} from "./features/articles/components/detail/detail.component";

// TODO: add a guard combined with canLoad / canActivate route option to manage unauthenticated user to access private routes
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'articles', component: ArticleListComponent },
  { path: 'articles/new', component: ArticleFormComponent},
  { path: 'articles/:id', component: ArticleDetailComponent },
  { path: 'themes', component: ThemeListComponent },
  { path: 'profile', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
