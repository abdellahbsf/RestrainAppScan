import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AppDetailsComponent } from './list/app-details.component';
import { AppDetailsDetailComponent } from './detail/app-details-detail.component';
import { AppDetailsUpdateComponent } from './update/app-details-update.component';
import AppDetailsResolve from './route/app-details-routing-resolve.service';

const appDetailsRoute: Routes = [
  {
    path: '',
    component: AppDetailsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AppDetailsDetailComponent,
    resolve: {
      appDetails: AppDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AppDetailsUpdateComponent,
    resolve: {
      appDetails: AppDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AppDetailsUpdateComponent,
    resolve: {
      appDetails: AppDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default appDetailsRoute;
