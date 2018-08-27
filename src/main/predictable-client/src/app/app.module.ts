import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { Injectable } from '@angular/core';
import { LoginComponent } from './LoginComponent/login.component';

import {SummaryPanelComponent} from './summary/summaryPanel.component';
import {BottomToolbarComponent} from './Dashboard/bottombar.component';
import {MainPanelComponent} from './Dashboard/mainpanel.component';
import {RightPanelComponent} from './Dashboard/rightpanel.component';
import {TitleBarComponent} from './Dashboard/titlebar.component';
import { DashboardComponent } from './Dashboard/dashboard.component';

import { AppService } from './app.service';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS
} from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { AuthGuard } from './RouteGuards/auth-guard.service';
import { FormComponent } from './FormComponent/form.component';
import { UserListComponent } from './Dashboard/UsersComponent/user-list.component';
import { UserEditComponent } from './Dashboard/UsersComponent/user-edit.component';
import { UserService } from './Dashboard/UsersComponent/user.service';
import { RoleService } from './Dashboard/RolesComponent/role.service';
import { RoleComponent } from './Dashboard/RolesComponent/role.component';

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const xhr = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}

const appRoutes: Routes = [
  {path: 'login' , component : LoginComponent},
  {path: 'dashboard',
  component : DashboardComponent,
     children : [{
       path : '',
       component : MainPanelComponent
     },
      {
        path : 'user',
        component : UserListComponent
      },
      {
        path : 'user/:id/edit',
        component : UserEditComponent
      },
      {
        path : 'role',
        component  : RoleComponent
      }
    ]
    },
  {path: 'form' , component : FormComponent},
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
    FormComponent,
    UserListComponent,
    UserEditComponent,
    RoleComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [AppService, UserService, RoleService,{ provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
