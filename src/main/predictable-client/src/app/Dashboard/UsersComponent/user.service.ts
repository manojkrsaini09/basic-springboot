import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { User } from '../../Models/user-model';

@Injectable()
export class UserService {

    errorMessage: string;
    constructor(private route: Router, private http: HttpClient) {

    }

    getUsers(): Observable<any> {
        return this.http.get('user').pipe(
            tap( response => {
                console.log(response);
                if ( response['status'] === 'SUCCESS') {
                    this.errorMessage = response['data'].errorMessage;
                } else {
                    this.errorMessage = response['data'].errorMessage;
                }
            }),
            catchError(this.handleError)
        );
    }
    saveUser(user: User): Observable<any> {
        return this.http.post('user/create', user).pipe(
            tap( response => {
                console.log(response);
                if ( response['status'] === 'SUCCESS') {
                    this.errorMessage = response['data'].errorMessage;
                } else {
                    this.errorMessage = response['data'].errorMessage;
                }
            }),
            catchError(this.handleError)
        );
    }
    private handleError(err: HttpErrorResponse) {
        console.log('error in user service');
        let errorMessage = '';
        if (err.error instanceof ErrorEvent) {
          errorMessage = `An error occurred: ${err.error.message}`;
        } else {
          errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
        }
        this.errorMessage = errorMessage;
        console.error(errorMessage);
        return throwError(errorMessage);
      }
}
