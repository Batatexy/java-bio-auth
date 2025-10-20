import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable, take } from 'rxjs';
import { User } from '../models/user/user';
import { UserFindByEmailAndPasswordDTO } from '../models/user/userFindByEmailAndPassword';
import { UserList } from '../models/user/userList';
import { UserCreate } from '../models/user/userCreate';
import { UserFindByEmail } from '../models/user/userFindByEmail';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private httpClient = inject(HttpClient);
  private API_URL = 'http://localhost:8080/api/users';

  private user?: User;
  tryLogin: UserFindByEmailAndPasswordDTO = { email: '', password: '' };

  list(): Observable<UserList> {
    return this.httpClient.get<UserList>(`${this.API_URL}`).pipe(take(1));
  }

  me(): Observable<User> {
    return this.httpClient.post<User>(`${this.API_URL}/me`, this.tryLogin).pipe(take(1));
  }

  findByEmail(user: UserFindByEmail): Observable<User> {
    return this.httpClient.post<User>(`${this.API_URL}/find-by-email`, user).pipe(take(1));
  }

  register(registerUser: UserCreate, userImage: File | null, digitalImages: File[] | null) {
    console.log(userImage);

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
        formData.append(`digitalImages[${index}]`, file);
      });
    }

    return this.httpClient.post<User>(`${this.API_URL}`, formData);
  }

  getUser(): User | undefined {
    return this.user;
  }

  setUser(user: User): void {
    this.user = user;
  }
}
