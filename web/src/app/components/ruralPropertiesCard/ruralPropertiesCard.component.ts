import { Component, inject, Input } from '@angular/core';
import { RuralProperties } from '../../models/ruralProperties/ruralProperties';
import { UserService } from '../../services/user.service';
import { UserRoles } from '../../models/userRole/userRoles';

@Component({
  selector: 'app-rural-properties-card',
  templateUrl: './ruralPropertiesCard.component.html',
  styleUrls: ['./ruralPropertiesCard.component.css']
})
export class RuralPropertiesCardComponent {
  private userService = inject(UserService);

  @Input() ruralProperties!: RuralProperties;

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

  getUserRoles(): UserRoles | undefined {
    return this.userService.getUserRoles();
  }
}
