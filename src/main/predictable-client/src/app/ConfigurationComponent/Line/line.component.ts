import {Component, OnInit} from '@angular/core';
import {ILine} from '../../Models/lineModel';
import { IOrgnization } from '../../Models/companyModel';
import {LineService} from '../../Services/line.service';
import {CompanyService} from '../../Services/company.service';
import { AppService} from '../../app.service';
import {MessageService} from 'primeng/api';
@Component({
    templateUrl : './line.component.html'
})
export class LinesComponent{
    lines: ILine[];
    orgnizations: IOrgnization[];
    errorMessage: string;
    editableMode = false;
    selectedLine: ILine;
    isSuperAdmin:boolean=false;
    userOrgnizationId:number = 0;

    constructor(private lineService: LineService, private companyService: CompanyService, private appService:AppService,
                private messageService: MessageService) {}

    ngOnInit(): void {
        if(this.appService.loggedInUserInfo){
            if(this.appService.loggedInUserInfo.roles != null && this.appService.loggedInUserInfo.roles.length > 0 && this.appService.loggedInUserInfo.roles[0].role == "superadmin"){
                this.isSuperAdmin = true;
            }
            if(this.appService.loggedInUserInfo.companyVO != null){
                this.userOrgnizationId = this.appService.loggedInUserInfo.companyVO.id;
            }
        }
        this.getCompanies();
        this.getLines();
    }

    getCompanies():void{
        this.orgnizations = this.companyService.getCompanies();
    }

    getLines(): void {
        this.lines = this.lineService.getLines();
    }

    addNewLine(): void {
        this.selectedLine = {} as ILine;
        this.editableMode = true;
    }

    saveLine(): void {
        if(!this.isSuperAdmin){
            this.selectedLine.companyId = this.userOrgnizationId;
        }
        
        if(this.selectedLine.id > 0){
            this.lineService.updateLine(this.selectedLine);
            this.messageService.add({severity:'info', summary: 'Success', detail:'Updated Successfully'});
        }
        else{
            this.lineService.saveLine(this.selectedLine);
            this.messageService.add({severity:'info', summary: 'Success', detail:'Saved Successfully'});
        }
        
        this.selectedLine = {} as ILine;
        this.editableMode = false;

        this.getLines();
    }

    editLine(line: ILine):void{
        this.selectedLine = line;
        this.editableMode = true;
    }

    cancel(): void {
        this.selectedLine = {} as ILine;
        this.editableMode = false;
    }

    deleteLine(id:number):void{
        this.lineService.deleteLine(id);
        this.messageService.add({severity:'info', summary: 'Success', detail:'Deleted Successfully'});
        this.editableMode = false;
        this.getLines();
    }
}