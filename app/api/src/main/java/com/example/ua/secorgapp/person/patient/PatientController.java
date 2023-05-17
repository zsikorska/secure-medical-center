package com.example.ua.secorgapp.person.patient;

import com.example.ua.secorgapp.controller.config.ApiController;
import com.example.ua.secorgapp.person.user.User;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class PatientController implements ApiController {

    private final PatientService patientService;

    @PostMapping ("/v1/account/register")
    public ResponseEntity<?> register(@RequestBody Patient patient) {
        return  patientService.addPatient(patient);
    }

    @PostMapping ("/v1/account/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return  patientService.authenticatePatient(user);
    }
}
