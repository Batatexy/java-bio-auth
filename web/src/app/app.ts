import { Component, inject, signal } from '@angular/core';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { UserService } from './services/user.service';
import { filter } from 'rxjs';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('web');

  private userService = inject(UserService);
  private router = inject(Router);

  ngOnInit() {
    this.userService.tryLogin.email = localStorage.getItem('email') || '';
    this.userService.tryLogin.password = localStorage.getItem('password') || '';

    if (this.userService.tryLogin.email && this.userService.tryLogin.password) {
      this.userService.me().subscribe({
        next: (user) => {
          this.userService.setUser(user);
        },
        complete: () => {
          if (!this.userService.getUser()) {
            this.router.navigate(['/login']);
          }
        }
      });
    }



    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(() => {
        if (!this.userService.getUser()) {
          this.router.navigate(['/login']);
        }
      });
  }
}
