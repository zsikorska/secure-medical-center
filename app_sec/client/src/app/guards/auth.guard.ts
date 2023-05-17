import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {AccountService} from "../services/account/account.service";
import {ToastMessageService} from "../services/toastr/toastr.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private accountService: AccountService, private toast: ToastMessageService) {}

  canActivate(): Observable<boolean> {
    return this.accountService.currentUser$.pipe(
      map(user => {
        if(user) return true;
        this.toast.error('You shall not pass!');
        return false;
      })
    )
  }

}
