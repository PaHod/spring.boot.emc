package com.spring.boot.emc.exception;

public class CannotRemoveException extends APIException {
    public static final int CODE = 555;

    public CannotRemoveException(int from, int toRemoveId) {
        super(CODE, String.format("Cannot remove %d from %d", toRemoveId, from));
    }
}
