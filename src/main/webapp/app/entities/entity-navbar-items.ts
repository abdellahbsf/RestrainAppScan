import NavbarItem from 'app/layouts/navbar/navbar-item.model';

export const EntityNavbarItems: NavbarItem[] = [
  {
    name: 'Client',
    route: '/client',
    translationKey: 'global.menu.entities.client',
  },
  {
    name: 'AllApps',
    route: '/all-apps',
    translationKey: 'global.menu.entities.allApps',
  },
  {
    name: 'AppDetails',
    route: '/app-details',
    translationKey: 'global.menu.entities.appDetails',
  },
  {
    name: 'AppIssue',
    route: '/app-issue',
    translationKey: 'global.menu.entities.appIssue',
  },
  {
    name: 'AppScans',
    route: '/app-scans',
    translationKey: 'global.menu.entities.appScans',
  },
];
