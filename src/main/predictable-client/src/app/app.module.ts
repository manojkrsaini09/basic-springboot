import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { LoginComponent } from './LoginComponent/login.component';
import { DashboardComponent } from './DashboardComponent/dashboard.component';
import { AppService } from './app.service';
import { XhrInterceptor } from './app.interceptor';
import { Observable } from 'rxjs/Observable';

@NgModule({
  declarations: [
    AppComponent , LoginComponent , DashboardComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot([
     {path: 'login' , component : LoginComponent},
     {path: 'dashboard' , component : DashboardComponent},
     { path: '' , redirectTo : 'dashboard' , pathMatch : 'full'},
      { path : '**' , redirectTo : 'dashboard' , pathMatch : 'full'}
    ] , {useHash: true})
  ],
  providers: [AppService ,  { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
