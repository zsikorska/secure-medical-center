package com.example.ua.secorgapp.repository;

import com.example.ua.secorgapp.appointments.AppointmentRepository;
import com.example.ua.secorgapp.appointments.dto.AppointmentPostDto;
import com.example.ua.secorgapp.person.doctor.DoctorRepository;
import com.example.ua.secorgapp.person.patient.PatientService;
import com.example.ua.secorgapp.person.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestDoctorRepository {
    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void shouldReturnAllDoctors() throws Exception {
        var doctorDtos = doctorRepository.findAllDoctors();
        assert doctorDtos != null;
    }

}
