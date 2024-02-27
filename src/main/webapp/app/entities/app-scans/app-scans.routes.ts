import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AppScansComponent } from './list/app-scans.component';
import { AppScansDetailComponent } from './detail/app-scans-detail.component';
import { AppScansUpdateComponent } from './update/app-scans-update.component';
import AppScansResolve from './route/app-scans-routing-resolve.service';

const appScansRoute: Routes = [
  {
    path: '',
    component: AppScansComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AppScansDetailComponent,
    resolve: {
      appScans: AppScansResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AppScansUpdateComponent,
    resolve: {
      appScans: AppScansResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AppScansUpdateComponent,
    resolve: {
      appScans: AppScansResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default appScansRoute;
