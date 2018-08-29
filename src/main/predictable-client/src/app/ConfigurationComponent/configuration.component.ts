import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { Router } from '@angular/router';

@Component({
    templateUrl: './configuration.component.html',
    styleUrls: ['./configuration.component.css']
})
export class ConfigurationComponent implements OnInit {
    sideMenuClicked = false;
    homeSubmenu = false;
    toggleSubMenu(): void {
        this.homeSubmenu = !this.homeSubmenu;
    }
    sideMenuClick(): void {
        this.sideMenuClicked = !this.sideMenuClicked;
    }
    constructor(private app: AppService, private router: Router) {}

    ngOnInit() {
        if (!this.app.authenticated) {
            this.router.navigate(['/login']);
        }
    }
    logout() {
        this.app.logout();
    }
}
