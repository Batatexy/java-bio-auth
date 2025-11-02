import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserCardComponent } from "../../components/userCard/userCard.component";
import { UserList } from '../../models/user/userList';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
  imports: [UserCardComponent]
})
export class UsersComponent implements OnInit {
  private userService = inject(UserService);
  private cd = inject(ChangeDetectorRef);
  userList?: UserList;

  private router = inject(Router);

  ngOnInit() {
    this.userService.list().subscribe({
      next: (userList) => {
        this.userList = userList;
      },
      complete: () => {
        this.cd.detectChanges();
      }
    });
  }

  onView(ruralPropertiesId: string) {
    this.router.navigate(['/user-details', ruralPropertiesId]);
  }
}
