import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogCreatedAppointmentComponent } from './dialog-created-appointment.component';

describe('DialogCreatedAppointmentComponent', () => {
  let component: DialogCreatedAppointmentComponent;
  let fixture: ComponentFixture<DialogCreatedAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogCreatedAppointmentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DialogCreatedAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
