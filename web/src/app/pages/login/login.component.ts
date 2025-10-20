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
  previewDigitallUrl: string[] = ['/finger-print-image.png'];
  errorMessage = '';
  acceptedFormats = ['image/jpeg', 'image/png', 'image/webp'];
  maxSizeMB = 2;

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: [''],
      name: [''],
      userPassword: [''],
      userImage: [''],
      userFingerPrints: [['']]
    });
  }

  onRegister() {
    if (this.loginForm.value.email && this.loginForm.value.userPassword && this.loginForm.value.name) {
      this.userService.findByEmail({ email: this.loginForm.value.email }).subscribe({
        next: (user) => {
          if (user) {
            //adicionar toast
            alert('Email já cadastrado.');
            return;
          }
        },
        error: () => {
          let register: UserCreate = {
            fullName: this.loginForm.value.name,
            email: this.loginForm.value.email,
            password: this.loginForm.value.userPassword
          };

          if (this.loginForm.value.userImage == '/no-profile-image.png') {
            this.loginForm.value.userImage = null;
          }

          if (JSON.stringify(this.loginForm.value.userFingerPrints) == JSON.stringify(['/finger-print-image.png'])) {
            this.loginForm.value.userFingerPrints = null;
          }



          this.userService.register(register, this.loginForm.get('userImage')?.value, this.loginForm.get('userFingerPrints')?.value).subscribe({
            next: (user) => {
              console.log(user);
              this.userService.setUser(user);
            },
            complete: () => {
              if (!this.userService.getUser) {
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
      });
    }
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
          if (!this.userService.getUser) {
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
      this.onChange([file]); // envia como array para manter o padrão
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

        this.previewDigitallUrl = [...previews];
        this.onChange(validFiles.length ? validFiles : null);
      };
      reader.readAsDataURL(file);
    }
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
