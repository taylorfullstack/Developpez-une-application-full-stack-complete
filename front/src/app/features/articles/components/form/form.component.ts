import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ThemeService } from "../../../themes/services/theme.service";
import { Theme } from "../../../themes/interfaces/theme";
import {ArticleService} from "../../services/article.service";
import {Subscription} from "rxjs";
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit, OnDestroy {
  themes: Theme[] = [];
  formControls: { [key: string]: FormControl } = {
    theme: new FormControl('', [Validators.required]),
    title: new FormControl('', [Validators.required]),
    content: new FormControl('', [Validators.required])
  };

  labels: { [key: string]: string } = {
    theme: 'Sélectionner un thème',
    title: 'Titre de l’article',
    content: 'Contenu de l’article'
  };

  controlNames: { [key: string]: string } = {
    theme: 'un thème',
    title: 'un titre',
    content: 'du contenu'
  };

  errorMessages: { [key: string]: string } = {
    theme: '',
    title: '',
    content: ''
  };

  private themeSubscription: Subscription | null = null;
  private articleSubscription: Subscription | null = null;

  constructor(private themeService: ThemeService, private articleService: ArticleService, private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.themeSubscription = this.themeService.getThemes().subscribe(themes => {
      this.themes = themes;
    });
  }

  onBlur(controlName: string) {
    const control = this.formControls[controlName];
    control.markAsTouched();
    this.errorMessages[controlName] = control.hasError('required') ? `Veuillez saisir ${this.controlNames[controlName]}` : '';
  }

  onSubmit() {
    if (this.formControls['theme'].valid && this.formControls['title'].valid && this.formControls['content'].valid) {
      const newArticle = {
        themeId: this.formControls['theme'].value,
        title: this.formControls['title'].value,
        content: this.formControls['content'].value
      };

      //TODO post article to backend

    }
    this.formControls['theme'].reset();
    this.formControls['title'].reset();
    this.formControls['content'].reset();

  }

  ngOnDestroy(): void {
    if (this.themeSubscription) {
      this.themeSubscription.unsubscribe();
    }
    if (this.articleSubscription) {
      this.articleSubscription.unsubscribe();
    }
  }
}
