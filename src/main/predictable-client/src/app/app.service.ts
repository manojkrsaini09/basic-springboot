import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { throwError, Observable} from 'rxjs';
import { tap , catchError , finalize } from 'rxjs/operators';

@Injectable()
export class AppService {
  authenticated = false;
  loggedInUserInfo = {};
  errorMessage = '';

  constructor(private http: HttpClient, private router: Router) {}

  authenticate (credentials): Observable<any> {
        const headers = new HttpHeaders(credentials ? {
            authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        } : {});


        return  this.http.get('userInfo', {headers: headers}).pipe(
            tap(response => {
                if ( response['status'] === 'SUCCESS') {
                    this.authenticated = response['data'].authenticated;
                    this.loggedInUserInfo = response['data'].userVO;
                    this.errorMessage = response['data'].errorMessage;
                } else {
                    this.authenticated = false;
                    this.errorMessage = response['data'].errorMessage;
                }
             }
            ),
            catchError(this.handleError)
          );
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
        this.router.navigate(['/login']);
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
