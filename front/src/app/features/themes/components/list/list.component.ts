import {Component, OnInit} from '@angular/core';
import {Theme} from "../../interfaces/theme";
import {ThemeService} from "../../services/theme.service";

@Component({
  selector: 'app-themes',
  templateUrl: './list.component.html',
  host: { class: 'auto-grid' },
  styles: [".actions button { margin: 0.5rem auto; padding-inline: 3rem;}"],
})

export class ListComponent implements OnInit {
  themes: Theme[] = [];

  constructor(private themeService: ThemeService) { }

  ngOnInit(): void {
    this.getThemes();
  }

  getThemes(): void {
    this.themeService.getThemes().subscribe((themes) => {
      this.themes = themes;
    });
  }
}
