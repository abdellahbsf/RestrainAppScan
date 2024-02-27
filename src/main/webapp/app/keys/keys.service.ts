import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiKeyService {

  private apiUrl = '/api';

  constructor(private http: HttpClient) { }

  sendApiKeys(apiKeyId: string, apiKeySecret: string) {
    const apiKeys = { apiKeyId, apiKeySecret };
    return this.http.post(`${this.apiUrl}/processApiKeys`, apiKeys);
  }
}
