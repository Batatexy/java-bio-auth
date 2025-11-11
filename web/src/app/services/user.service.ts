import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable, take } from 'rxjs';
import { UserRoles } from '../models/userRole/userRoles';
import { UserCreate } from '../models/user/userCreate';
import { UserFindByEmail } from '../models/user/userFindByEmail';
import { UserFindByEmailAndPasswordDTO } from '../models/user/userFindByEmailAndPassword';
import { UserList } from '../models/user/userList';
import { User } from '../models/user/user';
import { Role } from '../models/role/role';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private httpClient = inject(HttpClient);
  private API_URL = 'http://localhost:8080/api/users';

  private userRoles?: UserRoles;
  tryLogin: UserFindByEmailAndPasswordDTO = { email: '', password: '' };

  userImageFile: File | null = null;
  userFingerPrintFiles: File[] = [];

  list(): Observable<UserList> {
    return this.httpClient.get<UserList>(`${this.API_URL}`).pipe(take(1));
  }

  me(): Observable<UserRoles> {
    return this.httpClient.post<UserRoles>(`${this.API_URL}/me`, this.tryLogin).pipe(take(1));
  }

  findByDigitalImage(digitalImage: File) {
    const formData = new FormData();
    if (digitalImage) {
      formData.append('digitalImage', digitalImage);
    }

    return this.httpClient.post<UserRoles>(`${this.API_URL}/find-by-digital`, formData);
  }

  findByEmail(user: UserFindByEmail): Observable<number> {
    return this.httpClient.post<number>(`${this.API_URL}/find-by-email`, user).pipe(take(1));
  }

  findById(id: string): Observable<UserRoles> {
    return this.httpClient.get<UserRoles>(`${this.API_URL}/${id}`).pipe(take(1));
  }

  register(registerUser: UserCreate, userImage: File | null, digitalImages: File[] | null) {
    const formData = new FormData();
    formData.append(
      'data',
      new Blob([JSON.stringify(registerUser)], { type: 'application/json' })
    );
    if (userImage) {
      formData.append('userImage', userImage);
    }

    if (digitalImages) {
      digitalImages.forEach((file, index) => {
        formData.append(`digitalImage${index + 1}`, file);
      });
    }

    return this.httpClient.post<User>(`${this.API_URL}`, formData);
  }




  getUserRoles(): UserRoles | undefined {
    return this.userRoles;
  }

  setUserRoles(userRoles: UserRoles): void {
    this.userRoles = userRoles;
  }

  setUser(user: User): void {
    if (this.userRoles) {
      this.userRoles.user = user;
    } else {
      this.userRoles = { user: user, roles: [] };
    }
  }
}
