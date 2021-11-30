package com.spring.boot.emc.exception;

public class CannotRemoveEntityException extends APIException {
    public static final int CODE = 555;

    public CannotRemoveEntityException(int from, int toRemoveId) {
        super(CODE, String.format("Cannot remove %d from %d", toRemoveId, from));
    }
}
