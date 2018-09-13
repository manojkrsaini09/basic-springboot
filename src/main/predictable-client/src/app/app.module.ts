import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule, APP_INITIALIZER } from '@angular/core';
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
import { ProductService } from './Services/product.service';
import { LineService } from './Services/line.service';
import { EventTypeService } from './Services/eventType.service';
import { CompanyService } from './Services/company.service';
import { RoleService } from './Dashboard/RolesComponent/role.service';
import { RoleComponent } from './Dashboard/RolesComponent/role.component';
import { ConfigurationComponent } from './ConfigurationComponent/configuration.component';
import { LeftMenuComponent } from './ConfigurationComponent/LeftMenu/left-menu.component';
import { ContentPanelComponent } from './ConfigurationComponent/RightPanel/content-panel.component';
import { ProfileComponent } from './ProfileComponent/profile.component';
import { AuthInterceptor } from './HttpInterceptor/auth.interceptor';
import { OrganizationComponent } from './OrganizationComponent/organization.component';
import { OrganizationService } from './OrganizationComponent/organization.service';
import {ProductsComponent} from './ConfigurationComponent/Product/product.component';
import {LinesComponent} from './ConfigurationComponent/Line/line.component';
import {EventTypeComponent} from './ConfigurationComponent/Event/eventType.component';
import { UserInfoProvider } from './Services/preload.service';

import {TableModule} from 'primeng/table';
import {ButtonModule} from 'primeng/button';
import {ToastModule} from 'primeng/toast';

import {MessageService} from 'primeng/api';

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const xhr = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}

// export function init_app(provider: UserInfoProvider) {
//    return () => provider.load;
// }

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
    {path: 'configuration',
    component: ConfigurationComponent,
    children : [
              {
              path : '',
              component : ProfileComponent
              },
              {
                path : 'user',
                component : UserListComponent
              },
              {
                path : 'product',
                component : ProductsComponent
              },
              {
                path : 'line',
                component : LinesComponent
              },
              {
                path : 'eventtype',
                component : EventTypeComponent
              },
              {
                path : 'organization',
                component : OrganizationComponent
              },
              {
                path: 'profile',
                redirectTo: ''
              }
        ]
    },
  {path: 'form' , component : FormComponent},
  { path: '' , redirectTo : 'login' , pathMatch : 'full'},
   { path : '**' , redirectTo : 'dashboard' , pathMatch : 'full'}
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
    RoleComponent,
    ConfigurationComponent,
    LeftMenuComponent,
    ContentPanelComponent,
    ProfileComponent,
    OrganizationComponent,
    ProductsComponent,
    LinesComponent,
    EventTypeComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    HttpClientModule,
    FormsModule,
    TableModule,
    ButtonModule,
    ToastModule,
    BrowserAnimationsModule
  ],
  providers: [AppService, UserService, RoleService, OrganizationService, ProductService , CompanyService,MessageService,LineService,EventTypeService,
    UserInfoProvider,
    { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  //  { provide: APP_INITIALIZER, useFactory: init_app, deps: [UserInfoProvider], multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
