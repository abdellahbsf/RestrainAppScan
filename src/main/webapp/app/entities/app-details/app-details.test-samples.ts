import dayjs from 'dayjs/esm';

import { IAppDetails, NewAppDetails } from './app-details.model';

export const sampleWithRequiredData: IAppDetails = {
  id: 24297,
};

export const sampleWithPartialData: IAppDetails = {
  id: 22213,
  riskRating: 'flight transport aw',
  highIssues: 597,
  mediumIssues: 31868,
  lowIssues: 9158,
  informationalIssues: 30233,
  issuesInProgress: 11223,
  assetGroupId: 'coaxingly inside coordination',
  businessImpact: 'wildly teriyaki',
  url: 'https://nautical-firm.org/',
  confidentialityRequirement: 'lisp remorseful inject',
  availabilityRequirement: 'unruly',
  tester: 'obviate meh',
  developmentContact: 'so dreary geez',
  lastUpdated: dayjs('2024-02-27T04:34'),
  createdBy: 'gosh manoeuvre that',
  totalIssues: 26601,
  canBeDeleted: false,
  hasExceedingIssuesNumber: false,
  autoDeleteExceededScans: false,
};

export const sampleWithFullData: IAppDetails = {
  id: 11315,
  appId: 'cuddly midst woot',
  riskRating: 'grave fatally',
  criticalIssues: 30402,
  highIssues: 5577,
  mediumIssues: 9786,
  lowIssues: 5144,
  informationalIssues: 7628,
  issuesInProgress: 16802,
  maxSeverity: 'lonely filter grovel',
  correlationState: 'virtuous spectacular now',
  rRMaxSeverity: 13758,
  assetGroupId: 'whoa especially yowza',
  businessImpact: 'knavishly boohoo ouch',
  url: 'https://quick-luxury.biz',
  description: 'er impale',
  businessUnit: 'total',
  businessUnitId: 'fooey worriedly',
  types: 'powerless ack',
  technology: 'angry',
  testingStatus: 'around',
  appHosts: 'of face forenenst',
  collateralDamagePotential: 'hungry',
  targetDistribution: 'likewise ew',
  confidentialityRequirement: 'fervently tightly',
  integrityRequirement: 'incredible lap',
  availabilityRequirement: 'falter carefully meh',
  tester: 'grid',
  businessOwner: 'vague wherever composed',
  developmentContact: 'backpack down',
  preferredOfferingType: 'pish crusade',
  assetGroupName: 'lion indeed',
  dateCreated: dayjs('2024-02-27T02:28'),
  lastUpdated: dayjs('2024-02-27T11:49'),
  lastComment: 'where',
  createdBy: 'show',
  newIssues: 20192,
  openIssues: 4528,
  totalIssues: 12045,
  overallCompliance: true,
  canBeDeleted: true,
  lockedToSubscription: false,
  totalScans: 16888,
  nScanExecutions: 18581,
  hasExceedingIssuesNumber: true,
  hasExceedingScansNumber: false,
  autoDeleteExceededScans: false,
};

export const sampleWithNewData: NewAppDetails = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
