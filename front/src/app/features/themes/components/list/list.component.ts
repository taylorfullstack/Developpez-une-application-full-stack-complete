import {Component, OnDestroy, OnInit} from '@angular/core';
import {Theme} from "../../interfaces/theme";
import {ThemeService} from "../../services/theme.service";
import { Subscription} from "rxjs";

@Component({
  selector: 'app-themes',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})

export class ListComponent implements OnInit, OnDestroy {
  themes: Theme[] = [];
  isLoading = true;
  themesSubscription: Subscription | null = null;

  constructor(private themeService: ThemeService) { }

  ngOnInit(): void {
    this.getThemes();
  }

  getThemes(): void {
    this.isLoading = true;
    this.themesSubscription = this.themeService.getThemes()
        .subscribe((themes) => {
      this.themes = themes;
      this.isLoading = false;
    });
  }

  ngOnDestroy(): void {
    if(this.themesSubscription){
      this.themesSubscription.unsubscribe();
    }
  }
}
