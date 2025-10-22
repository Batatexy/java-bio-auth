import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { User } from '../../models/user/user';
import { ActivatedRoute, Router } from '@angular/router';
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
  userDetails?: User;

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.paramMap.get("id");

    if (this.id) {
      this.userService.findById(this.id).subscribe({
        next: (userDetails) => {
          this.userDetails = userDetails;
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

  getUser(): User | undefined {
    return this.userService.getUser();
  }
}
