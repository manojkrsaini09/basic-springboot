import { Organization } from "./organization-model";

export interface ILine{
    id:number;
    name:string;
    companyId:number;
    companyVO:Organization;
}