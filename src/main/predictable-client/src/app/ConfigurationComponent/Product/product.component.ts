import {Component, OnInit} from '@angular/core';
import {IProduct} from '../../Models/productModel';
import { ICompany } from '../../Models/companyModel';
import {ProductService} from '../../Services/product.service';
import {CompanyService} from '../../Services/company.service';

@Component({
    templateUrl : './product.component.html'
})
export class ProductsComponent{
    products: IProduct[];
    companies: ICompany[];
    errorMessage: string;
    editableMode = false;
    selectedProduct: IProduct;

    constructor(private productService: ProductService, private companyService: CompanyService) {}

    ngOnInit(): void {
        this.getCompanies();
        this.getProducts();
    }

    getCompanies():void{
        this.companies = this.companyService.getCompanies();
    }

    getProducts(): void {
        this.products = this.productService.getProducts();
    }

    addNewProduct(): void {
        this.selectedProduct = {} as IProduct;
        this.editableMode = true;
    }

    saveProduct(): void {
        if(this.selectedProduct.id > 0){
            this.productService.updateProduct(this.selectedProduct);
        }
        else{
            this.productService.saveProduct(this.selectedProduct);
        }
        
        this.selectedProduct = {} as IProduct;
        this.editableMode = false;

        this.getProducts();
    }

    editProduct(product: IProduct):void{
        this.selectedProduct = product;
        this.editableMode = true;
    }

    cancel(): void {
        this.selectedProduct = {} as IProduct;
        this.editableMode = false;
    }

    deleteProduct(id:number):void{
        this.productService.deleteProduct(id);
        this.getProducts();
    }
}