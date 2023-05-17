package com.example.ua.secorgapp.diagnosis.dto;

import com.example.ua.secorgapp.appointments.dto.AppointmentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiagnosisDto {
    private AppointmentDto appointment;
    private String disease;
    private String description;
}
