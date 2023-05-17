package com.example.ua.secorgapp.diagnosis;

import com.example.ua.secorgapp.appointments.dto.AppointmentDto;
import com.example.ua.secorgapp.diagnosis.dto.DiagnosisDto;
import com.example.ua.secorgapp.logger.Log4j;
import com.example.ua.secorgapp.logger.messages.LoggerMessages;
import com.example.ua.secorgapp.mysql.MySqlDatabase;
import com.example.ua.secorgapp.person.doctor.dto.DoctorDto;
import com.example.ua.secorgapp.person.patient.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
@RequiredArgsConstructor
public class DiagnosisService {

    private final MySqlDatabase mySqlDatabase;
    @Autowired
    @Qualifier("diagnosisServiceLogger")
    private Log4j logger;

    public ResponseEntity<?> findDiagnosis(String id) {
        logger.info(LoggerMessages.onFindDiagnosisInfo(id));
        String sql = "SELECT diagnosis.id, diagnosis.disease, diagnosis.description, appointment.topic, " +
                "appointment.date, doctor.id AS doctor_id, doctor.firstname AS doctor_firstname, " +
                "doctor.lastname AS doctor_lastname, patient.firstname AS patient_firstname, " +
                "patient.lastname AS patient_lastname, patient.birthdate, patient.email FROM patient INNER JOIN " +
                "(doctor INNER JOIN (appointment INNER JOIN diagnosis ON appointment.id = diagnosis.appointment_id) " +
                "ON doctor.id = appointment.doctor_id) ON patient.id = appointment.patient_id " +
                "WHERE diagnosis.id="+'"'+id+'"';

        ResultSet resultSet = null;
        DiagnosisDto diagnosisDto = null;
        AppointmentDto appointmentDto = null;
        DoctorDto doctorDto = null;
        PatientDto patientDto = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            resultSet = mySqlDatabase.executeRead(sql);
            while (resultSet.next()) {
                patientDto = PatientDto.builder()
                        .firstname(resultSet.getString("patient_firstname"))
                        .lastname(resultSet.getString("patient_lastname"))
                        .birthdate(resultSet.getString("birthdate"))
                        .email(resultSet.getString("email"))
                        .build();

                doctorDto = DoctorDto.builder()
                        .id(resultSet.getLong("doctor_id"))
                        .firstname(resultSet.getString("doctor_firstname"))
                        .lastname(resultSet.getString("doctor_lastname"))
                        .build();

                appointmentDto = AppointmentDto.builder()
                        .date(resultSet.getString("date"))
                        .topic(resultSet.getString("topic"))
                        .patient(patientDto)
                        .doctor(doctorDto)
                        .build();

                diagnosisDto = DiagnosisDto.builder()
                        .appointment(appointmentDto)
                        .disease(resultSet.getString("disease"))
                        .description(resultSet.getString("description"))
                        .build();
            }
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(diagnosisDto, HttpStatus.OK);
    }
}
