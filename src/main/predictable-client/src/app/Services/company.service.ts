import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { IOrgnization } from '../Models/companyModel';

@Injectable()
export class CompanyService{
    constructor(private http: HttpClient) {}

    getCompanies():IOrgnization[]{
        return [
            {id:1,name:"Company 1"},
            {id:2,name:"Company 2"},
            {id:3,name:"Company 3"},
            {id:4,name:"Company 4"}
        ];
    }
}