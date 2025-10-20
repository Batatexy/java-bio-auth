import { Component, inject, OnInit } from '@angular/core';
import { NavigationEnd, Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { LoadingComponent } from "../loading/loading.component";
import { UserService } from '../../services/user.service';
import { User } from '../../models/user/user';
import { filter } from 'rxjs';

@Component({
  selector: 'app-layout',
  imports: [RouterOutlet, LoadingComponent, RouterLink, RouterLinkActive,],
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent {
  private router = inject(Router);
  private userService = inject(UserService);

  logout() {
    localStorage.removeItem('email');
    localStorage.removeItem('password');
    this.router.navigate(['/login']);
  }

  getUser(): User | undefined {
    return this.userService.getUser();
  }

}
