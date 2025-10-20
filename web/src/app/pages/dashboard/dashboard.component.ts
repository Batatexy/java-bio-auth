import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { RuralPropertiesList } from '../../models/ruralProperties/ruralPropertiesList';
import { Router } from '@angular/router';
import { RuralPropertiesCardComponent } from '../../components/ruralPropertiesCard/ruralPropertiesCard.component';
import { RuralPropertiesService } from '../../services/ruralProperties.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  imports: [RuralPropertiesCardComponent]
})
export class DashboardComponent implements OnInit {
  private ruralPropertiesService = inject(RuralPropertiesService);
  private cd = inject(ChangeDetectorRef);
  ruralPropertiesList?: RuralPropertiesList;
  private router = inject(Router);

  ngOnInit() {
    this.ruralPropertiesService.list().subscribe({
      next: (ruralPropertiesList) => {
        this.ruralPropertiesList = ruralPropertiesList;
      },
      complete: () => {
        this.cd.detectChanges();
      }
    });
  }

  onView(ruralPropertiesId: string) {
    this.router.navigate(['/rural-properties', ruralPropertiesId]);
  }

}
