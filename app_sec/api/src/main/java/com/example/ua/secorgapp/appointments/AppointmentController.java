package com.example.ua.secorgapp.appointments;

import com.example.ua.secorgapp.appointments.dto.AppointmentPostDto;
import com.example.ua.secorgapp.controller.config.ApiController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AppointmentController implements ApiController  {

    private final AppointmentService appointmentService;
    @PostMapping("/v1/appointment")
    public ResponseEntity<?> makeAppointment(@RequestBody AppointmentPostDto appointmentPostDto) throws Exception {
        return appointmentService.addAppointment(appointmentPostDto);
    }
}
