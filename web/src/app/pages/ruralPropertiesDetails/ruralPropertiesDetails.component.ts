import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RuralProperties } from '../../models/ruralProperties/ruralProperties';
import { UserRoles } from '../../models/userRole/userRoles';
import { RuralPropertiesService } from '../../services/ruralProperties.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-details',
  imports: [CommonModule],
  templateUrl: './ruralPropertiesDetails.component.html',
  styleUrls: ['./ruralPropertiesDetails.component.css']
})
export class DetailsComponent implements OnInit {
  private userService = inject(UserService);
  private ruralPropertiesService = inject(RuralPropertiesService);
  private cd = inject(ChangeDetectorRef);
  private router = inject(Router);
  private activatedRoute = inject(ActivatedRoute);

  id: string | null = null;
  ruralProperties?: RuralProperties;

  levelPermission2 = false;
  levelPermission3 = false;

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.paramMap.get("id");

    if (this.id) {
      this.ruralPropertiesService.findById(this.id).subscribe({
        next: (ruralProperties) => {
          this.ruralProperties = ruralProperties;
        },
        complete: () => {
          this.cd.detectChanges();
          this.getUserRoles()?.roles.forEach(role => {
            if (role.levelOrder == 2) {
              this.levelPermission2 = true;
            }

            if (role.levelOrder == 3) {
              this.levelPermission3 = true;
            }
          });
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
