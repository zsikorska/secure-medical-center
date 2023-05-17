package com.example.ua.secorgapp.person.patient;

import com.example.ua.secorgapp.logger.Log4j;
import com.example.ua.secorgapp.logger.messages.LoggerMessages;
import com.example.ua.secorgapp.mysql.MySqlDatabase;
import com.example.ua.secorgapp.patient_simulator.PatientSimulator;
import com.example.ua.secorgapp.person.patient.dto.PatientDto;
import com.example.ua.secorgapp.person.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("patientServiceLogger")
    private Log4j logger;

    public ResponseEntity<?> authenticatePatient(User user) {
        logger.info(LoggerMessages.onAuthPatientInfo(user));
        var patient = patientRepository.findByUsername(user.getUsername());
        if( patient == null || !passwordEncoder.matches(user.getPassword(), patient.getPassword()) ){
            return new ResponseEntity<>("Unauthorized.", HttpStatus.UNAUTHORIZED);
        }
        var patientDto = PatientDto.builder()
                .id(patient.getId())
                .firstname(patient.getFirstname())
                .lastname(patient.getLastname())
                .birthdate(patient.getBirthdate())
                .email(patient.getEmail())
                .username(patient.getUsername())
                .build();
        return new ResponseEntity<>(patientDto ,HttpStatus.OK);
    }

    public ResponseEntity<?> addPatient(Patient patient) {
        logger.info(LoggerMessages.onAddPatientInfo(patient));
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patientRepository.save(patient);
        return new ResponseEntity<>(true ,HttpStatus.OK);
    }

}
