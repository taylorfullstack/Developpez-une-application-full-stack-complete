import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent implements OnInit, OnDestroy {

formControls: { [key: string]: FormControl } = {
  username: new FormControl('', [Validators.required, Validators.minLength(2)]),
  email: new FormControl('', [Validators.email, Validators.required]),
  password: new FormControl('', [
    Validators.required,
    Validators.minLength(8),
    Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\W(?!_))[A-Za-z\d\W].{8,}$'),
  ]),
};

labels: { [key: string]: string } = {
  username: 'Nom d’utilisateur',
  email: 'Adresse e-mail',
  password: 'Mot de passe',
};

controlNames: { [key: string]: string } = {
  username: 'un nom d’utilisateur avec au moins 2 caractères',
  email: 'une adresse e-mail valide',
  password: 'un mot de passe avec au moins 8 caractères, dont 1 lettre majuscule, 1 lettre minuscule, 1 chiffre et 1 caractère spécial',
};

errorMessages: { [key: string]: string } = {
  username: '',
  email: '',
  password: '',
};

constructor() {}

ngOnInit(): void {}

onBlur(controlName: string) {
  const control = this.formControls[controlName];
  control.markAsTouched();
  this.errorMessages[controlName] = control.hasError('required') ? `Veuillez saisir ${this.controlNames[controlName]}` : '';
}

onSubmit() {
  if (this.formControls["username"] && this.formControls['email'].valid && this.formControls['password'].valid) {
    console.log('submit'); //TODO add register post request (via service)
  }
}

ngOnDestroy(): void {

}


}
