package com.chigirh.eh.rem.domain.error;

public class SystemError extends RuntimeException {
    public SystemError(String message, Throwable e) {
        super(message, e);
    }

    public SystemError(String message) {
        super(message);
    }
}
