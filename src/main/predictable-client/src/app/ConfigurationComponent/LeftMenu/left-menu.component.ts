import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-left-menu',
    templateUrl: './left-menu.component.html',
    styleUrls: ['./../configuration.component.css']
})
export class LeftMenuComponent {
    @Input() toggleMenu: boolean;
}
