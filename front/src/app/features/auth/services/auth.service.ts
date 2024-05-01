import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {environment} from "../../../../environments/environment";
import {Observable} from "rxjs";
import {RegisterRequest} from "../interfaces/register.request";
import {LoginRequest} from "../interfaces/login.request";
import {AuthSuccess} from "../interfaces/auth.success";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl: string = environment.apiUrl + '/auth';

  constructor(private http: HttpClient) { }

  public register(registerRequest: RegisterRequest): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/register`, registerRequest);
  }

  public login(loginRequest: LoginRequest): Observable<AuthSuccess> {
    return this.http.post<AuthSuccess>(`${this.apiUrl}/login`, loginRequest);
  }
}
