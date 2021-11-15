package com.spring.boot.emc.exception;

public class PatientNotFoundException extends APIException {
    public static final int CODE = 111;

    public PatientNotFoundException(int patientId) {
        super(CODE, "Not found patient with id=" + patientId);
    }
}
