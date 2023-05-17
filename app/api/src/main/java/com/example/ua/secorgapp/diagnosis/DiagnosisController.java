package com.example.ua.secorgapp.diagnosis;

import com.example.ua.secorgapp.controller.config.ApiController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class DiagnosisController implements ApiController {

    private final DiagnosisService diagnosisService;

    @GetMapping("/v1/diagnosis/{id}")
    public ResponseEntity<?> findDiagnosis(@PathVariable String id) {
        return diagnosisService.findDiagnosis(id);
    }
}
