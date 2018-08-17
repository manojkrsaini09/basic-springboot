import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { Router } from '@angular/router';
@Component({
    templateUrl: 'dashboard.component.html'
})
export  class DashboardComponent implements OnInit {

    constructor(private app: AppService, private router: Router) {}

    ngOnInit() {
        if (!this.app.authenticated) {
            this.router.navigate(['/login']);
        }
    }
}
