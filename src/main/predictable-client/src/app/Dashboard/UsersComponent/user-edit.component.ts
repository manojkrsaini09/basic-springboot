import { Component, OnInit } from '@angular/core';
import { User } from '../../Models/user-model';
import { UserService } from './user.service';
import { RoleService } from '../RolesComponent/role.service';
import { Role } from '../../Models/role-model';

@Component({
    templateUrl  : './user-edit.component.html'
})
export class UserEditComponent implements OnInit {
    userVO = new User();
    errorMessage: string;
    userRoles: Role[];
    ngOnInit(): void {
        console.log('this.userVO');
        console.log(this.userVO);
       this.getRoles();
       this.userVO.roles = [];
    }
    constructor(private userService: UserService, private roleService: RoleService) {

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

    saveUser(): void {
        this.userService.saveUser(this.userVO).subscribe(
            response => {
               console.log(response);
               if (response['status'] === 'SUCCESS') {
                this.userRoles = response['data'];
               } else {
                   // this.errorMessage = response.exception.message;
                   this.errorMessage = 'Server Error';
               }
            },
            error => this.errorMessage = 'Error in call'
        );
    }
}
