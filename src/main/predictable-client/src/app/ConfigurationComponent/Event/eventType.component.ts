import {Component, OnInit} from '@angular/core';
import {IEventType} from '../../Models/eventTypeModel';
import { IOrgnization } from '../../Models/companyModel';
import {EventTypeService} from '../../Services/eventType.service';
import {CompanyService} from '../../Services/company.service';
import { AppService} from '../../app.service';
import {MessageService} from 'primeng/api';
@Component({
    templateUrl : './eventType.component.html'
})
export class EventTypeComponent{
    eventTypes: IEventType[];
    orgnizations: IOrgnization[];
    errorMessage: string;
    editableMode = false;
    selectedEventType: IEventType;
    isSuperAdmin:boolean=false;
    userOrgnizationId:number = 0;

    constructor(private eventTypeService: EventTypeService, private companyService: CompanyService, private appService:AppService,
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
        this.getEventTypes();
    }

    getCompanies():void{
        this.orgnizations = this.companyService.getCompanies();
    }

    getEventTypes(): void {
        this.eventTypes = this.eventTypeService.getEventTypes();
    }

    addNewEventType(): void {
        this.selectedEventType = {} as IEventType;
        this.editableMode = true;
    }

    saveEventType(): void {
        if(!this.isSuperAdmin){
            this.selectedEventType.companyId = this.userOrgnizationId;
        }
        
        if(this.selectedEventType.id > 0){
            this.eventTypeService.updateEventType(this.selectedEventType);
            this.messageService.add({severity:'info', summary: 'Success', detail:'Updated Successfully'});
        }
        else{
            this.eventTypeService.saveEventType(this.selectedEventType);
            this.messageService.add({severity:'info', summary: 'Success', detail:'Saved Successfully'});
        }
        
        this.selectedEventType = {} as IEventType;
        this.editableMode = false;

        this.getEventTypes();
    }

    editEventType(event: IEventType):void{
        this.selectedEventType = event;
        this.editableMode = true;
    }

    cancel(): void {
        this.selectedEventType = {} as IEventType;
        this.editableMode = false;
    }

    deleteEventType(id:number):void{
        this.eventTypeService.deleteEventType(id);
        this.messageService.add({severity:'info', summary: 'Success', detail:'Deleted Successfully'});
        this.editableMode = false;
        this.getEventTypes();
    }
}