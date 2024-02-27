import dayjs from 'dayjs/esm';

export interface IAppScans {
  id: number;
  appId?: string | null;
  nameScan?: string | null;
  technology?: string | null;
  iastAgentType?: string | null;
  iastAgentStatus?: string | null;
  urlScan?: string | null;
  appName?: string | null;
  testOptimizationLevel?: string | null;
  numberOfExecutions?: number | null;
  createdAt?: dayjs.Dayjs | null;
  lastModified?: dayjs.Dayjs | null;
}

export type NewAppScans = Omit<IAppScans, 'id'> & { id: null };
