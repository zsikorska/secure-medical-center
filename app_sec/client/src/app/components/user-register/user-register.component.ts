import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {AccountService} from "../../services/account/account.service";
import {Router} from "@angular/router";
import {DatePipe} from "@angular/common";
import {ToastMessageService} from "../../services/toastr/toastr.service";

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.scss'],
  providers: [DatePipe]
})
export class UserRegisterComponent implements OnInit {

  registerForm!: FormGroup;

  constructor(
    public accountService: AccountService,
    private formBuilder: FormBuilder,
    private router: Router,
    private datePipe: DatePipe,
    private toastService: ToastMessageService
  ) { }

  ngOnInit(): void {
    this.buildForm();
  }

  private buildForm(): void {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      confirmPassword: ['', [Validators.required, this.matchValues('password')]],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      birthdate: ['', Validators.required]
    });
  }

  registerAccount() {
    if (this.registerForm.invalid) {
      return;
    }

    this.registerForm.value['birthdate'] = this.datePipe.transform(this.registerForm.value['birthdate'], 'yyyy-MM-dd');

    this.accountService.register(this.registerForm.value).subscribe(_ => {
      this.toastService.success('You are registered');
      this.router.navigateByUrl('account/login');
    }, _ => {
      this.toastService.warning('Wrong data');
    })

  }

  matchValues(matchTo: string): ValidatorFn {
    return (control: AbstractControl) =>
      control?.value === (control?.parent as FormGroup)?.controls[matchTo].value ? null : { isMatching: true };
  }
}
