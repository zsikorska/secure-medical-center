package com.example.ua.secorgapp.mysql;

import com.example.ua.secorgapp.appointments.AppointmentRepository;
import com.example.ua.secorgapp.appointments.dto.AppointmentPostDto;
import com.example.ua.secorgapp.person.patient.PatientService;
import com.example.ua.secorgapp.person.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestSqlInjectionAsRealUser {
    @Autowired
    private PatientService patientService;
    @Autowired
    private MySqlDatabase mySqlDatabase;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    public void shouldInjectAdditionalDropTable() throws Exception {
        String vulnInput = "topic'); DROP TABLE doctor, patient, appointment, diagnosis; -- /";
        var appointmentPostDto = new AppointmentPostDto(
                null,
                1L,
                1L,
                "1999-11-11",
                vulnInput
        );
        appointmentRepository.saveAppointment(appointmentPostDto);

        List<String> tablesThatShouldbeDeleted = new ArrayList<>(Arrays.asList("doctor", "patient", "appointment", "diagnosis"));
        for (String table : tablesThatShouldbeDeleted){
            assert !mySqlDatabase.isTableExists(table);
        }
    }

    @Test
    public void shouldInjectLogin() throws Exception {
        String vulnInput = "\" OR TRUE ; -- ";
        String password = "";
        User user = new User(vulnInput,password);
        var response = patientService.authenticatePatient(user);
        var object = response.toString();

        assert response.getStatusCode().value() == 200;
    }
}
