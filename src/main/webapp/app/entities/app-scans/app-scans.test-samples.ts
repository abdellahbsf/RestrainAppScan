import dayjs from 'dayjs/esm';

import { IAppScans, NewAppScans } from './app-scans.model';

export const sampleWithRequiredData: IAppScans = {
  id: 2611,
};

export const sampleWithPartialData: IAppScans = {
  id: 17394,
  nameScan: 'indeed hutch',
  iastAgentType: 'forenenst simplistic um',
  iastAgentStatus: 'beyond',
  appName: 'like more ah',
  testOptimizationLevel: 'ew victoriously meanwhile',
};

export const sampleWithFullData: IAppScans = {
  id: 15817,
  appId: 'so pimple incidentally',
  nameScan: 'weakly exhausted inasmuch',
  technology: 'seldom rage',
  iastAgentType: 'for',
  iastAgentStatus: 'confront intestine ecumenist',
  urlScan: 'potentially how boo',
  appName: 'lucky',
  testOptimizationLevel: 'eek',
  numberOfExecutions: 23799,
  createdAt: dayjs('2024-02-27T02:25'),
  lastModified: dayjs('2024-02-26T20:09'),
};

export const sampleWithNewData: NewAppScans = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
