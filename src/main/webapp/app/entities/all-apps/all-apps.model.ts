export interface IAllApps {
  id: number;
  appId?: string | null;
  name?: string | null;
}

export type NewAllApps = Omit<IAllApps, 'id'> & { id: null };
