import {AppointmentModel} from "./appointment.model";

export interface DiagnosisModel {
  id?: String;
  appointment: AppointmentModel
  disease: string;
  description: string;
}
