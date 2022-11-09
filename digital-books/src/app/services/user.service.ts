import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const baseUrl = 'http://localhost:9091/api/test/';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(baseUrl + 'all', { responseType: 'text' });
  }

  getUserProfile(): Observable<any> {
    return this.http.get(baseUrl + 'user', { responseType: 'text' });
  }

  getReaderProfile(): Observable<any> {
    return this.http.get(baseUrl + 'reader', { responseType: 'text' });
  }

  getAuthorProfile(): Observable<any> {
    return this.http.get(baseUrl + 'author', { responseType: 'text' });
  }
}
