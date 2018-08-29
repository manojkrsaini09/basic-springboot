import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { throwError} from 'rxjs';
import { tap , catchError , finalize } from 'rxjs/operators';
import { AppService } from '../app.service';

@Component({
    selector: 'title-bar',
    templateUrl: './titlebar.component.html'
})
export class TitleBarComponent {
    constructor(private app: AppService, private http: HttpClient, private router: Router) {
    }

    logout() {
        this.app.logout();
      }
}