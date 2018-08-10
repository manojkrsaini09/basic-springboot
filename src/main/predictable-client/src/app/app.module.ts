import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { Injectable } from '@angular/core';
import { DashboardComponent} from './DashboardComponent/dashboard.component';
import { LoginComponent } from './LoginComponent/login.component';
import { AppService } from './app.service';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS
} from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const xhr = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    LoginComponent
  ],
  imports: [
    RouterModule.forRoot([
      {path: 'login' , component : LoginComponent},
      {path: 'dashboard' , component : DashboardComponent},
      { path: '' , redirectTo : 'dashboard' , pathMatch : 'full'},
       { path : '**' , redirectTo : 'dashboard' , pathMatch : 'full'}
     ] , {useHash: true}),
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [AppService, { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
