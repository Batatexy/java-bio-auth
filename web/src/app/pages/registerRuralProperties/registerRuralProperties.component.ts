import { UserRolesService } from '../../services/userRoles.service';
import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { LoadingComponent } from "../../components/loading/loading.component";
import { UserCreate } from '../../models/user/userCreate';
import { UserService } from '../../services/user.service';
import { UserRoles } from '../../models/userRole/userRoles';

@Component({
  selector: 'app-login',
  imports: [
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
    LoadingComponent
  ],
  templateUrl: './registerRuralProperties.component.html',
  styleUrls: ['./registerRuralProperties.component.css']
})
export class RegisterRuralProperties implements OnInit {
  private router = inject(Router);
  private userService = inject(UserService);
  private userRolesService = inject(UserRolesService);
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
      placeName: ['', Validators.required],
      ownerName: ['', Validators.required],
      address: ['', Validators.required],
      size: ['', Validators.required],
      agroChemicals: ['', Validators.required],
      agrochemicalsLevelOrder: ['', Validators.required],
      levelOrder: ['', Validators.required],
      image: [''],
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
        if (user == 1) {
          alert('Email já cadastrado.');
          return;
        } else {
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
              const user = this.userService.getUserRoles()?.user
              if (user) {
                localStorage.setItem('email', email);
                localStorage.setItem('password', userPassword);

                if (user && user.id) {
                  this.userRolesService.changeRole({ userId: user.id, roleId: '1', create: true }).subscribe({
                    next: (role) => {

                    }
                  });
                }


              } else {
                alert('Falha no login.');
                return;
              }
            }
          });
        }
      }
    });
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
