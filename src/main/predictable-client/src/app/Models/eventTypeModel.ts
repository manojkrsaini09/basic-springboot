import { Organization } from "./organization-model";

export interface IEventType{
    id:number;
    name:string;
    constraints:string;
    discard:boolean;
    companyId:number;
    companyVO:Organization;
}