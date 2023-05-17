package com.example.ua.secorgapp.appointments.dto;

import com.example.ua.secorgapp.person.doctor.dto.DoctorDto;
import com.example.ua.secorgapp.person.patient.dto.PatientDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto {
    private DoctorDto doctor;
    private PatientDto patient;
    private String date;
    private String topic;
}
