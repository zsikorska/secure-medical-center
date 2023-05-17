package com.example.ua.secorgapp.password_encoder;

import com.example.ua.secorgapp.patient_simulator.PatientSimulator;
import com.example.ua.secorgapp.person.patient.Patient;
import com.example.ua.secorgapp.person.patient.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestPasswordEncoder {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientSimulator patientSimulator;
    List<Patient> potatos = new ArrayList<>();

    @Test
    public void decodedPasswordsShouldMatchTestData() {
        for(int i = 0; i < potatos.size() ; i++){
            assert passwordEncoder.matches(patientSimulator.getPlainPasswords().get(i), potatos.get(i).getPassword());
        }
    }

    @BeforeEach
    public void encodeTestPatientsPasswords(){
        potatos = patientRepository.findAll();
        potatos.stream().forEach(patient -> {
            System.out.printf("user: %s, hashed_password: %s",
                    patient.getUsername(), patient.getPassword());
        });
    }
}
