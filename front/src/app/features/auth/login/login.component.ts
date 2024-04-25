import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl,  Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Subscription} from "rxjs";
import {LoginRequest} from "../interfaces/login.request";
import {SessionService} from "../services/session.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit, OnDestroy {
  formControls: { [key: string]: FormControl } = {
    email: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  };

  labels: { [key: string]: string } = {
    email: 'E-mail ou nom d’utilisateur',
    password: 'Mot de passe',
  };

  controlNames: { [key: string]: string } = {
    email: 'votre e-mail ou nom d’utilisateur',
    password: 'votre mot de passe',
  };

  errorMessages: { [key: string]: string } = {
    email: '',
    password: '',
  };

  isLoading = false;
  loginSubscription: Subscription | null = null;

  constructor(private authService: AuthService, private sessionService: SessionService) {}

  ngOnInit(): void {}

  login(): void {
    const loginRequest: LoginRequest = {
      email: this.formControls['email'].value,
      password: this.formControls['password'].value,
    };

    this.isLoading = true;
    this.loginSubscription = this.authService.login(loginRequest)
      .subscribe((data) => {
        console.log("data:", data);
        this.isLoading = false;
        this.sessionService.logIn(data.token);
      });
  }

  onBlur(controlName: string) {
    const control = this.formControls[controlName];
    control.markAsTouched();
    this.errorMessages[controlName] = control.hasError('required') ? `Veuillez saisir ${this.controlNames[controlName]}` : '';
  }

  onSubmit() {
    if (this.formControls['email'].valid && this.formControls['password'].valid) {
      this.login();
    }
  }

  ngOnDestroy(): void {
    if(this.loginSubscription){
      this.loginSubscription.unsubscribe();
    }
  }

}
