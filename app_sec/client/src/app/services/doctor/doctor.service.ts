import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DoctorModel} from "../../models/doctor.model";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  readonly urlDoctorList = `${environment.apiUrl}/doctor/all`;

  constructor(
    private http: HttpClient
  ) {
  }

  getAll(): Observable<DoctorModel[]> {
    return this.http.get<DoctorModel[]>(this.urlDoctorList);
  }

}
