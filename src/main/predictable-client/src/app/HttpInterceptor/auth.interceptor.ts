import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpInterceptor, HttpRequest, 
    HttpHandler, HttpEvent, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { tap, finalize } from 'rxjs/operators';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private  route: Router) {

    }

    //   intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    //     return next.handle(request).do((event: HttpEvent<any>) => {
    //       if (event instanceof HttpResponse) {
    //         // do stuff with response if you want
    //       }
    //     }, (err: any) => {
    //       if (err instanceof HttpErrorResponse {
    //         if (err.status === 401) {
    //             this.route.navigate(['/login']);
    //         }
    //       }
    //     });
    //   }

    intercept(req: HttpRequest<any>, next: HttpHandler) {
        // extend server response observable with logging
        return next.handle(req)
          .pipe(
            tap(
              // Succeeds when there is a response; ignore other events
              event =>  {
                console.log('success response>>>>>>>>');
                console.log(event);
              },
              // Operation failed; error is an HttpErrorResponse
              error =>  {
                  console.log('in error response');
                  if ( error instanceof HttpErrorResponse) {
                      console.log('error status>>>');
                      console.log(error.status);
                    if ( error.status === 401) {
                        console.log('matched');
                        this.route.navigate(['/login']);
                    }
                  }
                  return Observable.throw(error);
              }
            ),
            // Log when response observable either completes or errors
            finalize(() => {
                console.log('in finalize');
                // this.route.navigate(['/login']);
            })
          );
      }
}
