package com.example.ua.secorgapp.person.patient;

import com.example.ua.secorgapp.mysql.MySqlDatabase;
import com.example.ua.secorgapp.person.doctor.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.ResultSet;

@RequiredArgsConstructor
@Repository
@Transactional
public class PatientRepository {
    private final MySqlDatabase mySqlDatabase;

    public boolean existPatient(String username) {
        String sql = "SELECT * FROM patient WHERE USERNAME=" + '"'+username+'"'+";";
        ResultSet resultSet;
        String usernameResult = null;
        try {
            resultSet = mySqlDatabase.executeRead(sql);
            while (resultSet.next()) {
                usernameResult = resultSet.getString("username");
            }
        }catch(Exception ex){
            return false;
        }
        return usernameResult != null;
    }

    public boolean existPatient(Long id) {
        String sql = "SELECT * FROM patient WHERE ID="+id+";";
        ResultSet resultSet;
        Long idResult = null;
        try {
            resultSet = mySqlDatabase.executeRead(sql);
            while (resultSet.next()) {
                idResult = resultSet.getLong("id");
            }
        }catch(Exception ex){
            return false;
        }
        return idResult != null;
    }

    public Patient findPatientById(Long patientId) throws Exception {
        String sql = "SELECT * FROM patient WHERE id="+patientId+";";
        try {
            var resultSet = mySqlDatabase.executeRead(sql);
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var firstname = resultSet.getString("firstname");
                var lastname = resultSet.getString("lastname");
                var email = resultSet.getString("email");
                var birthdate = resultSet.getString("birthdate");
                var username = resultSet.getString("username");
                var password = resultSet.getString("password");
                Patient patient = new Patient(id, firstname, lastname);
                patient.setEmail(email);
                patient.setBirthdate(birthdate);
                patient.setUsername(username);
                patient.setPassword(password);
                return patient;
            }
        }catch(Exception ex){
            throw new Exception("Error during checking if doctor exists, query: "+ sql);
        }
        return null;
    }
}
