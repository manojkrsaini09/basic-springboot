import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  pageTitle = 'Login Page';
  credentials = {username: '', password: ''};

  constructor(private app: AppService, private http: HttpClient, private router: Router) {
  }

  ngOnInit(){
    // if(this.app.authenticated){
    //   this.router.navigateByUrl('dashboard');
    // }
  }

  login() {
    this.app.authenticate(this.credentials, () => {
        this.router.navigateByUrl('/dashboard');
    });
    return false;
  }
}
