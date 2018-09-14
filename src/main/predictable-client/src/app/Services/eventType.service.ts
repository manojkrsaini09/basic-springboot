import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { IEventType } from '../Models/eventTypeModel';

@Injectable()
export class EventTypeService{
    constructor(private http: HttpClient) {}

    getEventTypes():IEventType[]{
        return [
            {id:1,name:"EventType 1",constraints:'cons', discard:true, companyId:1,companyVO:null},
            {id:2,name:"EventType 2",constraints:'cons', discard:true, companyId:2,companyVO:null},
            {id:3,name:"EventType 3",constraints:'cons', discard:true, companyId:3,companyVO:null},
            {id:4,name:"EventType 4",constraints:'cons', discard:true, companyId:4,companyVO:null},
            {id:5,name:"EventType 5",constraints:'cons', discard:true, companyId:4,companyVO:null},
            {id:6,name:"EventType 6",constraints:'cons', discard:true, companyId:4,companyVO:null},
            {id:7,name:"EventType 7",constraints:'cons', discard:true, companyId:4,companyVO:null},
            {id:8,name:"EventType 8",constraints:'cons', discard:true, companyId:4,companyVO:null},
            {id:9,name:"EventType 9",constraints:'cons', discard:true, companyId:4,companyVO:null},
            {id:10,name:"EventType 10",constraints:'cons', discard:true, companyId:4,companyVO:null},
            {id:11,name:"EventType 11",constraints:'cons', discard:true, companyId:4,companyVO:null},
            {id:12,name:"EventType 12",constraints:'cons', discard:true, companyId:4,companyVO:null},
        ];
    }

    saveEventType(product:IEventType):boolean{
        return true;
    }

    deleteEventType(id:number):boolean{
        return true;
    }

    updateEventType(product:IEventType):boolean{
        return true;
    }

}