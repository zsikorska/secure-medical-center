import {Injectable} from '@angular/core';
import {environment} from "../../../environments/environment";
import {map, Observable, ReplaySubject} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {UserModel} from "../../models/user.model";
import {PatientModel} from "../../models/patient.model";

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  readonly urlLogin = `${environment.apiUrl}/account/login`;
  readonly urlRegister = `${environment.apiUrl}/account/register`;

  private currentUserSource = new ReplaySubject<PatientModel>(1);
  currentUser$ = this.currentUserSource.asObservable();

  constructor(private http: HttpClient) {
  }

  login(model: any) {
    return this.http.post<PatientModel>(this.urlLogin, model).pipe(
      map((response: PatientModel) => {
        const patient = response;
        if (patient) {
          this.setCurrentUser(patient);
        }
      })
    );
  }

  register(model: any): Observable<any> {
    return this.http.post<UserModel>(this.urlRegister, model);
  }

  setCurrentUser(patient: PatientModel) {
    localStorage.setItem('user', JSON.stringify(patient));
    this.currentUserSource.next(patient);
  }

  logout() {
    localStorage.removeItem('user');
    this.currentUserSource.next(null!);
  }
}
