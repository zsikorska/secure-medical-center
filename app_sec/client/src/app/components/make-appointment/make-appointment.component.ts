import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { DoctorModel } from "../../models/doctor.model";
import { DoctorService } from "../../services/doctor/doctor.service";
import { AppointmentService } from "../../services/appointment/appointment.service";
import { AccountService } from "../../services/account/account.service";
import { DatePipe } from "@angular/common";
import {Router} from "@angular/router";
import {AppointmentResponseModel} from "../../models/appointmentResponse.model";
import {MatDialog} from "@angular/material/dialog";
import {DialogCreatedAppointmentComponent} from "../dialog-created-appointment/dialog-created-appointment.component";

@Component({
  selector: 'app-make-appointment',
  templateUrl: './make-appointment.component.html',
  styleUrls: ['./make-appointment.component.scss'],
  providers: [DatePipe]
})
export class MakeAppointmentComponent implements OnInit {

  appointmentForm!: FormGroup;
  doctors!: DoctorModel[];
  patientId!: number;
  appointmentResponse!: AppointmentResponseModel;

  constructor(
    private formBuilder: FormBuilder,
    private doctorService: DoctorService,
    private appointmentService: AppointmentService,
    private accountService: AccountService,
    private router: Router,
    private datePipe: DatePipe,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.buildForm();
    this.loadData();
  }

  private buildForm(): void {
    this.appointmentForm = this.formBuilder.group({
      date: ['', Validators.required],
      topic: ['', Validators.required],
      doctorId: ['', Validators.required],
      patientId: ['', Validators.required],
    });
  }

  private loadData(): void {
    this.doctorService.getAll().subscribe(doctors => {
      this.doctors = doctors;
    });

    this.accountService.currentUser$.subscribe(patient => {
      if (patient.id != null) {
        this.patientId = patient.id;
      }
    });
  }

  makeAppointment(): void {
    this.appointmentForm.patchValue({
      date: this.datePipe.transform(this.appointmentForm.value['date'], 'yyyy-MM-dd'),
      patientId: this.patientId
    });

    console.log(this.appointmentForm.value)

    this.appointmentService.post(this.appointmentForm.value).subscribe( appointmentResponse => {
        console.log(appointmentResponse);
        this.appointmentResponse = appointmentResponse;

        this.openDialog(appointmentResponse.diagnosisId);

        setTimeout(()=>{this.router.navigateByUrl('examination-result') }, 8000);
      }
    );

  }

  openDialog(code: string): void {
    this.dialog.open(DialogCreatedAppointmentComponent, {data: {code}});
  }

}
