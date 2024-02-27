import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAppIssue, NewAppIssue } from '../app-issue.model';

export type PartialUpdateAppIssue = Partial<IAppIssue> & Pick<IAppIssue, 'id'>;

type RestOf<T extends IAppIssue | NewAppIssue> = Omit<T, 'dateCreated' | 'lastUpdated' | 'lastFound'> & {
  dateCreated?: string | null;
  lastUpdated?: string | null;
  lastFound?: string | null;
};

export type RestAppIssue = RestOf<IAppIssue>;

export type NewRestAppIssue = RestOf<NewAppIssue>;

export type PartialUpdateRestAppIssue = RestOf<PartialUpdateAppIssue>;

export type EntityResponseType = HttpResponse<IAppIssue>;
export type EntityArrayResponseType = HttpResponse<IAppIssue[]>;

@Injectable({ providedIn: 'root' })
export class AppIssueService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/app-issues');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(appIssue: NewAppIssue): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(appIssue);
    return this.http
      .post<RestAppIssue>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(appIssue: IAppIssue): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(appIssue);
    return this.http
      .put<RestAppIssue>(`${this.resourceUrl}/${this.getAppIssueIdentifier(appIssue)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(appIssue: PartialUpdateAppIssue): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(appIssue);
    return this.http
      .patch<RestAppIssue>(`${this.resourceUrl}/${this.getAppIssueIdentifier(appIssue)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestAppIssue>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestAppIssue[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAppIssueIdentifier(appIssue: Pick<IAppIssue, 'id'>): number {
    return appIssue.id;
  }

  compareAppIssue(o1: Pick<IAppIssue, 'id'> | null, o2: Pick<IAppIssue, 'id'> | null): boolean {
    return o1 && o2 ? this.getAppIssueIdentifier(o1) === this.getAppIssueIdentifier(o2) : o1 === o2;
  }

  addAppIssueToCollectionIfMissing<Type extends Pick<IAppIssue, 'id'>>(
    appIssueCollection: Type[],
    ...appIssuesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const appIssues: Type[] = appIssuesToCheck.filter(isPresent);
    if (appIssues.length > 0) {
      const appIssueCollectionIdentifiers = appIssueCollection.map(appIssueItem => this.getAppIssueIdentifier(appIssueItem)!);
      const appIssuesToAdd = appIssues.filter(appIssueItem => {
        const appIssueIdentifier = this.getAppIssueIdentifier(appIssueItem);
        if (appIssueCollectionIdentifiers.includes(appIssueIdentifier)) {
          return false;
        }
        appIssueCollectionIdentifiers.push(appIssueIdentifier);
        return true;
      });
      return [...appIssuesToAdd, ...appIssueCollection];
    }
    return appIssueCollection;
  }

  protected convertDateFromClient<T extends IAppIssue | NewAppIssue | PartialUpdateAppIssue>(appIssue: T): RestOf<T> {
    return {
      ...appIssue,
      dateCreated: appIssue.dateCreated?.toJSON() ?? null,
      lastUpdated: appIssue.lastUpdated?.toJSON() ?? null,
      lastFound: appIssue.lastFound?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restAppIssue: RestAppIssue): IAppIssue {
    return {
      ...restAppIssue,
      dateCreated: restAppIssue.dateCreated ? dayjs(restAppIssue.dateCreated) : undefined,
      lastUpdated: restAppIssue.lastUpdated ? dayjs(restAppIssue.lastUpdated) : undefined,
      lastFound: restAppIssue.lastFound ? dayjs(restAppIssue.lastFound) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestAppIssue>): HttpResponse<IAppIssue> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestAppIssue[]>): HttpResponse<IAppIssue[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
