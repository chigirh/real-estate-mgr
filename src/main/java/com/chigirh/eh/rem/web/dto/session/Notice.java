package com.chigirh.eh.rem.web.dto.session;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Notice {
    private final List<String> info = new ArrayList<>();
    private final List<String> success = new ArrayList<>();
    private final List<String> warn = new ArrayList<>();
    private final List<String> error = new ArrayList<>();

    public void info(String message) {
        info.add(message);
    }

    public void success(String message) {
        success.add(message);
    }

    public void warn(String message) {
        warn.add(message);
    }

    public void error(String message) {
        error.add(message);
    }

    public boolean isInfoNotEmpty() {
        return !info.isEmpty();
    }

    public List<String> getInfo() {
        var messages = new ArrayList<>(info);
        return messages;
    }

    public boolean isSuccessNotEmpty() {
        return !success.isEmpty();
    }

    public List<String> getSuccess() {
        var messages = new ArrayList<>(success);
        return messages;
    }

    public boolean isWarnNotEmpty() {
        return !warn.isEmpty();
    }

    public List<String> getWarn() {
        var messages = new ArrayList<>(warn);
        return messages;
    }

    public boolean isErrorNotEmpty() {
        return !error.isEmpty();
    }

    public List<String> getError() {
        var messages = new ArrayList<>(error);
        return messages;
    }

    public boolean getClear() {
        info.clear();
        success.clear();
        warn.clear();
        error.clear();
        return false;
    }
}
