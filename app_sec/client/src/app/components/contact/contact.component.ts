import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {

  contactForm!: FormGroup;
  messageIsSent!: boolean;

  constructor(
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit(): void {
    this.buildForm();
    this.messageIsSent = false;
  }

  private buildForm(): void {
    this.contactForm = this.formBuilder.group({
      topic: ['', Validators.required],
      message: ['', Validators.required],
    });
  }

  sendMessage(): void{
    if (this.contactForm.valid)
      this.messageIsSent = true;
  }
}
