package com.chigirh.eh.rem.domain.error;

public class DataAccessError extends SystemError {
    public DataAccessError(String message, Throwable e) {
        super(message, e);
    }
}
