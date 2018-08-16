import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { Injectable } from '@angular/core';
import { LoginComponent } from './LoginComponent/login.component';

import {SummaryPanelComponent} from './summary/summaryPanel.component';
import {BottomToolbarComponent} from './main/bottombar.component';
import {MainPanelComponent} from './main/mainpanel.component';
import {RightPanelComponent} from './main/rightpanel.component';
import {TitleBarComponent} from './main/titlebar.component';
import { DashboardComponent } from './main/dashboard.component';

import { AppService } from './app.service';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS
} from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { AuthGuard } from './RouteGuards/auth-guard.service';

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const xhr = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}

const appRoutes: Routes=[
  {path: 'login' , component : LoginComponent},
  {path: 'dashboard' , component : DashboardComponent},
  { path: '' , redirectTo : 'login' , pathMatch : 'full'},
   { path : '**' , redirectTo : 'login' , pathMatch : 'full'}
 ];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    SummaryPanelComponent,
    BottomToolbarComponent,
    MainPanelComponent,
    RightPanelComponent,
    TitleBarComponent,
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [AppService, { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
