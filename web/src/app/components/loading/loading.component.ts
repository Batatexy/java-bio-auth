import { Component, OnInit } from '@angular/core';
import { map, Observable, switchMap, timer } from 'rxjs';
import { LoadingService } from '../../services/loading.service';
import { AsyncPipe, CommonModule } from '@angular/common';

@Component({
  selector: 'app-loading',
  imports: [CommonModule, AsyncPipe],
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.css']
})
export class LoadingComponent {
  isLoading$;
  showSpinner$;

  constructor(private loadingService: LoadingService) {
    this.isLoading$ = this.loadingService.loading$;

    this.showSpinner$ = this.isLoading$.pipe(
      switchMap(loading => loading ? timer(500).pipe(map(() => true)) : [false])
    );
  }
}
