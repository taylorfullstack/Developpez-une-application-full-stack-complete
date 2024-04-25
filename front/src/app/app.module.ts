import { NgModule, LOCALE_ID } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import localeFr from '@angular/common/locales/fr';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import {ListComponent as ThemeListComponent} from "./features/themes/components/list/list.component";
import {ListComponent as ArticleListComponent} from "./features/articles/components/list/list.component";
import {DetailComponent as ArticleDetailComponent } from "./features/articles/components/detail/detail.component";
import {FormComponent as ArticleFormComponent} from "./features/articles/components/form/form.component";
import {MatCardModule} from '@angular/material/card';
import {NgOptimizedImage} from "@angular/common";
import {NavbarComponent} from "./components/navbar/navbar.component";
import {MatIcon} from "@angular/material/icon";
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatInput} from "@angular/material/input";
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {MatMenuModule} from "@angular/material/menu";
import {MatOption, MatSelect} from "@angular/material/select";
import { MatSidenavModule } from "@angular/material/sidenav";
import {MatToolbar} from "@angular/material/toolbar";
import {MatNavList} from "@angular/material/list";
import {MatDivider} from "@angular/material/divider";
import {LoginComponent} from "./features/auth/login/login.component";
import {RegisterComponent} from "./features/auth/register/register.component";
import {MeComponent} from "./features/me/components/me/me.component";


registerLocaleData(localeFr, 'fr');

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ThemeListComponent,
    ArticleDetailComponent,
    ArticleListComponent,
    ArticleFormComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    MeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    HttpClientModule,
    NgOptimizedImage,
    MatIcon,
    MatFormFieldModule,
    MatInput,
    MatProgressSpinner,
    MatMenuModule,
    MatSelect,
    MatOption,
    FormsModule,
    MatSidenavModule,
    MatToolbar,
    MatNavList,
    MatDivider
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'fr' }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
