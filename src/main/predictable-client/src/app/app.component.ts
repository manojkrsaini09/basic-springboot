import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError} from 'rxjs';
import { tap , catchError , finalize } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Home Page';
  greeting = {};
  constructor(private app: AppService, private http: HttpClient, private router: Router) {
    console.log('app component constructor called 666');
  }

  ngOnInit() {
    console.log('app component ng on in it called');
    // this.app.authenticate(undefined, undefined);
  }
}
