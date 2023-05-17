import { PatientModel } from "./patient.model";
import { DoctorModel } from "./doctor.model";

export interface AppointmentModel {
  id?: number;
  date: string;
  topic: string;
  doctor: DoctorModel;
  patient: PatientModel;
}
