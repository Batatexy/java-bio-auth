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
import { UserCreate } from '../../models/user/userCreate';

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
  screen: number = 0;

  previewUserUrl: string = '/no-profile-image.png';
  previewDigitalUrl: string[] = ['/finger-print-image.png'];
  errorMessage = '';
  acceptedFormats = ['image/jpeg', 'image/png', 'image/webp'];
  maxSizeMB = 2;

  userImageFile: File | null = null;
  userFingerPrintFiles: File[] = [];

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      name: ['', Validators.required],
      userPassword: ['', Validators.required],
      userImage: [''],
      userFingerPrints: [[]]
    });
  }

  onRegister() {
    if (this.loginForm.invalid) {
      this.errorMessage = 'Preencha todos os campos obrigatórios.';
      return;
    }

    const { email, name, userPassword } = this.loginForm.value;

    this.userService.findByEmail({ email }).subscribe({
      next: (user) => {
        if (user) {
          alert('Email já cadastrado.');
          return;
        }
      },
      error: () => {
        const register: UserCreate = {
          fullName: name,
          email,
          password: userPassword
        };

        this.userService.register(register, this.userImageFile, this.userFingerPrintFiles).subscribe({
          next: (user) => {
            console.log('Usuário registrado:', user);
            this.userService.setUser(user);
          },
          complete: () => {
            const savedUser = this.userService.getUser;
            if (!savedUser) {
              alert('Falha no login.');
              return;
            }
            localStorage.setItem('email', email);
            localStorage.setItem('password', userPassword);
            this.router.navigate(['/dashboard']);
          }
        });
      }
    });
  }

  onLogin() {
    if (this.loginForm.value.email && this.loginForm.value.userPassword) {
      this.userService.tryLogin = {
        email: this.loginForm.value.email,
        password: this.loginForm.value.userPassword
      };

      this.userService.me().subscribe({
        next: (user) => {
          this.userService.setUser(user);
        },
        complete: () => {
          const savedUser = this.userService.getUser;
          if (!savedUser) {
            alert('Login failed');
            return;
          } else {
            localStorage.setItem('email', this.loginForm.value.email);
            localStorage.setItem('password', this.loginForm.value.userPassword);
            this.router.navigate(['/dashboard']);
          }
        }
      });
    }
  }

  onFileUserChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) return;

    const file = input.files[0];
    this.errorMessage = '';

    const isValidFormat = this.acceptedFormats.includes(file.type);
    const isValidSize = file.size / 1024 / 1024 <= this.maxSizeMB;

    if (!isValidFormat) {
      this.errorMessage = 'Formato inválido. Use JPG, PNG ou WEBP.';
      return;
    }

    if (!isValidSize) {
      this.errorMessage = `A imagem deve ter no máximo ${this.maxSizeMB}MB.`;
      return;
    }

    const reader = new FileReader();
    reader.onload = () => {
      this.previewUserUrl = reader.result as string;
      this.userImageFile = file;
    };
    reader.readAsDataURL(file);
  }

  onFileDigitalChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) return;

    this.errorMessage = '';
    const files = Array.from(input.files);
    const validFiles: File[] = [];
    const previews: string[] = [];

    for (const file of files) {
      const isValidFormat = this.acceptedFormats.includes(file.type);
      const isValidSize = file.size / 1024 / 1024 <= this.maxSizeMB;

      if (!isValidFormat) {
        this.errorMessage = 'Formato inválido. Use JPG, PNG ou WEBP.';
        continue;
      }

      if (!isValidSize) {
        this.errorMessage = `A imagem deve ter no máximo ${this.maxSizeMB}MB.`;
        continue;
      }

      validFiles.push(file);

      const reader = new FileReader();
      reader.onload = () => {
        previews.push(reader.result as string);
        this.previewDigitalUrl = [...previews];
      };
      reader.readAsDataURL(file);
    }

    this.userFingerPrintFiles = validFiles;
  }

  private onChange = (files: File[] | null) => { };
  registerOnChange(fn: (value: File[] | null) => void): void {
    this.onChange = fn;
  }

  private onTouched = () => { };
  registerOnTouched(fn: () => void): void {
    this.onTouched = fn;
  }

  triggerTouched() {
    this.onTouched();
  }
}
