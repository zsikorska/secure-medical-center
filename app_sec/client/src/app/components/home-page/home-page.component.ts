import { Component, OnInit } from '@angular/core';
import {DoctorModel} from "../../models/doctor.model";
import {DoctorService} from "../../services/doctor/doctor.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  doctors!: DoctorModel[];

  constructor(
    private doctorService: DoctorService
  ) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.doctorService.getAll().subscribe(doctors => {
      this.doctors = doctors;
    });
  }

}
