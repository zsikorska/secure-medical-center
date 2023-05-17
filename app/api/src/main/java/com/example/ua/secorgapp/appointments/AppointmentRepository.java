package com.example.ua.secorgapp.appointments;

import com.example.ua.secorgapp.appointments.dto.AppointmentPostDto;
import com.example.ua.secorgapp.mysql.MySqlDatabase;
import com.example.ua.secorgapp.person.doctor.Doctor;
import com.example.ua.secorgapp.person.doctor.DoctorRepository;
import com.example.ua.secorgapp.person.patient.Patient;
import com.example.ua.secorgapp.person.patient.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Repository
@Transactional
public class AppointmentRepository {
    private final MySqlDatabase mySqlDatabase;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public void saveAppointment(AppointmentPostDto appointmentPostDto) throws Exception {
        String sql = String.format("INSERT INTO appointment (doctor_id, patient_id, date, topic)\n" +
                        "VALUES (%d, %d, '%s', '%s');",
                appointmentPostDto.getDoctorId(),
                appointmentPostDto.getPatientId(),
                appointmentPostDto.getDate(),
                appointmentPostDto.getTopic()
        );
        try {
            mySqlDatabase.executeUpdate(sql);
        }catch(Exception ex){
            throw new Exception("Error during inserting Appointment, query: "+ sql);
        }
    }

    public Appointment findAppointment(Long appointmentId, Long doctorId, Long patientId) throws Exception{
        String sql = "SELECT * FROM appointment WHERE id="+appointmentId+";";
        try {
            var resultSet = mySqlDatabase.executeRead(sql);
            Doctor doctor = doctorRepository.findDoctorById(doctorId);
            Patient patient = patientRepository.findPatientById(patientId);
            String topic = "";
            String date = "";
            while(resultSet.next()){
                topic = resultSet.getString("topic");
                date = resultSet.getString("date");
                return Appointment.builder()
                        .id(appointmentId)
                        .doctor(doctor)
                        .patient(patient)
                        .topic(topic)
                        .date(date)
                        .build();
            }
        }catch(Exception ex){
            throw new Exception("Error during reading Appointment ID, query: "+ sql);
        }
        return null;
    }

    public Long findAppointmentId(AppointmentPostDto appointmentPostDto) throws Exception {
        String sql = String.format("SELECT * FROM appointment WHERE " +
                "doctor_id=%d and patient_id=%d and date='%s';",
                appointmentPostDto.getDoctorId(),
                appointmentPostDto.getPatientId(),
                appointmentPostDto.getDate()
        );
        try {
            var resultSet = mySqlDatabase.executeRead(sql);
            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                return id;
            }
        }catch(Exception ex){
            throw new Exception("Error during reading Appointment ID, query: "+ sql);
        }
        return -1L;
    }

}
