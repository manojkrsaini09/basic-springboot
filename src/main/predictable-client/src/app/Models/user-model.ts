import { Role } from './role-model';

/* Defines the product entity */
export class User {
    id: number;
    firstName: string;
    lastName: string;
    userName: string;
    roles: Role[];
    constructor () { }
}
