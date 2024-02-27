import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAppDetails } from '../app-details.model';
import { AppDetailsService } from '../service/app-details.service';

export const appDetailsResolve = (route: ActivatedRouteSnapshot): Observable<null | IAppDetails> => {
  const id = route.params['id'];
  if (id) {
    return inject(AppDetailsService)
      .find(id)
      .pipe(
        mergeMap((appDetails: HttpResponse<IAppDetails>) => {
          if (appDetails.body) {
            return of(appDetails.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default appDetailsResolve;
