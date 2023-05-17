package com.example.ua.secorgapp.person.doctor;

import com.example.ua.secorgapp.person.Person;
import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Super;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Entity
@Table(name = "doctor")
@NoArgsConstructor
public class Doctor extends Person {
    public Doctor(Long id, String firstname, String lastname) {
        super(id, firstname, lastname);
    }

}
