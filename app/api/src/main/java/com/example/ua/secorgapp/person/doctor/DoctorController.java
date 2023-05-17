package com.example.ua.secorgapp.person.doctor;

import com.example.ua.secorgapp.controller.config.ApiController;
import com.example.ua.secorgapp.logger.Log4j;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
public class DoctorController implements ApiController {
    private static final Logger logger = LogManager.getLogger(Log4j.class);
    private final DoctorService doctorService;

    @GetMapping("/v1/doctor/{id}")
    public ResponseEntity<?> findDoctor(@PathVariable Long id) throws SQLException {
        return doctorService.findDoctor(id);
    }

    @GetMapping("/v1/doctor/all")
    public ResponseEntity<?> getAllDoctors() throws SQLException {
        return doctorService.findAllDoctors();
    }

    @GetMapping("/v1/vuln")
    public ResponseEntity<?> testLog4j(@RequestParam("input") String input) {
        logger.log(Level.INFO, "input {}", input);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
