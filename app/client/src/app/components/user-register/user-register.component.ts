import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AccountService} from "../../services/account/account.service";
import {Router} from "@angular/router";
import {DatePipe} from "@angular/common";

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
    private datePipe: DatePipe
  ) { }

  ngOnInit(): void {
    this.buildForm();
  }

  private buildForm(): void {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
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

    this.accountService.register(this.registerForm.value).subscribe(response => {
      this.router.navigateByUrl('account/login');
    }, error => {
      console.log(error);
    })

  }
}
