import {Injectable} from "@angular/core";
import { Router} from "@angular/router";
import {SessionService} from "../services/session.service";
import {map, Observable} from "rxjs";


@Injectable({providedIn: 'root'})
export class AuthGuard {

  constructor(
    private router: Router,
    private sessionService: SessionService,
  ) {
  }

  public canActivate(): boolean {
    if (!this.sessionService.isLogged) {
      this.router.navigateByUrl('/login').then(
        () => console.log('Redirected to /login')
      );
      return false;
    }
    return true;
  }
}
