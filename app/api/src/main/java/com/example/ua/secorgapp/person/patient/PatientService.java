package com.example.ua.secorgapp.person.patient;

import com.example.ua.secorgapp.logger.Log4j;
import com.example.ua.secorgapp.logger.messages.LoggerMessages;
import com.example.ua.secorgapp.mysql.MySqlDatabase;
import com.example.ua.secorgapp.person.patient.dto.PatientDto;
import com.example.ua.secorgapp.person.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.ResultSet;


@Service
@Transactional
@RequiredArgsConstructor
public class PatientService {
    private final MySqlDatabase mySqlDatabase;
    private final PatientRepository patientRepository;
    @Autowired
    @Qualifier("patientServiceLogger")
    private Log4j logger;

    public ResponseEntity<?> authenticatePatient(User user) {
        logger.info(LoggerMessages.onAuthPatientInfo(user));
        String sql = "SELECT * FROM patient WHERE USERNAME=" +'"'+user.getUsername()+'"'+" AND PASSWORD =" + '"' + user.getPassword() +'"' ;
        ResultSet resultSet;
        String patientPassword = null;
        PatientDto patientDto = null;
        try {
            resultSet = mySqlDatabase.executeRead(sql);
            while (resultSet.next()) {
                patientPassword = resultSet.getString("password");

                if(patientPassword == null)//| !user.getPassword().equals(patientPassword))
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);

                patientDto = PatientDto.builder()
                        .id(resultSet.getLong("id"))
                        .firstname(resultSet.getString("firstname"))
                        .lastname(resultSet.getString("lastname"))
                        .birthdate(resultSet.getString("birthdate"))
                        .email(resultSet.getString("email"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .build();
            }
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(patientDto ,HttpStatus.OK);
    }


    public ResponseEntity<?> addPatient(Patient patient) {
        logger.info(LoggerMessages.onAddPatientInfo(patient));
        String sql = "INSERT INTO patient (firstname, lastname, email, birthdate, username, password)\n" +
                "VALUES ("+ '"'+patient.getFirstname()+'"' +","+ '"'+patient.getLastname()+'"'+","+'"'+patient.getEmail()
                +'"'+","+'"'+patient.getBirthdate()+'"'+","+'"'+patient.getUsername()+'"'+','+'"'
                +patient.getPassword()+'"'+");";
        try {
            if(!patientRepository.existPatient(patient.getUsername()))
                mySqlDatabase.executeUpdate(sql);
            else
                return new ResponseEntity<>(false ,HttpStatus.OK);

        }catch(Exception ex){
            return new ResponseEntity<>(false ,HttpStatus.OK);
        }

        return new ResponseEntity<>(true ,HttpStatus.OK);
    }

}
