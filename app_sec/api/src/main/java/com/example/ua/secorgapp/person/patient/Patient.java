package com.example.ua.secorgapp.person.patient;

import com.example.ua.secorgapp.person.Person;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "patient")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient extends Person {
    public Patient(Long id, String firstname, String lastname){
        super(id, firstname, lastname);
    }
    private String email;
    private String birthdate;
    private String username;
    private String password;
}
