import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { CourtsService } from '../services/courts.service';

import { HomeComponent } from './home.component';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;
  let service: CourtsService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomeComponent],
      imports: [HttpClientModule, ReactiveFormsModule, RouterTestingModule, HttpClientTestingModule, FormsModule, BrowserAnimationsModule]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    service = TestBed.inject(CourtsService)
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Register and Login toggle should work properly', () => {
    component.isLogin = true;
    component.isLoginCheck();
    expect(component.isLogin).toBeFalsy();
  });

  it('Login method should work properly', () => {
    component.login();
    fixture.detectChanges();
    expect(component.loginForm.valid).toBeFalsy();
    expect(component.errorMsg).toEqual('Please Fill All the Details');
    component.loginForm.get('vehicle_phone_no')?.patchValue('s');
    component.loginForm.get('password')?.patchValue('d');
    expect(component.loginForm.valid).toBeTruthy();
    let eventServiceMock = spyOn(service, 'loginUser').and.callThrough();
    component.login();
    fixture.detectChanges();
    expect(eventServiceMock).toHaveBeenCalled();
  });

  it('Register method should work properly', () => {
    component.register({});
    fixture.detectChanges();
    expect(component.registerForm.valid).toBeFalsy();
    expect(component.errorMsg).toEqual('Please Fill All the Details');
    component.registerForm.get('name')?.patchValue('s');
    component.registerForm.get('mobileNo')?.patchValue('d');
    component.registerForm.get('email')?.patchValue('d');
    component.registerForm.get('password')?.patchValue('d');
    component.registerForm.get('address')?.patchValue('d');
    component.registerForm.get('vehicleType')?.patchValue('d');
    component.registerForm.get('vehicleNo')?.patchValue('d');
    expect(component.registerForm.valid).toBeTruthy();
    let eventServiceMock = spyOn(service, 'registeruser').and.callThrough();
    component.register({});
    fixture.detectChanges();
    expect(eventServiceMock).toHaveBeenCalled();
  });
});
