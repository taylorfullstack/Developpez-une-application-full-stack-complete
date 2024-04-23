import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Article } from "../interfaces/article";
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private apiUrl = 'http://localhost:8080/articles';
  private articles: Article[] | null = null;

  constructor(private http: HttpClient) { }

  getArticles(): Observable<Article[]> {
    if (this.articles) {
      return of(this.articles);
    } else {
      return this.http.get<Article[]>(this.apiUrl).pipe(
        tap(articles => this.articles = articles)
      );
    }
  }

  sortArticles(sortBy: string, direction: string = 'desc'): void {
    if (this.articles) {
      switch (sortBy) {
        case 'date':
          this.articles.sort((a, b) => new Date(b.created_at).getTime() - new Date(a.created_at).getTime());
          break;
        case 'title':
          this.articles.sort((a, b) => b.title.localeCompare(a.title));
          break;
      }

      if (direction === 'asc') {
        this.articles.reverse();
      }
    }
  }

  getArticle(id: number): Observable<Article> {
    const article = this.articles?.find(article => article.id === id);
    if (!article) {
      throw new Error(`No article found with id ${id}`);
    }
    return of(article);
  }

  createArticle(article: Pick<Article, 'title' | 'content' | 'themeId'>): Observable<Article> {
    return this.http.post<Article>(this.apiUrl, article).pipe(
      tap(newArticle => this.articles?.push(newArticle))
    );
  }
}
