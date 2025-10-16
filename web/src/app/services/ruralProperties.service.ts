import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable, take } from 'rxjs';
import { RuralPropertiesList } from '../models/ruralProperties/ruralPropertiesList';

@Injectable({
  providedIn: 'root'
})
export class RuralPropertiesService {
  private httpClient = inject(HttpClient);
  private API_URL = 'http://localhost:8080/api/rural-properties';

  list(): Observable<RuralPropertiesList> {
    return this.httpClient.get<RuralPropertiesList>(`${this.API_URL}`).pipe(take(1));
  }

  // create(assessment: AssessmentCreate) {
  //   return this.httpClient.post<AssessmentResponse>(`${this.API_URL}`, assessment, { withCredentials: true, }).pipe(take(1));
  // }

  // update(id: string, assessment: AssessmentUpdate) {
  //   return this.httpClient.put<AssessmentResponse>(`${this.API_URL}/${id}`, assessment, { withCredentials: true, }).pipe(take(1));
  // }

  // delete(id: string) {
  //   return this.httpClient.delete<null>(`${this.API_URL}/${id}`, { withCredentials: true, }).pipe(take(1));
  // }

}
