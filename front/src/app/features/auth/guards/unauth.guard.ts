import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { SessionService } from "../services/session.service";
import {  Observable } from "rxjs";

@Injectable({ providedIn: 'root' })
export class UnauthGuard {
  constructor(
    private router: Router,
    private sessionService: SessionService,
  ) { }

  public canActivate(): boolean {
    if (!this.sessionService.isLogged) {
      this.router.navigateByUrl('/articles').then(
        () => console.log('Success: Redirected to /articles')
      );
      return false;
    }
    return true;
  }
}
