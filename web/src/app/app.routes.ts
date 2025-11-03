import { UserDetails } from './pages/userDetails/userDetails.component';
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
        path: 'rural-properties/register',
        loadComponent: () => import('./pages/registerRuralProperties/registerRuralProperties.component').then(m => m.RegisterRuralProperties),
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
        loadComponent: () => import('./pages/userDetails/userDetails.component').then(m => m.UserDetails),
      },
    ]
  },
  {
    path: '**',
    redirectTo: 'dashboard'
  },
];
