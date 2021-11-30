package com.spring.boot.emc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code = HttpStatus.NOT_FOUND, reason =  "Requested patient not found.")
public class PatientNotFoundException extends APIException {
    public static final int CODE = 111;

    public PatientNotFoundException(int patientId) {
        super(CODE, "Not found patient with id=" + patientId);
    }
}
