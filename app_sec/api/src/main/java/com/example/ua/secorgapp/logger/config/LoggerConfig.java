package com.example.ua.secorgapp.logger.config;

import com.example.ua.secorgapp.appointments.AppointmentService;
import com.example.ua.secorgapp.diagnosis.DiagnosisService;
import com.example.ua.secorgapp.logger.Log4j;
import com.example.ua.secorgapp.logger.LoggerFactory;
import com.example.ua.secorgapp.person.doctor.DoctorService;
import com.example.ua.secorgapp.person.patient.PatientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {
    @Bean(name = "appointmentServiceLogger")
    public Log4j appointmentServiceLogger(){
        return LoggerFactory.createLogger(AppointmentService.class.getName());
    }

    @Bean(name = "doctorServiceLogger")
    public Log4j doctorServiceLogger(){
        return LoggerFactory.createLogger(DoctorService.class.getName());
    }

    @Bean(name = "patientServiceLogger")
    public Log4j patientServiceLogger(){
        return LoggerFactory.createLogger(PatientService.class.getName());
    }

    @Bean(name = "diagnosisServiceLogger")
    public Log4j diagnosisServiceLogger(){
        return LoggerFactory.createLogger(DiagnosisService.class.getName());
    }


}
