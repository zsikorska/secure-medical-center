package com.example.ua.secorgapp.diagnosis;

import com.example.ua.secorgapp.appointments.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "diagnosis")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Diagnosis {
    @Id
    private String id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;
    private String disease;
    private String description;

}
