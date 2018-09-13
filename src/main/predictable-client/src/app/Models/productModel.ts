import { Organization } from "./organization-model";

export interface IProduct{
    id:number;
    name:string;
    companyId:number;
    companyVO:Organization;
}