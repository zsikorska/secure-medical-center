package com.example.ua.secorgapp.person.patient.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDto {
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String birthdate;
}
