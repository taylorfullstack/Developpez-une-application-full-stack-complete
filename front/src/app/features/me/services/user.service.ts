import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { User } from "../interfaces/user";
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl: string = environment.apiUrl + '/me';
  private User: User | null = null;

  constructor(private http: HttpClient) { }

  // subscribeToTheme(id: number): Observable<User[]> {
  //   //TODO: Implement this method
  // }
  //
  // unsubscribeFromTheme(id: number): Observable<User[]> {
  //   //TODO: Implement this method
  // }
}
