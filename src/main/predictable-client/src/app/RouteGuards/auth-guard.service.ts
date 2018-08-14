import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AppService } from '../app.service';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private app: AppService, private router: Router) {}

  canActivate(): boolean {
      console.log('auth guardd4545');
      console.log(this.app.authenticated);
    if (!this.app.authenticated) {
      //this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
