package com.example.ua.secorgapp.person.doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDto {
    private Long id;
    private String firstname;
    private String lastname;
}
