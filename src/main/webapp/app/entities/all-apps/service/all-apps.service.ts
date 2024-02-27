import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAllApps, NewAllApps } from '../all-apps.model';

export type PartialUpdateAllApps = Partial<IAllApps> & Pick<IAllApps, 'id'>;

export type EntityResponseType = HttpResponse<IAllApps>;
export type EntityArrayResponseType = HttpResponse<IAllApps[]>;

@Injectable({ providedIn: 'root' })
export class AllAppsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/all-apps');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(allApps: NewAllApps): Observable<EntityResponseType> {
    return this.http.post<IAllApps>(this.resourceUrl, allApps, { observe: 'response' });
  }

  update(allApps: IAllApps): Observable<EntityResponseType> {
    return this.http.put<IAllApps>(`${this.resourceUrl}/${this.getAllAppsIdentifier(allApps)}`, allApps, { observe: 'response' });
  }

  partialUpdate(allApps: PartialUpdateAllApps): Observable<EntityResponseType> {
    return this.http.patch<IAllApps>(`${this.resourceUrl}/${this.getAllAppsIdentifier(allApps)}`, allApps, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAllApps>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAllApps[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAllAppsIdentifier(allApps: Pick<IAllApps, 'id'>): number {
    return allApps.id;
  }

  compareAllApps(o1: Pick<IAllApps, 'id'> | null, o2: Pick<IAllApps, 'id'> | null): boolean {
    return o1 && o2 ? this.getAllAppsIdentifier(o1) === this.getAllAppsIdentifier(o2) : o1 === o2;
  }

  addAllAppsToCollectionIfMissing<Type extends Pick<IAllApps, 'id'>>(
    allAppsCollection: Type[],
    ...allAppsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const allApps: Type[] = allAppsToCheck.filter(isPresent);
    if (allApps.length > 0) {
      const allAppsCollectionIdentifiers = allAppsCollection.map(allAppsItem => this.getAllAppsIdentifier(allAppsItem)!);
      const allAppsToAdd = allApps.filter(allAppsItem => {
        const allAppsIdentifier = this.getAllAppsIdentifier(allAppsItem);
        if (allAppsCollectionIdentifiers.includes(allAppsIdentifier)) {
          return false;
        }
        allAppsCollectionIdentifiers.push(allAppsIdentifier);
        return true;
      });
      return [...allAppsToAdd, ...allAppsCollection];
    }
    return allAppsCollection;
  }
}
