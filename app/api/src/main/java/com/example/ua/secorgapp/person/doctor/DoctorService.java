package com.example.ua.secorgapp.person.doctor;

import com.example.ua.secorgapp.logger.Log4j;
import com.example.ua.secorgapp.logger.messages.LoggerMessages;
import com.example.ua.secorgapp.mysql.MySqlDatabase;
import com.example.ua.secorgapp.person.doctor.dto.DoctorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorService {
    private final MySqlDatabase mySqlDatabase;
    @Autowired
    @Qualifier("doctorServiceLogger")
    private Log4j logger;
    private final DoctorRepository doctorRepository;

    public ResponseEntity<?> findDoctor(Long id) {
        logger.info(LoggerMessages.onFindDoctorInfo(id));
        String sql = "SELECT * FROM doctor WHERE ID="+id;
        ResultSet resultSet = null;
        DoctorDto doctorDto = null;
        try {
            resultSet = mySqlDatabase.executeRead(sql);
            while (resultSet.next()) {
                doctorDto = DoctorDto.builder()
                        .id(resultSet.getLong("id"))
                        .firstname(resultSet.getString("firstname"))
                        .lastname(resultSet.getString("lastname"))
                        .build();
            }
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(doctorDto, HttpStatus.OK);
    }

    public ResponseEntity<?> findAllDoctors() throws SQLException {
        return new ResponseEntity(doctorRepository.findAllDoctors() , HttpStatus.OK);
    }
}
