package com.example.ua.secorgapp.person.doctor;

import com.example.ua.secorgapp.mysql.MySqlDatabase;
import com.example.ua.secorgapp.person.doctor.dto.DoctorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Transactional
public class DoctorRepository {
    private final MySqlDatabase mySqlDatabase;

    public boolean existDoctor(Long id) throws Exception {
        String sql = "SELECT * FROM doctor WHERE ID="+id+";";
        ResultSet resultSet;
        Long idResult = null;
        try {
            resultSet = mySqlDatabase.executeRead(sql);
            while (resultSet.next()) {
                idResult = resultSet.getLong("id");
            }
        }catch(Exception ex){
            throw new Exception("Error during checking if doctor exists, query: "+ sql);
        }
        return idResult != null;
    }

    public Doctor findDoctorById(Long doctorId) throws Exception {
        String sql = "SELECT * FROM doctor WHERE id="+doctorId+";";
        try {
            var resultSet = mySqlDatabase.executeRead(sql);
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var firstname = resultSet.getString("firstname");
                var lastname = resultSet.getString("lastname");
                return new Doctor(id, firstname, lastname);
            }
        }catch(Exception ex){
            throw new Exception("Error during checking if doctor exists, query: "+ sql);
        }
        return null;
    }

    public List<DoctorDto> findAllDoctors() throws SQLException {
        var sql = "SELECT * FROM doctor;";
        var resultSet = mySqlDatabase.executeRead(sql);
        List<DoctorDto> doctorDtos = new ArrayList<>();
        while(resultSet.next()){
            var doctorDto = DoctorDto.builder()
                    .id(resultSet.getLong("id"))
                    .firstname(resultSet.getString("firstname"))
                    .lastname(resultSet.getString("lastname"))
                    .build();
            doctorDtos.add(doctorDto);
        }
        return doctorDtos;
    }

}
