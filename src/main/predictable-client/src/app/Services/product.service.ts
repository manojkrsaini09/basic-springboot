import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { IProduct } from '../Models/productModel';
import "rxjs/add/operator/map"


@Injectable()
export class ProductService{
    constructor(private http: HttpClient) {}

    getProducts(): Observable<IProduct[]>{
        // return [
        //     {id:1,name:"Product 1", companyId:1,companyVO:null},
        //     {id:2,name:"Product 2", companyId:2,companyVO:null},
        //     {id:3,name:"Product 3", companyId:3,companyVO:null},
        //     {id:4,name:"Product 4", companyId:4,companyVO:null},
        //     {id:5,name:"Product 5", companyId:4,companyVO:null},
        //     {id:6,name:"Product 6", companyId:4,companyVO:null},
        //     {id:7,name:"Product 7", companyId:4,companyVO:null},
        //     {id:8,name:"Product 8", companyId:4,companyVO:null},
        //     {id:9,name:"Product 9", companyId:4,companyVO:null},
        //     {id:10,name:"Product 10", companyId:4,companyVO:null},
        //     {id:11,name:"Product 11", companyId:4,companyVO:null},
        //     {id:12,name:"Product 12", companyId:4,companyVO:null},
        // ];

        return this.http.get<IProduct[]>('product/all?companyId=1');
    }

    saveProduct(product:IProduct):boolean{
        return true;
    }

    deleteProduct(id:number):boolean{
        return true;
    }

    updateProduct(product:IProduct):boolean{
        return true;
    }

}