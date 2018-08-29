import { Component } from '@angular/core';
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
export class AppComponent {
  title = 'Home Page';
  constructor(private app: AppService, private http: HttpClient, private router: Router) {}
}
