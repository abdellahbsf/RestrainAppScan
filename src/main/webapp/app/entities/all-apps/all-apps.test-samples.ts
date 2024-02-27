import { IAllApps, NewAllApps } from './all-apps.model';

export const sampleWithRequiredData: IAllApps = {
  id: 26502,
};

export const sampleWithPartialData: IAllApps = {
  id: 25669,
  name: 'uh-huh breakable',
};

export const sampleWithFullData: IAllApps = {
  id: 27528,
  appId: 'poor',
  name: 'indeed',
};

export const sampleWithNewData: NewAllApps = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
