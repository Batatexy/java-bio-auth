import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';

import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { LoadingComponent } from "../../components/loading/loading.component";

@Component({
  selector: 'app-login',
  imports: [
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
    LoadingComponent
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private router = inject(Router);
  private userService = inject(UserService);
  private formBuilder = inject(FormBuilder);

  loginForm!: FormGroup;
  login: boolean = true;

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      // name: ['', Validators.required],
      userPassword: ['', Validators.required],
      // userImage: ['', Validators.required],
      // userFingerPrints: [[]]
    });
  }

  onLogin() {
    this.userService.tryLogin = {
      email: this.loginForm.value.email,
      password: this.loginForm.value.userPassword
    }

    this.userService.me().subscribe({
      next: (user) => {
        this.userService.user = user;
      },
      complete: () => {
        if (!this.userService.user) {
          alert('Login failed');
          return;
        }
        else {
          localStorage.setItem('email', this.loginForm.value.email);
          localStorage.setItem('password', this.loginForm.value.userPassword);
          this.router.navigate(['/dashboard']);
        }

      }
    });
  }



}

