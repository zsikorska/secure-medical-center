import {Component, OnInit} from '@angular/core';
import {AccountService} from "../../services/account/account.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss'],
})
export class NavBarComponent implements OnInit {

  constructor(
    public accountService: AccountService,

    private router: Router
  ) {}

  ngOnInit(): void {
  }

  logout() {
    this.accountService.logout();
    this.router.navigateByUrl('/');
  }

  navigateToLogin() {
    this.router.navigateByUrl('account/login');
  }

  navigateToRegister() {
    this.router.navigateByUrl('account/register');
  }
}
