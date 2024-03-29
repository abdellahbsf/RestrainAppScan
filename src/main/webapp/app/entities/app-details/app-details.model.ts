import dayjs from 'dayjs/esm';

export interface IAppDetails {
  id: number;
  appId?: string | null;
  riskRating?: string | null;
  criticalIssues?: number | null;
  highIssues?: number | null;
  mediumIssues?: number | null;
  lowIssues?: number | null;
  informationalIssues?: number | null;
  issuesInProgress?: number | null;
  maxSeverity?: string | null;
  correlationState?: string | null;
  rRMaxSeverity?: number | null;
  assetGroupId?: string | null;
  businessImpact?: string | null;
  url?: string | null;
  description?: string | null;
  businessUnit?: string | null;
  businessUnitId?: string | null;
  types?: string | null;
  technology?: string | null;
  testingStatus?: string | null;
  appHosts?: string | null;
  collateralDamagePotential?: string | null;
  targetDistribution?: string | null;
  confidentialityRequirement?: string | null;
  integrityRequirement?: string | null;
  availabilityRequirement?: string | null;
  tester?: string | null;
  businessOwner?: string | null;
  developmentContact?: string | null;
  preferredOfferingType?: string | null;
  assetGroupName?: string | null;
  dateCreated?: dayjs.Dayjs | null;
  lastUpdated?: dayjs.Dayjs | null;
  lastComment?: string | null;
  createdBy?: string | null;
  newIssues?: number | null;
  openIssues?: number | null;
  totalIssues?: number | null;
  overallCompliance?: boolean | null;
  canBeDeleted?: boolean | null;
  lockedToSubscription?: boolean | null;
  totalScans?: number | null;
  nScanExecutions?: number | null;
  hasExceedingIssuesNumber?: boolean | null;
  hasExceedingScansNumber?: boolean | null;
  autoDeleteExceededScans?: boolean | null;
}

export type NewAppDetails = Omit<IAppDetails, 'id'> & { id: null };
