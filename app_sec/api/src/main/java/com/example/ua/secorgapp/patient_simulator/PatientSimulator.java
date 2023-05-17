package com.example.ua.secorgapp.patient_simulator;

import com.example.ua.secorgapp.person.patient.Patient;
import com.example.ua.secorgapp.person.patient.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PatientSimulator {
    private final PatientRepository patientRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private List<String> plainPasswords = new ArrayList<>();

    @EventListener(ApplicationReadyEvent.class)
    public void initSimulateSampleUsers() {
        var patients = patientRepository.findAll();
        for (Patient patient : patients) {
            plainPasswords.add(patient.getPassword());
            patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        }
        patientRepository.saveAll(patients);
    }

    public List<String> getPlainPasswords(){
        return plainPasswords;
    }

}
