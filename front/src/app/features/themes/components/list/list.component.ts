import {Component, OnDestroy, OnInit} from '@angular/core';
import {Theme} from "../../interfaces/theme";
import {ThemeService} from "../../services/theme.service";
import { Subscription} from "rxjs";
import {UserService} from "../../../me/services/user.service";

@Component({
  selector: 'app-themes',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})

export class ListComponent implements OnInit, OnDestroy {
  themes: Theme[] = [];
  isLoading = true;
  themesSubscription: Subscription | null = null;

  constructor(private themeService: ThemeService, userService: UserService) { }

  ngOnInit(): void {
    this.getThemes();
  }

  getThemes(): void {
    this.isLoading = true;
    this.themesSubscription = this.themeService.getThemes().subscribe((themes) => {
      this.themes = themes;
      this.isLoading = false;
    });
  }

  onSubscribeClick(themeId: number): void {
    //if user is subscribed to the theme, unsubscribe (remove from list of subscribedThemes)
    //else subscribe to theme (add theme to subscribedThemes
    //subscribe to theme
    //add theme to subscribedThemes
  }

  ngOnDestroy(): void {
    if(this.themesSubscription){
      this.themesSubscription.unsubscribe();
    }
  }
}
