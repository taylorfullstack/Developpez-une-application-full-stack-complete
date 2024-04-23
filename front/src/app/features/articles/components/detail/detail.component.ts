import {Component, OnInit, OnDestroy} from '@angular/core';
import {Article} from "../../interfaces/article";
import {ArticleService} from "../../services/article.service";
import {ActivatedRoute} from "@angular/router";
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-article',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss'],
})

export class DetailComponent implements OnInit, OnDestroy {
  article: Article | undefined;
  private routeSubscription: Subscription | null = null;
  private articleSubscription: Subscription | null = null;

  constructor(
    private route: ActivatedRoute,
    private articleService: ArticleService
  ) {}

  ngOnInit(): void {
    this.routeSubscription = this.route.params.subscribe(params => {
      const id = +params['id'];
      this.articleSubscription = this.articleService.getArticle(id).subscribe(article => {
        this.article = article;
      });
    });
  }

  ngOnDestroy(): void {
    if (this.routeSubscription) {
      this.routeSubscription.unsubscribe();
    }

    if (this.articleSubscription) {
      this.articleSubscription.unsubscribe();
    }
  }
}
