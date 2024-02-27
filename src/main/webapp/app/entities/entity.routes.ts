import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'client',
    data: { pageTitle: 'restrainAppScanApp.client.home.title' },
    loadChildren: () => import('./client/client.routes'),
  },
  {
    path: 'all-apps',
    data: { pageTitle: 'restrainAppScanApp.allApps.home.title' },
    loadChildren: () => import('./all-apps/all-apps.routes'),
  },
  {
    path: 'app-details',
    data: { pageTitle: 'restrainAppScanApp.appDetails.home.title' },
    loadChildren: () => import('./app-details/app-details.routes'),
  },
  {
    path: 'app-issue',
    data: { pageTitle: 'restrainAppScanApp.appIssue.home.title' },
    loadChildren: () => import('./app-issue/app-issue.routes'),
  },
  {
    path: 'app-scans',
    data: { pageTitle: 'restrainAppScanApp.appScans.home.title' },
    loadChildren: () => import('./app-scans/app-scans.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
