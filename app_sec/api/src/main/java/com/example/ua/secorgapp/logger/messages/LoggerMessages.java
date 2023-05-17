package com.example.ua.secorgapp.logger.messages;

import com.example.ua.secorgapp.appointments.dto.AppointmentPostDto;
import com.example.ua.secorgapp.person.patient.Patient;
import com.example.ua.secorgapp.person.user.User;

public class LoggerMessages {
    public static final String patientNotFound(String id){
        return "Patient with ID: " + id + " could not be found.";
    }

    public static final String doctorNotFound(String id){
        return "Doctor with ID: " + id + " could not be found.";
    }
    public static final String onAddAppointmentInfo(AppointmentPostDto appointmentPostDto){
        return String.format("New appointment request - doctorId: %d, patientId: %d, topic: %s, date: %s.",
                appointmentPostDto.getDoctorId(),
                appointmentPostDto.getPatientId(),
                appointmentPostDto.getTopic(),
                appointmentPostDto.getDate());
    }
    public static final String onFindDiagnosisInfo(String id){
        return String.format("Find diagnosis request - diagnosisId: %s", id);
    }
    public static final String onAuthPatientInfo(User user){
        return String.format("Auth patient request - username: %s", user.getUsername());
    }
    public static final String onAddPatientInfo(Patient patient) {
        return String.format("Add patient request - patientId: %s, email: %s, firstname: %s, lastname: %s, birthdate: %s, username: %s",
                patient.getId(),
                patient.getEmail(),
                patient.getFirstname(),
                patient.getLastname(),
                patient.getBirthdate(),
                patient.getUsername()
        );
    }
    public static final String onFindDoctorInfo(Long id){
        return String.format("Find doctor request - doctorId: %d.", id);
    }
}
