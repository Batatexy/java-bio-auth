import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable, take } from 'rxjs';
import { User } from '../models/user/user';
import { UserFindByEmailAndPasswordDTO } from '../models/user/userFindByEmailAndPasswordDTO';
import { UserList } from '../models/user/userList';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private httpClient = inject(HttpClient);
  private API_URL = 'http://localhost:8080/api/users';

  user?: User;
  tryLogin: UserFindByEmailAndPasswordDTO = { email: '', password: '' };



  list(): Observable<UserList> {
    return this.httpClient.get<UserList>(`${this.API_URL}`).pipe(take(1));
  }

  me(): Observable<User> {
    return this.httpClient.post<User>(`${this.API_URL}/me`, this.tryLogin).pipe(take(1));
  }
}
