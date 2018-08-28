import { Component, OnInit } from '@angular/core';
import { User } from '../../Models/user-model';
import { UserService } from './user.service';
import { RoleService } from '../RolesComponent/role.service';
import { Role } from '../../Models/role-model';

@Component({
    templateUrl : './user-list.component.html'
})
export class UserListComponent implements OnInit {
    users: User[];
    userRoles: Role[];
    errorMessage: string;
    editableMode = false;
    selectedUser: User;
    ngOnInit(): void {
        this.getUsers();
        this.getRoles();
    }

    constructor(private userService: UserService, private roleService: RoleService) {
        this.roleService = roleService;
    }

    getUsers(): void {
        this.userService.getUsers().subscribe(
            response => {
               console.log(response);
               if (response['status'] === 'SUCCESS') {
                this.users = response['data'];
               } else {
                // this.errorMessage = "";
               }
            },
            error => this.errorMessage = 'Error in call'
        );
    }

    getRoles(): void {
        this.roleService.getRoles().subscribe(
            response => {
               console.log(response);
               if (response['status'] === 'SUCCESS') {
                this.userRoles = response['data'];
               } else {
                    // this.errorMessage = response.exception.message;
               }
            },
            error => this.errorMessage = 'Error in call'
        );
    }

    addNewUser(): void {
        this.selectedUser = new User();
        this.selectedUser.roles = [];
        this.selectedUser.enabled = true;
        this.editableMode = true;
    }

    saveUser(): void {
        this.userService.saveUser(this.selectedUser).subscribe(
            response => {
               console.log(response);
               if (response['status'] === 'SUCCESS') {
                this.users.push(response['data']);
               } else {
                   // this.errorMessage = response.exception.message;
                   this.errorMessage = 'Server Error';
               }
            },
            error => this.errorMessage = 'Error in call'
        );
        this.selectedUser = new User();
        this.editableMode = false;
    }

    cancel(): void {
        this.selectedUser = new User();
        this.editableMode = false;
    }
}
