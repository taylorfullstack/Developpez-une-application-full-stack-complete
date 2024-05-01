import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from "../interfaces/user";
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl: string = environment.apiUrl + '/me';

  constructor(private http: HttpClient) { }

  public getUser(): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}`)
  }

  public updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}`, user);
  }

  public subscribeToTheme(themeId: number): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/themes/${themeId}`, {});
  }

  public unsubscribeFromTheme(themeId: number): Observable<User> {
    return this.http.delete<User>(`${this.apiUrl}/themes/${themeId}`);
  }
}
