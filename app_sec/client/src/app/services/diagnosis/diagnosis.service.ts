import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DiagnosisModel} from "../../models/diagnosis.model";

@Injectable({
  providedIn: 'root'
})
export class DiagnosisService {

  readonly urlDiagnosis = `${environment.apiUrl}/diagnosis`;

  constructor(
    private http: HttpClient
  ) { }

  get(id: string): Observable<DiagnosisModel> {
    return this.http.get<DiagnosisModel>(this.urlDiagnosis + '/' + id);
  }
}
