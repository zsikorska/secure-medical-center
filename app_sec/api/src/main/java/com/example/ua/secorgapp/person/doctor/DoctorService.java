package com.example.ua.secorgapp.person.doctor;

import com.example.ua.secorgapp.logger.Log4j;
import com.example.ua.secorgapp.logger.messages.LoggerMessages;
import com.example.ua.secorgapp.person.doctor.dto.DoctorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorService {
    @Autowired
    @Qualifier("doctorServiceLogger")
    private Log4j logger;
    private final DoctorRepository doctorRepository;

    public ResponseEntity<?> findDoctor(Long id) {
        logger.info(LoggerMessages.onFindDoctorInfo(id));
        var doctor = doctorRepository.findById(id).get();
        var doctorDto = DoctorDto.builder()
                .id(doctor.getId())
                .firstname(doctor.getFirstname())
                .lastname(doctor.getLastname())
                .build();
        return new ResponseEntity<>(doctorDto, HttpStatus.OK);
    }

    public ResponseEntity<?> findAllDoctors() throws SQLException {
        return new ResponseEntity(doctorRepository.findAll() , HttpStatus.OK);
    }
}
