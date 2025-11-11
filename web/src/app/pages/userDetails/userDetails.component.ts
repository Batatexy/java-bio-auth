import { ChangeDetectorRef, Component, inject, Input, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserRoles } from '../../models/userRole/userRoles';
import { UserService } from '../../services/user.service';
import { Role } from '../../models/role/role';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserRolesService } from '../../services/userRoles.service';

@Component({
  selector: 'app-user-details',
  imports: [CommonModule, FormsModule],
  templateUrl: './userDetails.component.html',
  styleUrl: './userDetails.component.scss'
})
export class UserDetails {
  private userService = inject(UserService);
  private userRolesService = inject(UserRolesService);
  private cd = inject(ChangeDetectorRef);
  private router = inject(Router);
  private activatedRoute = inject(ActivatedRoute);

  id: string | null = null;
  userDetails?: UserRoles;
  roles = [
    { levelOrder: 1, selected: false, name: 'Usuário padrão' },
    { levelOrder: 2, selected: false, name: 'Diretor de divisões' },
    { levelOrder: 3, selected: false, name: 'Ministro do meio ambiente' },
  ];

  selectedRoles: boolean[] = [false, false, false];

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.paramMap.get("id");

    if (this.id) {
      this.userService.findById(this.id).subscribe({
        next: (userRoles) => {
          this.userDetails = userRoles;
        },
        complete: () => {
          this.cd.detectChanges();

          this.userDetails?.roles.forEach(userRole => {
            this.roles.forEach((role, index) => {
              if (userRole.levelOrder == role.levelOrder) {
                role.selected = true;
                this.selectedRoles[index] = true;
              }
            });
          });
        },
        error: (error) => {
          this.router.navigate(['/dashboard']);
        }
      });
    }
  }

  changeRoles(levelOrder: number, create: boolean) {
    const userId = this.userDetails?.user.id ?? '';
    this.userRolesService.changeRole({ userId: userId, roleId: levelOrder.toString(), create: create }).subscribe({

    });
  }

  getUserRoles(): UserRoles | undefined {
    return this.userService.getUserRoles();
  }
}
