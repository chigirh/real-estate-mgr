package com.chigirh.eh.rem.domain.error;

import java.util.List;
import lombok.Getter;

@Getter
public class ValidationError extends SystemError {

    private final List<String> messages;

    public ValidationError(List<String> messages) {
        super("");
        this.messages = messages;
    }
}
