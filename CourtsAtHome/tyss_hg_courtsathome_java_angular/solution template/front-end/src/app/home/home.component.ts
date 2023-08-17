import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CourtsService } from '../services/courts.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  isLogin: boolean = true;
  registerForm!: FormGroup;
  loginForm!: FormGroup;
  errorMsg: any;

  constructor(private fb: FormBuilder, private router: Router, private courtService: CourtsService) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      vehicle_phone_no: this.fb.control('', [Validators.required]),
      password: this.fb.control('', [Validators.required]),
    })
    this.registerForm = this.fb.group({
      name: this.fb.control('', [Validators.required]),
      mobileNo: this.fb.control('', [Validators.required]),
      email: this.fb.control(''),
      password: this.fb.control('', [Validators.required]),
      address: this.fb.control('', [Validators.required]),
      vehicleType: this.fb.control('', [Validators.required]),
      vehicleNo: this.fb.control('', [Validators.required]),
    })
  }
  isLoginCheck() {
    this.isLogin = !this.isLogin;
  }

  login() {
    if (this.loginForm.valid) {
      this.courtService.loginUser(this.loginForm.value).subscribe({
        next: (loginResponce: any) => {
          this.errorMsg = undefined;
          this.router.navigate(['view-case-details']);
        },
        error: (err: any) => {
          this.errorMsg = err?.error?.msg;
        }
      })
    }else {
      this.errorMsg = 'Please Fill All the Details';
    }
  }

  register(userDetails: any) {
    if (this.registerForm.valid) {
      this.courtService.registeruser(userDetails).subscribe({
        next: (registeruserResponce: any) => {
          this.isLogin = true;
          this.errorMsg = undefined;
          this.registerForm.reset();
        },
        error: (err: any) => {
          console.error("Error Ocured While regiter", err);
        }
      })
    } else {
      this.errorMsg = 'Please Fill All the Details';
    }
  }

}
