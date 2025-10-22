import { UserDetails } from './pages/user-details/user-details';
import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./pages/login/login.component').then(m => m.LoginComponent),
  },
  {
    path: '',
    loadComponent: () => import('./components/layout/layout.component').then(m => m.LayoutComponent),
    children: [
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full',
      },
      {
        path: 'dashboard',
        loadComponent: () => import('./pages/dashboard/dashboard.component').then(m => m.DashboardComponent),
      },
      {
        path: 'rural-properties/:id',
        loadComponent: () => import('./pages/ruralPropertiesDetails/ruralPropertiesDetails.component').then(m => m.DetailsComponent),
      },
      {
        path: 'users',
        loadComponent: () => import('./pages/users/users.component').then(m => m.UsersComponent),
      },
      {
        path: 'user-details/:id',
        loadComponent: () => import('./pages/user-details/user-details').then(m => m.UserDetails),
      },
    ]
  },
  {
    path: '**',
    redirectTo: 'dashboard'
  },
];
