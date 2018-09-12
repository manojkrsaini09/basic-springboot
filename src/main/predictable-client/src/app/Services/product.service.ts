import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { IProduct } from '../Models/productModel';

@Injectable()
export class ProductService{
    constructor(private http: HttpClient) {}

    getProducts():IProduct[]{
        return [
            {id:1,name:"Product 1",attributes:"Attr 1, Attr 2, Attr 3", organizationId:1},
            {id:2,name:"Product 2",attributes:"Attr 1, Attr 2, Attr 3", organizationId:2},
            {id:3,name:"Product 3",attributes:"Attr 1, Attr 2, Attr 3", organizationId:3},
            {id:4,name:"Product 4",attributes:"Attr 1, Attr 2, Attr 3", organizationId:4}
        ];
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