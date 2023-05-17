package com.example.ua.secorgapp.doctor_simulator;

import com.example.ua.secorgapp.appointments.Appointment;
import com.example.ua.secorgapp.diagnosis.Diagnosis;

import java.util.Random;
import java.util.UUID;

public class DoctorSimulator {
    private final static String[] diagnosisDiseases = {
            "The flu",
            "Measles",
            "HIV",
            "COVID-19",
            "Salmonella",
            "Strep Throat",
            "Cancer",
            "Alzheimer"
    };
    private final static String[] diagnosisDescriptions = {
            "Please drink more water.",
            "Please keep warm for a few days.",
            "Please quit smoking.",
            "Please quit alcohol.",
            "Please do more sport activities.",
            "Please start swimming, it is good for your back."
    };

    private static Random random = new Random();

    private static String simulateDiagnosisDisease(){
        return diagnosisDiseases[random.nextInt(diagnosisDiseases.length)];
    }

    private static String simulateDiagnosisDescription(){
        return diagnosisDescriptions[random.nextInt(diagnosisDescriptions.length)];
    }

    public static Diagnosis simulateDiagnosis(Appointment appointment){
        return Diagnosis.builder()
                .id(UUID.randomUUID().toString())
                .appointment(appointment)
                .disease(simulateDiagnosisDisease())
                .description(simulateDiagnosisDescription())
                .build();
    }

}
