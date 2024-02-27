import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAllApps } from '../all-apps.model';
import { AllAppsService } from '../service/all-apps.service';

export const allAppsResolve = (route: ActivatedRouteSnapshot): Observable<null | IAllApps> => {
  const id = route.params['id'];
  if (id) {
    return inject(AllAppsService)
      .find(id)
      .pipe(
        mergeMap((allApps: HttpResponse<IAllApps>) => {
          if (allApps.body) {
            return of(allApps.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default allAppsResolve;
