import dayjs from 'dayjs/esm';

import { IAppIssue, NewAppIssue } from './app-issue.model';

export const sampleWithRequiredData: IAppIssue = {
  id: 23678,
};

export const sampleWithPartialData: IAppIssue = {
  id: 11115,
  appId: 'duh failing',
  statusIssue: 'what independent',
  issueType: 'note psst generously',
  lastUpdated: dayjs('2024-02-27T01:13'),
  callingMethod: 'cultured',
  isNewInScope: false,
  libraryName: 'incomparable idle gah',
  libraryVersion: 'afore',
  scaTechnology: 'hurdle',
  fixGroupId: 'legalise',
  apiIssue: 'tall',
  appscanVulnId: 'whoa duh oof',
};

export const sampleWithFullData: IAppIssue = {
  id: 22508,
  appId: 'reasoning',
  programmingLanguage: 'carefully',
  severities: 'wherever',
  statusIssue: 'pale',
  issueType: 'queasy or',
  locationIssue: 'quarrelsomely deceivingly',
  dateCreated: dayjs('2024-02-27T12:24'),
  lastUpdated: dayjs('2024-02-26T22:58'),
  lastFound: dayjs('2024-02-27T01:54'),
  callingMethod: 'or whenever',
  isNewInScope: true,
  libraryName: 'now',
  libraryVersion: 'blight to',
  scaTechnology: 'gadzooks',
  fGStatus: 'afore why',
  applicationId: 'atop boo',
  fixGroupId: 'urgently',
  apiIssue: 'even deceivingly',
  sourceIssue: 'near poorly estuary',
  contextIssue: 'who',
  appscanVulnId: 'drench wide-eyed',
  callingLine: 'tensely',
  classIssue: 'willfully',
  cveIssue: 'whoever',
};

export const sampleWithNewData: NewAppIssue = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
