import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAppIssue } from '../app-issue.model';
import { AppIssueService } from '../service/app-issue.service';

export const appIssueResolve = (route: ActivatedRouteSnapshot): Observable<null | IAppIssue> => {
  const id = route.params['id'];
  if (id) {
    return inject(AppIssueService)
      .find(id)
      .pipe(
        mergeMap((appIssue: HttpResponse<IAppIssue>) => {
          if (appIssue.body) {
            return of(appIssue.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default appIssueResolve;
