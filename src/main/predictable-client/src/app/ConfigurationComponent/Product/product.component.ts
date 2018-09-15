import {Component, OnInit} from '@angular/core';
import {IProduct} from '../../Models/productModel';
import { IOrgnization } from '../../Models/companyModel';
import {ProductService} from '../../Services/product.service';
import {CompanyService} from '../../Services/company.service';
import { AppService} from '../../app.service';
import {MessageService} from 'primeng/api';
@Component({
    templateUrl : './product.component.html'
})
export class ProductsComponent{
    products: IProduct[];
    orgnizations: IOrgnization[];
    errorMessage: string;
    editableMode = false;
    selectedProduct: IProduct;
    isSuperAdmin:boolean=false;
    userOrgnizationId:number = 50;

    constructor(private productService: ProductService, private companyService: CompanyService, private appService:AppService,
                private messageService: MessageService) {}

    ngOnInit(): void {
        if(this.appService.loggedInUserInfo){
            if(this.appService.loggedInUserInfo.roles != null && this.appService.loggedInUserInfo.roles.length > 0 && this.appService.loggedInUserInfo.roles[0].role == "superadmin"){
                this.isSuperAdmin = true;
            }
            if(this.appService.loggedInUserInfo.companyVO != null){
                this.userOrgnizationId = this.appService.loggedInUserInfo.companyVO.id;
            }
        }
        this.getCompanies();
        this.getProducts();
    }

    getCompanies():void{
        this.orgnizations = this.companyService.getCompanies();
    }

    getProducts(): void {
        this.productService.getProducts(this.userOrgnizationId)
                .subscribe(products => {
                    this.products = products['data'];
                },
                err => {
                    this.messageService.add({severity:'error', summary: 'Error', detail:'Someting went wrong. Please try later'});
                });
    }

    addNewProduct(): void {
        this.selectedProduct = {} as IProduct;
        this.editableMode = true;
    }

    saveProduct(): void {
        if(!this.isSuperAdmin){
            this.selectedProduct.companyId = this.userOrgnizationId;
        }
        
        if(this.selectedProduct.id > 0){
            this.productService.updateProduct(this.selectedProduct).subscribe(product => {
                this.selectedProduct = {} as IProduct;
                this.editableMode = false;

                this.getProducts();
                this.messageService.add({severity:'info', summary: 'Success', detail:'Updated Successfully'});
            },
            err => {
                this.messageService.add({severity:'error', summary: 'Error', detail:'Someting went wrong. Please try later'});
            });
        }
        else{
            this.productService.saveProduct(this.selectedProduct).subscribe(product => {
                this.selectedProduct = {} as IProduct;
                this.editableMode = false;

                this.getProducts();
                this.messageService.add({severity:'info', summary: 'Success', detail:'Saved Successfully'});
            },
            err => {
                this.messageService.add({severity:'error', summary: 'Error', detail:'Someting went wrong. Please try later'});
            });
        }
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
        this.productService.deleteProduct(id).subscribe(product => {
            this.messageService.add({severity:'info', summary: 'Success', detail:'Deleted Successfully'});
            this.editableMode = false;
            this.getProducts();
        },
        err => {
            this.messageService.add({severity:'error', summary: 'Error', detail:'Someting went wrong. Please try later'});
        });
        
    }
}