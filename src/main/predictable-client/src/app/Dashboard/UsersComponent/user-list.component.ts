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
    editableMode: false;
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
               }
            },
            error => this.errorMessage = 'Error in call'
        );
    }

    addNewUser(): void {

    }
}
