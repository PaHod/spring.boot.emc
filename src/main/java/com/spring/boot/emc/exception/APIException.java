package com.spring.boot.emc.exception;

public class APIException extends RuntimeException {
    private final int internalCode;

    public APIException(int internalCode, String message) {
        super(message);
        this.internalCode = internalCode;
    }

    public int internalCode() {
        return internalCode;
    }
}