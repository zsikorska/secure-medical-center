package com.example.ua.secorgapp.diagnosis;

import com.example.ua.secorgapp.appointments.Appointment;
import com.example.ua.secorgapp.mysql.MySqlDatabase;
import jdk.jshell.Diag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
@Transactional
public class DiagnosisRepository {
    private final MySqlDatabase mySqlDatabase;

    public void saveDiagnosis(Diagnosis diagnosis) throws Exception {
        String sql = "INSERT INTO diagnosis (id, appointment_id, disease, description)\n" +
                "VALUES ('"+diagnosis.getId()+"','"+diagnosis.getAppointment().getId()+"','"
                +diagnosis.getDisease()+"','"+diagnosis.getDescription()+"');";
        try {
            mySqlDatabase.executeUpdate(sql);
        }catch(Exception ex){
            throw new Exception("Error during inserting Diagnosis, query: "+ sql);
        }
    }

}
