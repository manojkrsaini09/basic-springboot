import { Component,AfterViewInit } from "@angular/core";
import { AppService } from '../app.service';
import { Router } from '@angular/router';
@Component({
    templateUrl:'dashboard.component.html'
})
export  class DashboardComponent implements AfterViewInit{

    constructor(private app: AppService, private router: Router) {
    }

    ngAfterViewInit(){
        if(!this.app.authenticated){
          this.router.navigate(['/login']);
        }
      }
}