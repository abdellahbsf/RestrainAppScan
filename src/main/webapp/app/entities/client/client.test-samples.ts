import { IClient, NewClient } from './client.model';

export const sampleWithRequiredData: IClient = {
  id: 22491,
};

export const sampleWithPartialData: IClient = {
  id: 7444,
  keySecret: 'saucer whether',
  token: 'absence gadzooks quickly',
};

export const sampleWithFullData: IClient = {
  id: 18781,
  keyId: 'furthermore blond',
  keySecret: 'around',
  token: 'membrane powerfully duh',
};

export const sampleWithNewData: NewClient = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
