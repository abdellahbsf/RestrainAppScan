import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAppDetails, NewAppDetails } from '../app-details.model';

export type PartialUpdateAppDetails = Partial<IAppDetails> & Pick<IAppDetails, 'id'>;

type RestOf<T extends IAppDetails | NewAppDetails> = Omit<T, 'dateCreated' | 'lastUpdated'> & {
  dateCreated?: string | null;
  lastUpdated?: string | null;
};

export type RestAppDetails = RestOf<IAppDetails>;

export type NewRestAppDetails = RestOf<NewAppDetails>;

export type PartialUpdateRestAppDetails = RestOf<PartialUpdateAppDetails>;

export type EntityResponseType = HttpResponse<IAppDetails>;
export type EntityArrayResponseType = HttpResponse<IAppDetails[]>;

@Injectable({ providedIn: 'root' })
export class AppDetailsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/app-details');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(appDetails: NewAppDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(appDetails);
    return this.http
      .post<RestAppDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(appDetails: IAppDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(appDetails);
    return this.http
      .put<RestAppDetails>(`${this.resourceUrl}/${this.getAppDetailsIdentifier(appDetails)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(appDetails: PartialUpdateAppDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(appDetails);
    return this.http
      .patch<RestAppDetails>(`${this.resourceUrl}/${this.getAppDetailsIdentifier(appDetails)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestAppDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestAppDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAppDetailsIdentifier(appDetails: Pick<IAppDetails, 'id'>): number {
    return appDetails.id;
  }

  compareAppDetails(o1: Pick<IAppDetails, 'id'> | null, o2: Pick<IAppDetails, 'id'> | null): boolean {
    return o1 && o2 ? this.getAppDetailsIdentifier(o1) === this.getAppDetailsIdentifier(o2) : o1 === o2;
  }

  addAppDetailsToCollectionIfMissing<Type extends Pick<IAppDetails, 'id'>>(
    appDetailsCollection: Type[],
    ...appDetailsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const appDetails: Type[] = appDetailsToCheck.filter(isPresent);
    if (appDetails.length > 0) {
      const appDetailsCollectionIdentifiers = appDetailsCollection.map(appDetailsItem => this.getAppDetailsIdentifier(appDetailsItem)!);
      const appDetailsToAdd = appDetails.filter(appDetailsItem => {
        const appDetailsIdentifier = this.getAppDetailsIdentifier(appDetailsItem);
        if (appDetailsCollectionIdentifiers.includes(appDetailsIdentifier)) {
          return false;
        }
        appDetailsCollectionIdentifiers.push(appDetailsIdentifier);
        return true;
      });
      return [...appDetailsToAdd, ...appDetailsCollection];
    }
    return appDetailsCollection;
  }

  protected convertDateFromClient<T extends IAppDetails | NewAppDetails | PartialUpdateAppDetails>(appDetails: T): RestOf<T> {
    return {
      ...appDetails,
      dateCreated: appDetails.dateCreated?.toJSON() ?? null,
      lastUpdated: appDetails.lastUpdated?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restAppDetails: RestAppDetails): IAppDetails {
    return {
      ...restAppDetails,
      dateCreated: restAppDetails.dateCreated ? dayjs(restAppDetails.dateCreated) : undefined,
      lastUpdated: restAppDetails.lastUpdated ? dayjs(restAppDetails.lastUpdated) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestAppDetails>): HttpResponse<IAppDetails> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestAppDetails[]>): HttpResponse<IAppDetails[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
