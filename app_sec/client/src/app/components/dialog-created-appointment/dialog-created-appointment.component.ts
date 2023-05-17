import {Component, Inject, OnInit} from '@angular/core';
import {ToastMessageService} from "../../services/toastr/toastr.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DialogCreatedAppointment} from "../../models/data-created-apponitment.model";

@Component({
  selector: 'app-dialog-created-appointment',
  templateUrl: './dialog-created-appointment.component.html',
  styleUrls: ['./dialog-created-appointment.component.scss']
})
export class DialogCreatedAppointmentComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DialogCreatedAppointmentComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogCreatedAppointment,
    private toastService: ToastMessageService,
  ) { }

  ngOnInit(): void {
  }

  copied() {
    this.toastService.info("Copied to clipboard")
  }
}
