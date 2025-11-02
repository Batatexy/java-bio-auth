import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable, take } from 'rxjs';
import { UserRoleCreate } from '../models/userRole/userRoleCreate';
import { UserRoleMicro } from '../models/userRole/userRoleMicro';

@Injectable({
  providedIn: 'root'
})
export class UserRolesService {
  private httpClient = inject(HttpClient);
  private API_URL = 'http://localhost:8080/api/users-roles';

  create(userRoleCreate: UserRoleCreate): Observable<UserRoleMicro> {
    console.log('Creating user role with data:');

    console.log(userRoleCreate);

    return this.httpClient.post<UserRoleMicro>(`${this.API_URL}`, userRoleCreate).pipe(take(1));
  }
}
