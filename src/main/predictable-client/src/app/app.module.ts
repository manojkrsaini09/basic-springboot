import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './LoginComponent/login.component';
import { DashboardComponent } from './DashboardComponent/dashboard.component';

@NgModule({
  declarations: [
    AppComponent , LoginComponent , DashboardComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
     {path: 'loginCom' , component : LoginComponent},
     {path: 'dashboard' , component : DashboardComponent},
     { path: '' , redirectTo : 'dashboard' , pathMatch : 'full'},
      { path : '**' , redirectTo : 'dashboard' , pathMatch : 'full'}
    ] , {useHash: true})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
