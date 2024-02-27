export interface IClient {
  id: number;
  keyId?: string | null;
  keySecret?: string | null;
  token?: string | null;
}

export type NewClient = Omit<IClient, 'id'> & { id: null };
