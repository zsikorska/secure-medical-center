package com.example.ua.secorgapp.appointments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentPostDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String diagnosisId;
    private Long doctorId;
    private Long patientId;
    private String date;
    private String topic;
}
