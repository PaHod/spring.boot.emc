package com.spring.boot.emc.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code = HttpStatus.NOT_FOUND, reason =  "Requested doctor not found.")
public class DoctorNotFoundException extends APIException {

    public static final int CODE = 222;

    public DoctorNotFoundException(int doctorId) {
        super(CODE, "Not found doctor with id=" + doctorId);
    }
}
