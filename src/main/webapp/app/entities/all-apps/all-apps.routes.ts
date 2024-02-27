import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AllAppsComponent } from './list/all-apps.component';
import { AllAppsDetailComponent } from './detail/all-apps-detail.component';
import { AllAppsUpdateComponent } from './update/all-apps-update.component';
import AllAppsResolve from './route/all-apps-routing-resolve.service';

const allAppsRoute: Routes = [
  {
    path: '',
    component: AllAppsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AllAppsDetailComponent,
    resolve: {
      allApps: AllAppsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AllAppsUpdateComponent,
    resolve: {
      allApps: AllAppsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AllAppsUpdateComponent,
    resolve: {
      allApps: AllAppsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default allAppsRoute;
