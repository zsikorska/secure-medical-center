import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {

  topic = '';
  message = '';
  messageIsSent!: boolean;

  constructor() { }

  ngOnInit(): void {
    this.messageIsSent = false;
  }

  sendMessage(): void{
    if (this.topic != '' && this.message != '')
      this.messageIsSent = true;
  }

}
