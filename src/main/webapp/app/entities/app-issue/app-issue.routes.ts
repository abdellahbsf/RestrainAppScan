import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AppIssueComponent } from './list/app-issue.component';
import { AppIssueDetailComponent } from './detail/app-issue-detail.component';
import { AppIssueUpdateComponent } from './update/app-issue-update.component';
import AppIssueResolve from './route/app-issue-routing-resolve.service';

const appIssueRoute: Routes = [
  {
    path: '',
    component: AppIssueComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AppIssueDetailComponent,
    resolve: {
      appIssue: AppIssueResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AppIssueUpdateComponent,
    resolve: {
      appIssue: AppIssueResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AppIssueUpdateComponent,
    resolve: {
      appIssue: AppIssueResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default appIssueRoute;
