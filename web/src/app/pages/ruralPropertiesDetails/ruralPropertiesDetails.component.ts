import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { RuralPropertiesService } from '../../services/ruralProperties.service';
import { RuralProperties } from '../../models/ruralProperties/ruralProperties';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user/user';

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

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.paramMap.get("id");

    if (this.id) {
      this.ruralPropertiesService.findById(this.id).subscribe({
        next: (ruralProperties) => {
          this.ruralProperties = ruralProperties;
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
