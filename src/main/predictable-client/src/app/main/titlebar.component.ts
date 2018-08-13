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
export class TitleBarComponent{
    constructor(private app: AppService, private http: HttpClient, private router: Router) {
    }

    logout() {
        this.http.post('/logout', {}).pipe(
          tap(data => console.log('All: ' + JSON.stringify(data))),
          catchError(this.handleError)
        ).subscribe();
        this.app.authenticated = false;
        this.router.navigateByUrl('/login');
      }

      private handleError(err: HttpErrorResponse) {
        this.app.authenticated = false;
        this.router.navigateByUrl('/login');
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        let errorMessage = '';
        if (err.error instanceof ErrorEvent) {
          // A client-side or network error occurred. Handle it accordingly.
          errorMessage = `An error occurred: ${err.error.message}`;
        } else {
          // The backend returned an unsuccessful response code.
          // The response body may contain clues as to what went wrong,
          errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
        }
        console.error(errorMessage);
        return throwError(errorMessage);
      }
}