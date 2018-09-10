import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Organization } from '../Models/organization-model';

@Injectable()
export class OrganizationService {

    errorMessage: string;
    constructor(private route: Router, private http: HttpClient) {

    }

    getOrganizations(): Observable<any> {
        return this.http.get('organization').pipe(
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
    saveOrganization(organization: Organization): Observable<any> {
        return this.http.post('organization/create', organization).pipe(
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
        console.log('error in organization service');
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
