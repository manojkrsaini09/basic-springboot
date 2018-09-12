import { Role } from './role-model';
import { Organization } from './organization-model';

/* Defines the product entity */
export class User {
    id: number;
    firstName: string;
    lastName: string;
    userName: string;
    enabled: boolean;
    roles: Role[];
    email: string;
    companyVO: Organization;
    constructor () { }
}
