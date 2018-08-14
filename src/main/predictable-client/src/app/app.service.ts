import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Router, Resolve } from '@angular/router';
import { throwError} from 'rxjs';
import { tap , catchError , finalize } from 'rxjs/operators';

@Injectable()
export class AppService implements Resolve<any> {
  authenticated = false;

  constructor(private http: HttpClient, private router: Router) {}

  resolve(){
      return this.authenticate(undefined, undefined);
  }
  authenticate(credentials, callback) {
        const headers = new HttpHeaders(credentials ? {
            authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        } : {});

        this.http.get('user', {headers: headers}).subscribe(response => {
            if (response['name']) {
                this.authenticated = true;
            } else {
                this.authenticated = false;
            }
            return callback && callback();
        });

    }

    logout() {
        this.http.post('/logout', {}).pipe(
          tap(data => console.log('All: ' + JSON.stringify(data))),
          catchError(this.handleError)
        ).subscribe();
        this.authenticated = false;
        this.router.navigateByUrl('/login');
      }

      private handleError(err: HttpErrorResponse) {
        console.log('error in login');
        this.authenticated = false;
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