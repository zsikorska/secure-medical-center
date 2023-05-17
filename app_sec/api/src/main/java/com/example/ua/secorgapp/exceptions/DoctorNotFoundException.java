package com.example.ua.secorgapp.exceptions;

import com.example.ua.secorgapp.exceptions.error_messages.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason= ErrorMessages.DOCTOR_NOT_FOUND)
public class DoctorNotFoundException extends  RuntimeException{
    public DoctorNotFoundException(String msg){
        super(msg);
    }
}
