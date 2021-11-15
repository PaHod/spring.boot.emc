package com.spring.boot.emc.exception;

public class DoctorNotFoundException extends APIException {

    public static final int CODE = 222;

    public DoctorNotFoundException(int doctorId) {
        super(CODE, "Not found doctor with id=" + doctorId);
    }
}
