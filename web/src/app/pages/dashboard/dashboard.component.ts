import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RuralPropertiesCardComponent } from '../../components/ruralPropertiesCard/ruralPropertiesCard.component';
import { RuralPropertiesList } from '../../models/ruralProperties/ruralPropertiesList';
import { UserRoles } from '../../models/userRole/userRoles';
import { RuralPropertiesService } from '../../services/ruralProperties.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  imports: [RuralPropertiesCardComponent]
})
export class DashboardComponent implements OnInit {
  private ruralPropertiesService = inject(RuralPropertiesService);
  private userService = inject(UserService);
  private cd = inject(ChangeDetectorRef);
  ruralPropertiesList?: RuralPropertiesList;
  private router = inject(Router);

  levelPermission2 = false;
  levelPermission3 = false;

  ngOnInit() {
    this.ruralPropertiesService.list().subscribe({
      next: (ruralPropertiesList) => {
        this.ruralPropertiesList = ruralPropertiesList;
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
      }
    });
  }

  onView(ruralPropertiesId: string) {
    this.router.navigate(['/rural-properties', ruralPropertiesId]);
  }

  registerRuralProperties() {
    if (this.levelPermission2 || this.levelPermission3)
      this.router.navigate(['/rural-properties/register']);
  }

  getUserRoles(): UserRoles | undefined {
    return this.userService.getUserRoles()
  }

}
