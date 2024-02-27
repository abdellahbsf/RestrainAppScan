import dayjs from 'dayjs/esm';

export interface IAppIssue {
  id: number;
  appId?: string | null;
  programmingLanguage?: string | null;
  severities?: string | null;
  statusIssue?: string | null;
  issueType?: string | null;
  locationIssue?: string | null;
  dateCreated?: dayjs.Dayjs | null;
  lastUpdated?: dayjs.Dayjs | null;
  lastFound?: dayjs.Dayjs | null;
  callingMethod?: string | null;
  isNewInScope?: boolean | null;
  libraryName?: string | null;
  libraryVersion?: string | null;
  scaTechnology?: string | null;
  fGStatus?: string | null;
  applicationId?: string | null;
  fixGroupId?: string | null;
  apiIssue?: string | null;
  sourceIssue?: string | null;
  contextIssue?: string | null;
  appscanVulnId?: string | null;
  callingLine?: string | null;
  classIssue?: string | null;
  cveIssue?: string | null;
}

export type NewAppIssue = Omit<IAppIssue, 'id'> & { id: null };
