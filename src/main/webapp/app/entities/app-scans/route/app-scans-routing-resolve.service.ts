import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAppScans } from '../app-scans.model';
import { AppScansService } from '../service/app-scans.service';

export const appScansResolve = (route: ActivatedRouteSnapshot): Observable<null | IAppScans> => {
  const id = route.params['id'];
  if (id) {
    return inject(AppScansService)
      .find(id)
      .pipe(
        mergeMap((appScans: HttpResponse<IAppScans>) => {
          if (appScans.body) {
            return of(appScans.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default appScansResolve;
