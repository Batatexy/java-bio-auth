import { RuralProperties } from './../../models/ruralProperties/ruralProperties';
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
import { RuralPropertiesService } from '../../services/ruralProperties.service';
import { RuralPropertiesCreate } from '../../models/ruralProperties/ruralPropertiesCreate';

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
  private ruralPropertiesService = inject(RuralPropertiesService);
  private userRolesService = inject(UserRolesService);
  private formBuilder = inject(FormBuilder);

  loginForm!: FormGroup;
  screen: number = 0;

  previewRuralPropertiesUrl: string = '/no-profile-image.png';
  previewDigitalUrl: string[] = ['/finger-print-image.png'];
  errorMessage = '';
  acceptedFormats = ['image/jpeg', 'image/png', 'image/webp'];
  maxSizeMB = 2;

  ruralPropertiesImageFile: File | null = null;

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      placeName: ['', Validators.required],
      description: ['', Validators.required],
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

    const ruralProperties: RuralPropertiesCreate = {
      placeName: this.loginForm.value.placeName,
      description: this.loginForm.value.description,
      ownerName: this.loginForm.value.ownerName,
      address: this.loginForm.value.address,
      size: this.loginForm.value.size,
      agroChemicals: this.loginForm.value.agroChemicals,
      agrochemicalsLevelOrder: this.loginForm.value.agrochemicalsLevelOrder,
      levelOrder: this.loginForm.value.levelOrder,
    };

    this.ruralPropertiesService.register(ruralProperties, this.ruralPropertiesImageFile).subscribe({
      next: () => {

      },
      complete: () => {
        this.router.navigate(['/dashboard']);
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
      this.previewRuralPropertiesUrl = reader.result as string;
      this.ruralPropertiesImageFile = file;
    };
    reader.readAsDataURL(file);
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
