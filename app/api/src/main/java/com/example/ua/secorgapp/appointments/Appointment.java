package com.example.ua.secorgapp.appointments;

import com.example.ua.secorgapp.diagnosis.Diagnosis;
import com.example.ua.secorgapp.person.doctor.Doctor;
import com.example.ua.secorgapp.person.patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "appointment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Patient patient;
    private String topic;
    @OneToOne(mappedBy = "appointment")
    private Diagnosis diagnosis;
}
