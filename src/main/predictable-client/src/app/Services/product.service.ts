import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { IProduct } from '../Models/productModel';
import "rxjs/add/operator/map"


@Injectable()
export class ProductService{
    constructor(private http: HttpClient) {}

    getProducts(orgnizationId: number): Observable<IProduct[]>{
        return this.http.get<IProduct[]>('product/all?companyId='+orgnizationId);
    }

    saveProduct(product:IProduct):Observable<IProduct>{
        return this.http.post<IProduct>('product/create', product)
    }

    deleteProduct(id:number):Observable<IProduct>{
        return this.http.get<IProduct>('product/delete?id='+id);
    }


    updateProduct(product:IProduct):Observable<IProduct>{
        return this.http.post<IProduct>('product/update', product)
    }

}