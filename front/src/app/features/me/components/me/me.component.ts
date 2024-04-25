import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {Theme} from "../../../themes/interfaces/theme";

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrl: './me.component.scss'
})
export class MeComponent implements OnInit, OnDestroy {
  subscribedThemes: Theme[] = []; //TODO get themes

  formControls: { [key: string]: FormControl } = {
    username: new FormControl('', [Validators.required, Validators.minLength(2)]),
    email: new FormControl('', [Validators.email, Validators.required]),
  };

  labels: { [key: string]: string } = {
    username: 'Nom d’utilisateur',
    email: 'Adresse e-mail',
  };

  controlNames: { [key: string]: string } = {
    username: 'un nom d’utilisateur avec au moins 2 caractères',
    email: 'une adresse e-mail valide',
  };

  errorMessages: { [key: string]: string } = {
    username: '',
    email: '',
  };

  constructor() {}

  ngOnInit(): void {
    this.subscribedThemes = [{id: 1, title: 'theme1', description: 'description1'}, {id: 2, title: 'theme2', description: 'description2'}]; //TODO get themes
  }

  onBlur(controlName: string) {
    const control = this.formControls[controlName];
    control.markAsTouched();
    this.errorMessages[controlName] = control.hasError('required') ? `Veuillez saisir ${this.controlNames[controlName]}` : '';
  }

  onSubmit() {
    if (this.formControls["username"] && this.formControls['email'].valid) {
      console.log('submit'); //TODO add register
    }
  }

  onLogout(){
    //set token to null
    //redirect to home

    console.log('logout'); //TODO add logout logic
  }

  onUnsubscribe(themeId : number){
    //unsubscribe from theme
    //remove theme from subscribedThemes
    console.log('unsubscribe', themeId); //TODO add unsubscribe
  }

  ngOnDestroy(): void {}

}
