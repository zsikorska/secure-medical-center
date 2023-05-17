import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {AppointmentPostModel} from "../../models/appointmentPost.model";

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  readonly urlAppointment = `${environment.apiUrl}/appointment`;

  constructor(
    private http: HttpClient
  ) {
  }

  post(appointment: AppointmentPostModel): Observable<any> {
    return this.http.post(this.urlAppointment, appointment);
  }


}
