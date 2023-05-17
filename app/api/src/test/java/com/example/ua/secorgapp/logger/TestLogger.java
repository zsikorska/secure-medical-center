package com.example.ua.secorgapp.logger;

import com.example.ua.secorgapp.logger.messages.LoggerMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestLogger {
    @Autowired
    @Qualifier("appointmentServiceLogger")
    private Log4j appointmentServiceLogger;
    @Autowired
    @Qualifier("diagnosisServiceLogger")
    private Log4j diagnosisServiceLogger;

    @Test
    public void shouldInit(){}

    @Test
    public void shouldLogAsAppointmentService(){
        appointmentServiceLogger.info(LoggerMessages.patientNotFound("2"));
        appointmentServiceLogger.warn(LoggerMessages.patientNotFound("3"));
        appointmentServiceLogger.error(LoggerMessages.patientNotFound("4"));
    }

    @Test
    public void shouldLogAsDiagnosisService(){
        diagnosisServiceLogger.info(LoggerMessages.patientNotFound("2"));
        diagnosisServiceLogger.warn(LoggerMessages.patientNotFound("3"));
        diagnosisServiceLogger.error(LoggerMessages.patientNotFound("4"));
    }
}
