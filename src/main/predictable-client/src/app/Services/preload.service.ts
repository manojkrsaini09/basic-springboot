import { Injectable } from '@angular/core';
import { User } from '../Models/user-model';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { tap, catchError } from 'rxjs/operators';
import { throwError } from '../../../node_modules/rxjs';

@Injectable()
export class UserInfoProvider {

    user: User = null;

    constructor(private http: HttpClient) {

    }

    public getUserInfo(): User {
        return this.user;
    }

    load() {
        console.log('in preload service :----got user data');
        return new Promise((resolve, reject) => {
            this.http
                .get('user/userInfo').pipe(
                    tap( response => {
                        console.log(response);
                        if ( response['status'] === 'SUCCESS') {
                            this.user =  response['data'];
                            console.log('preload service :----got user data');
                            console.log(this.user);
                        } else {
                            // this.errorMessage = response['data'].errorMessage;
                        }
                    }),
                    catchError(this.handleError)
                );
        });
    }

    private handleError(err: HttpErrorResponse) {
        console.log('error in user service');
        let errorMessage = '';
        if (err.error instanceof ErrorEvent) {
          errorMessage = `An error occurred: ${err.error.message}`;
        } else {
          errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
        }
        // this.errorMessage = errorMessage;
        console.error(errorMessage);
        return throwError(errorMessage);
      }
}
