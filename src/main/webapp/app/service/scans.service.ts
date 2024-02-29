import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class ScansService {
  private appUrl = 'api/all/applications';

  constructor(private http: HttpClient) {}

  getAllApplications(): Observable<any> {
    return this.http.get(this.appUrl);
  }

  getApplicationDetails(): Observable<any> {
    return this.http.get('api/application/details/521b5339-7461-4f5d-804a-6027ff441dc1');
  }

  getApplicationScans(): Observable<any> {
    return this.http.get('api/application-scans/521b5339-7461-4f5d-804a-6027ff441dc1');
  }

  getApplicationIssues(): Observable<any> {
    return this.http.get('api/application-issues/521b5339-7461-4f5d-804a-6027ff441dc1');
  }
}

/*
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { switchMap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class ScansService {
  private appUrl = 'api/all/applications';

  constructor(private http: HttpClient) {}

  getAllApplications(): Observable<any> {
    return this.http.get(this.appUrl);
  }

  getApplicationDetails(): Observable<any> {
    return this.getAllApplications().pipe(
      switchMap(applications => {
        const applicationId = applications?.Items?.[0]?.Id; // Adjust the logic based on your actual data structure
        return applicationId ? this.http.get(`api/application/details/${applicationId}`) : of(null);
      })
    );
  }

  getApplicationScans(): Observable<any> {
    return this.getAllApplications().pipe(
      switchMap(applications => {
        const applicationId = applications?.Items?.[0]?.Id; // Adjust the logic based on your actual data structure
        return applicationId ? this.http.get(`api/application-scans/${applicationId}`) : of(null);
      })
    );
  }

  getApplicationIssues(): Observable<any> {
    return this.getAllApplications().pipe(
      switchMap(applications => {
        const applicationId = applications?.Items?.[0]?.Id; // Adjust the logic based on your actual data structure
        return applicationId ? this.http.get(`api/application-issues/${applicationId}`) : of(null);
      })
    );
  }
}

 */
