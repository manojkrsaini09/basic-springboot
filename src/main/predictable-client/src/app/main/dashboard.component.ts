import { Component,AfterViewInit, OnInit } from "@angular/core";
import { AppService } from '../app.service';
import { Router } from '@angular/router';
@Component({
    templateUrl:'dashboard.component.html'
})
export  class DashboardComponent{

    constructor(private app: AppService, private router: Router) {
        console.log('constructor of dashboard');
         if(!this.app.authenticated){
                   this.router.navigate(['/login']);
           }
    }

    // ngAfterViewInit(){
    //     // this.app.authenticate(undefined, () => {
    //     //     if(!this.app.authenticated){
    //     //       this.router.navigate(['/login']);
    //     //     }
    //     //   });
    //     if(!this.app.authenticated){
    //                this.router.navigate(['/login']);
    //          }
    //   }
}