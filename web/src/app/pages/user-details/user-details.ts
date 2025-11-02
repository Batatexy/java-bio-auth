import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserRoles } from '../../models/userRole/userRoles';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user-details',
  imports: [],
  templateUrl: './user-details.html',
  styleUrl: './user-details.scss'
})
export class UserDetails {
  private userService = inject(UserService);
  private cd = inject(ChangeDetectorRef);
  private router = inject(Router);
  private activatedRoute = inject(ActivatedRoute);


  id: string | null = null;
  userDetails?: UserRoles;

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.paramMap.get("id");

    if (this.id) {
      this.userService.findById(this.id).subscribe({
        next: (userRoles) => {
          this.userDetails = userRoles;
        },
        complete: () => {
          this.cd.detectChanges();
        },
        error: (error) => {
          this.router.navigate(['/dashboard']);
        }
      });
    }
  }

  getUserRoles(): UserRoles | undefined {
    return this.userService.getUserRoles();
  }
}
