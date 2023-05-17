package com.example.ua.secorgapp.appointments;

import com.example.ua.secorgapp.appointments.dto.AppointmentPostDto;
import com.example.ua.secorgapp.diagnosis.DiagnosisRepository;
import com.example.ua.secorgapp.doctor_simulator.DoctorSimulator;
import com.example.ua.secorgapp.exceptions.DoctorNotFoundException;
import com.example.ua.secorgapp.exceptions.PatientNotFoundException;
import com.example.ua.secorgapp.logger.Log4j;
import com.example.ua.secorgapp.logger.messages.LoggerMessages;
import com.example.ua.secorgapp.person.doctor.DoctorRepository;
import com.example.ua.secorgapp.person.patient.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentService {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final DiagnosisRepository diagnosisRepository;
    @Autowired
    @Qualifier("appointmentServiceLogger")
    private Log4j logger;

    public ResponseEntity<?> addAppointment(AppointmentPostDto appointmentPostDto) throws Exception {
        // log
        logger.info(LoggerMessages.onAddAppointmentInfo(appointmentPostDto));
        // validate
        var patientId = appointmentPostDto.getPatientId();
        var doctorId = appointmentPostDto.getDoctorId();
        if(!patientRepository.existPatient(patientId)){
            throw new PatientNotFoundException("Can not find patientId: " + patientId);
        }
        if(!doctorRepository.existDoctor(doctorId)){
            throw new DoctorNotFoundException("Can not find doctorId: " + doctorId);
        }
        appointmentRepository.saveAppointment(appointmentPostDto);
        // get objects from database
        var appointment = appointmentRepository.findAppointment(
                appointmentRepository.findAppointmentId(appointmentPostDto),
                appointmentPostDto.getDoctorId(),
                appointmentPostDto.getPatientId()
        );
        var diagnosis = DoctorSimulator.simulateDiagnosis(appointment);
        diagnosis.setAppointment(appointment);
        // save
        diagnosisRepository.saveDiagnosis(diagnosis);
        // response
        appointmentPostDto.setDiagnosisId(diagnosis.getId());
        return new ResponseEntity<>(appointmentPostDto ,HttpStatus.OK);
    }

}
