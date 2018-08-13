import { Component, AfterViewInit } from '@angular/core';
import { AppService } from '../app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  templateUrl: './login.component.html',
})
export class LoginComponent implements AfterViewInit {
  pageTitle = 'Login Page';
  credentials = {username: '', password: ''};

  constructor(private app: AppService, private http: HttpClient, private router: Router) {
  }

  ngAfterViewInit(){
    if(this.app.authenticated){
      this.router.navigate(['/dashboard']);
    }
  }

  login() {
    this.app.authenticate(this.credentials, () => {
        this.router.navigate(['/dashboard']);
    });
    return false;
  }
}
