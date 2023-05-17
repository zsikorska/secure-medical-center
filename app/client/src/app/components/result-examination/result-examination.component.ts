import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {DiagnosisService} from "../../services/diagnosis/diagnosis.service";
import {DiagnosisModel} from "../../models/diagnosis.model";

@Component({
  selector: 'app-result-examination',
  templateUrl: './result-examination.component.html',
  styleUrls: ['./result-examination.component.scss']
})
export class ResultExaminationComponent implements OnInit {

  diagnosisForm!: FormControl;
  diagnosis!: DiagnosisModel;
  rightCode!: Boolean;

  constructor(
    private formBuilder: FormBuilder,
    private diagnosisService: DiagnosisService
  ) {
  }

  ngOnInit(): void {
    this.buildForm();
    this.rightCode = true;
  }

  private buildForm(): void {
    this.diagnosisForm = this.formBuilder.control<string>('', Validators.required);
  }

  getResultsOfExamination(): void {
    this.diagnosisService.get(this.diagnosisForm.value).subscribe(diagnosis => {
      this.diagnosis = diagnosis;
      this.rightCode = this.diagnosis != null;
      this.diagnosisForm.reset();
    });
  }

}
