import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root',
})
export class ToastMessageService {
  private readonly positionClass = 'toast-bottom-right';
  private readonly positionClassAboveConfirmationButton =
    'toast-bottom-right-124px-up';

  constructor(private toastr: ToastrService) {}

  success(
    message: string,
    title: string = 'Success',
    aboveConfirmationButton?: boolean
  ): void {
    const positionClass = aboveConfirmationButton
      ? this.positionClassAboveConfirmationButton
      : this.positionClass;
    this.toastr.success(message, title, { positionClass });
  }

  info(
    message: string,
    title: string = 'Info',
    aboveConfirmationButton?: boolean
  ): void {
    const positionClass = aboveConfirmationButton
      ? this.positionClassAboveConfirmationButton
      : this.positionClass;
    this.toastr.info(message, title, { positionClass });
  }

  warning(
    message: string,
    title: string = 'Attention',
    aboveConfirmationButton?: boolean
  ): void {
    const positionClass = aboveConfirmationButton
      ? this.positionClassAboveConfirmationButton
      : this.positionClass;
    this.toastr.warning(message, title, { positionClass });
  }

  error(
    message: string,
    title: string = 'Error',
    aboveConfirmationButton?: boolean
  ): void {
    const positionClass = aboveConfirmationButton
      ? this.positionClassAboveConfirmationButton
      : this.positionClass;
    this.toastr.error(message, title, { positionClass });
  }
}
