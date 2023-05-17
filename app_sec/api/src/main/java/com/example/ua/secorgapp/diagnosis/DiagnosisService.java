package com.example.ua.secorgapp.diagnosis;

import com.example.ua.secorgapp.appointments.dto.AppointmentDto;
import com.example.ua.secorgapp.diagnosis.dto.DiagnosisDto;
import com.example.ua.secorgapp.logger.Log4j;
import com.example.ua.secorgapp.logger.messages.LoggerMessages;
import com.example.ua.secorgapp.person.doctor.dto.DoctorDto;
import com.example.ua.secorgapp.person.patient.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;
    @Autowired
    @Qualifier("diagnosisServiceLogger")
    private Log4j logger;

    public ResponseEntity<?> findDiagnosis(String id) {
        logger.info(LoggerMessages.onFindDiagnosisInfo(id));
        var diagnosis = diagnosisRepository.findById(id).get();
        if(diagnosis == null ||
                diagnosis.getAppointment() == null ||
                diagnosis.getAppointment().getDoctor() == null ||
                diagnosis.getAppointment().getPatient() == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        var appointment = diagnosis.getAppointment();
        var doctor = appointment.getDoctor();
        var patient = appointment.getPatient();
        DoctorDto doctorDto = DoctorDto.builder()
                .id(doctor.getId())
                .firstname(doctor.getFirstname())
                .lastname(doctor.getLastname())
                .build();
        PatientDto patientDto = PatientDto.builder()
                .firstname(patient.getFirstname())
                .lastname(patient.getLastname())
                .birthdate(patient.getBirthdate())
                .email(patient.getEmail())
                .build();
        AppointmentDto appointmentDto = AppointmentDto.builder()
                .date(appointment.getDate())
                .topic(appointment.getTopic())
                .patient(patientDto)
                .doctor(doctorDto)
                .build();
        DiagnosisDto diagnosisDto = DiagnosisDto.builder()
                .appointment(appointmentDto)
                .disease(diagnosis.getDisease())
                .description(diagnosis.getDescription())
                .build();
        return new ResponseEntity<>(diagnosisDto, HttpStatus.OK);
    }
}
