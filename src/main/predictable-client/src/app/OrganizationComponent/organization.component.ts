import { Component, OnInit } from '@angular/core';
import { Organization } from '../Models/organization-model';
import { OrganizationService } from './organization.service';

@Component({
    templateUrl: './organization.component.html'
})
export class OrganizationComponent implements OnInit {
    organizations: Organization[];
    errorMessage: string;
    editableMode = false;
    selectedOrganization: Organization;
    ngOnInit(): void {
        this.getOrganizations();
    }

    constructor(private organizationService: OrganizationService) {
    }

    getOrganizations(): void {
        this.organizationService.getOrganizations().subscribe(
            response => {
               console.log(response);
               if (response['status'] === 'SUCCESS') {
                this.organizations = response['data'];
               } else {
                // this.errorMessage = "";
               }
            },
            error => this.errorMessage = 'Error in call'
        );
    }

    addNewOrganization(): void {
        this.selectedOrganization = new Organization();
        this.editableMode = true;
    }

    saveOrganization(): void {
        this.organizationService.saveOrganization(this.selectedOrganization).subscribe(
            response => {
               console.log(response);
               if (response['status'] === 'SUCCESS') {
                this.organizations.push(response['data']);
               } else {
                    this.errorMessage = response.exception.message;
                   // this.errorMessage = 'Server Error';
               }
            },
            error => this.errorMessage = 'Error in call'
        );
        this.selectedOrganization = new Organization();
        this.editableMode = false;
    }

    cancel(): void {
        this.selectedOrganization = new Organization();
        this.editableMode = false;
    }
}
