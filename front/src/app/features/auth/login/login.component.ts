import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl,  Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Subscription} from "rxjs";
import {LoginRequest} from "../interfaces/login.request";
import {SessionService} from "../services/session.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit, OnDestroy {
  formControls: { [key: string]: FormControl } = {
    emailOrUsername: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  };

  labels: { [key: string]: string } = {
    emailOrUsername: 'E-mail ou nom d’utilisateur',
    password: 'Mot de passe',
  };

  controlNames: { [key: string]: string } = {
    emailOrUsername: 'votre e-mail ou nom d’utilisateur',
    password: 'votre mot de passe',
  };

  errorMessages: { [key: string]: string } = {
    emailOrUsername: '',
    password: '',
  };

  loginSubscription: Subscription | null = null;

  constructor(private authService: AuthService,
              private sessionService: SessionService, private router: Router) {}

  ngOnInit(): void {}

  login(): void {
    const loginRequest: LoginRequest = {
      emailOrUsername: this.formControls['emailOrUsername'].value,
      password: this.formControls['password'].value,
    };

    this.loginSubscription = this.authService.login(loginRequest)
      .subscribe({
        next: (data) => {
          this.sessionService.logIn(data.token);
          this.router.navigate(['/articles']).then(
            () => {
            }
          );
        },
        error: error => {
          throw error;
        }
      });
  }

  onBlur(controlName: string): void {
    const control = this.formControls[controlName];
    control.markAsTouched();
    this.errorMessages[controlName] = control.hasError('required') ? `Veuillez saisir ${this.controlNames[controlName]}` : '';
  }

  onSubmit(): void {
    if (this.formControls['emailOrUsername'].valid && this.formControls['password'].valid) {
      this.login();
    }
  }

  ngOnDestroy(): void {
    if(this.loginSubscription){
      this.loginSubscription.unsubscribe();
    }
  }
}
