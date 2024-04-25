import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthService } from './auth.service';
import {User} from "../../me/interfaces/user";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  public isLogged: boolean = false;
  public user: User | undefined;

  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

  constructor(private authService: AuthService) {
    this.initializeUser();
  }

  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  public logIn(token: string): void {
    localStorage.setItem('token', token);
    this.isLogged = true;
    this.isLoggedSubject.next(this.isLogged);
    this.initializeUser();
  }

  public logOut(): void {
    localStorage.removeItem('token');
    this.user = undefined;
    this.isLogged = false;
    this.next();
  }

  private next(): void {
    this.isLoggedSubject.next(this.isLogged);
  }


  private initializeUser(): void {
    const token = localStorage.getItem('token');
    if (token) {
      this.authService.getUser().subscribe({
        next: (user) => {
          this.user = user;
          this.isLogged = true;
          this.isLoggedSubject.next(this.isLogged);
        },
        error: (error) => {
          // If an error occurs when fetching the user, check the error status
          if (error.status === 401) {
            // If the status is 401 Unauthorized, remove the token from local storage
            localStorage.removeItem('token');
          }
          console.error('Error fetching user:', error);
        }
      });
    }
  }
}
