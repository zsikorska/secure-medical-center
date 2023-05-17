import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AccountService} from "../../services/account/account.service";
import {Router} from "@angular/router";
import {ToastMessageService} from "../../services/toastr/toastr.service";

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.scss']
})
export class UserLoginComponent implements OnInit {
  userModel!: FormGroup;

  constructor(
    public accountService: AccountService,
    private formBuilder: FormBuilder,
    private router: Router,
    private toastService: ToastMessageService
  ) { }

  ngOnInit(): void {
    this.userModel = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  login() {
    if (this.userModel.valid) {
      this.accountService.login(this.userModel.value).subscribe(_ => {
        this.toastService.success("You are logged");
        this.router.navigateByUrl('/');
      }, _ => {
        this.toastService.error("Wrong login or password");
      });
    }
  }

}
