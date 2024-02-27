import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAppScans, NewAppScans } from '../app-scans.model';

export type PartialUpdateAppScans = Partial<IAppScans> & Pick<IAppScans, 'id'>;

type RestOf<T extends IAppScans | NewAppScans> = Omit<T, 'createdAt' | 'lastModified'> & {
  createdAt?: string | null;
  lastModified?: string | null;
};

export type RestAppScans = RestOf<IAppScans>;

export type NewRestAppScans = RestOf<NewAppScans>;

export type PartialUpdateRestAppScans = RestOf<PartialUpdateAppScans>;

export type EntityResponseType = HttpResponse<IAppScans>;
export type EntityArrayResponseType = HttpResponse<IAppScans[]>;

@Injectable({ providedIn: 'root' })
export class AppScansService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/app-scans');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(appScans: NewAppScans): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(appScans);
    return this.http
      .post<RestAppScans>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(appScans: IAppScans): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(appScans);
    return this.http
      .put<RestAppScans>(`${this.resourceUrl}/${this.getAppScansIdentifier(appScans)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(appScans: PartialUpdateAppScans): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(appScans);
    return this.http
      .patch<RestAppScans>(`${this.resourceUrl}/${this.getAppScansIdentifier(appScans)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestAppScans>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestAppScans[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAppScansIdentifier(appScans: Pick<IAppScans, 'id'>): number {
    return appScans.id;
  }

  compareAppScans(o1: Pick<IAppScans, 'id'> | null, o2: Pick<IAppScans, 'id'> | null): boolean {
    return o1 && o2 ? this.getAppScansIdentifier(o1) === this.getAppScansIdentifier(o2) : o1 === o2;
  }

  addAppScansToCollectionIfMissing<Type extends Pick<IAppScans, 'id'>>(
    appScansCollection: Type[],
    ...appScansToCheck: (Type | null | undefined)[]
  ): Type[] {
    const appScans: Type[] = appScansToCheck.filter(isPresent);
    if (appScans.length > 0) {
      const appScansCollectionIdentifiers = appScansCollection.map(appScansItem => this.getAppScansIdentifier(appScansItem)!);
      const appScansToAdd = appScans.filter(appScansItem => {
        const appScansIdentifier = this.getAppScansIdentifier(appScansItem);
        if (appScansCollectionIdentifiers.includes(appScansIdentifier)) {
          return false;
        }
        appScansCollectionIdentifiers.push(appScansIdentifier);
        return true;
      });
      return [...appScansToAdd, ...appScansCollection];
    }
    return appScansCollection;
  }

  protected convertDateFromClient<T extends IAppScans | NewAppScans | PartialUpdateAppScans>(appScans: T): RestOf<T> {
    return {
      ...appScans,
      createdAt: appScans.createdAt?.toJSON() ?? null,
      lastModified: appScans.lastModified?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restAppScans: RestAppScans): IAppScans {
    return {
      ...restAppScans,
      createdAt: restAppScans.createdAt ? dayjs(restAppScans.createdAt) : undefined,
      lastModified: restAppScans.lastModified ? dayjs(restAppScans.lastModified) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestAppScans>): HttpResponse<IAppScans> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestAppScans[]>): HttpResponse<IAppScans[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
