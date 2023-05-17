package com.example.ua.secorgapp.person.doctor;

import com.example.ua.secorgapp.person.Person;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "doctor")
@NoArgsConstructor
public class Doctor extends Person {
    public Doctor(Long id, String firstname, String lastname) {
        super(id, firstname, lastname);
    }

}
