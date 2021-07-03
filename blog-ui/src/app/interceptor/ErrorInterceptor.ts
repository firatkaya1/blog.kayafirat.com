import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError }                               from 'rxjs';
import { Injectable }                                           from '@angular/core';
import { catchError }                                           from 'rxjs/operators';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

    constructor() { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(catchError((err) => {
            if (err.status === 401) {
                // auto logout if 401 response returned from api
                window.location.reload();
            } 
            if (err.status === 403) {
                // auto logout if 403 response returned from api
                return throwError(err.error);
            } 
            if(err.status === 409) {
                return throwError(err.error);
            }
            const error =  err.status || err.error.message || err.statusText ;            
            return throwError(error);
        }))
    }
}