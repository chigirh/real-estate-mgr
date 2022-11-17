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

    public String getInfo() {
        if (info.isEmpty()) {
            return null;
        }
        var message = String.join("\r\n", info);
        info.clear();
        return message;
    }

    public boolean isSuccessNotEmpty() {
        return !success.isEmpty();
    }

    public String getSuccess() {
        if (success.isEmpty()) {
            return null;
        }
        var message = String.join("\r\n", success);
        success.clear();
        return message;
    }

    public boolean isWarnNotEmpty() {
        return !warn.isEmpty();
    }

    public String getWarn() {
        if (warn.isEmpty()) {
            return null;
        }
        var message = String.join("\r\n", warn);
        warn.clear();
        return message;
    }

    public boolean isErrorNotEmpty() {
        return !error.isEmpty();
    }

    public String getError() {
        if (error.isEmpty()) {
            return null;
        }
        var message = String.join("\r\n", error);
        error.clear();
        return message;
    }

    public void clear() {
        info.clear();
        success.clear();
        warn.clear();
        error.clear();
    }
}
