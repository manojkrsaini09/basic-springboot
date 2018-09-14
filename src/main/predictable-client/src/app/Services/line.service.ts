import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { ILine } from '../Models/lineModel';

@Injectable()
export class LineService{
    constructor(private http: HttpClient) {}

    getLines():ILine[]{
        return [
            {id:1,name:"Line 1", companyId:1,companyVO:null},
            {id:2,name:"Line 2", companyId:2,companyVO:null},
            {id:3,name:"Line 3", companyId:3,companyVO:null},
            {id:4,name:"Line 4", companyId:4,companyVO:null},
            {id:5,name:"Line 5", companyId:4,companyVO:null},
            {id:6,name:"Line 6", companyId:4,companyVO:null},
            {id:7,name:"Line 7", companyId:4,companyVO:null},
            {id:8,name:"Line 8", companyId:4,companyVO:null},
            {id:9,name:"Line 9", companyId:4,companyVO:null},
            {id:10,name:"Line 10", companyId:4,companyVO:null},
            {id:11,name:"Line 11", companyId:4,companyVO:null},
            {id:12,name:"Line 12", companyId:4,companyVO:null},
        ];
    }

    saveLine(product:ILine):boolean{
        return true;
    }

    deleteLine(id:number):boolean{
        return true;
    }

    updateLine(product:ILine):boolean{
        return true;
    }

}