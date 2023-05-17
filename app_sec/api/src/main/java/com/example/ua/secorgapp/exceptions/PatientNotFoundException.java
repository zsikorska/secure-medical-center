package com.example.ua.secorgapp.exceptions;

import com.example.ua.secorgapp.exceptions.error_messages.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason=ErrorMessages.PATIENT_NOT_FOUND)
public class PatientNotFoundException extends  RuntimeException{
    public PatientNotFoundException(String msg){
        super(msg);
    }
}
