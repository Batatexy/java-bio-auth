import { Component, inject } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { UserRoles } from '../../models/userRole/userRoles';
import { UserService } from '../../services/user.service';
import { LoadingComponent } from "../loading/loading.component";

@Component({
  selector: 'app-layout',
  imports: [RouterOutlet, LoadingComponent, RouterLink, RouterLinkActive,],
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent {
  private router = inject(Router);
  private userService = inject(UserService);

  levelPermission2 = false;
  levelPermission3 = false;

  ngOnInit() {
    this.getUserRoles()?.roles.forEach(role => {
      if (role.levelOrder == 2) {
        this.levelPermission2 = true;
      }

      if (role.levelOrder == 3) {
        this.levelPermission3 = true;
      }
    });
  }

  logout() {
    localStorage.removeItem('email');
    localStorage.removeItem('password');
    this.router.navigate(['/login']);
  }

  getUserRoles(): UserRoles | undefined {
    return this.userService.getUserRoles();
  }

}
