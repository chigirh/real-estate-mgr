package com.chigirh.eh.rem.domain.common;

import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Role {

    ADMIN("ADMIN", "ROLE_ADMIN"),
    USER("USER", "ROLE_USER");

    private final String name;
    private final String value;

    public static Role of(String name) {
        var opt = Stream.of(values())
            .filter(e -> e.name.equals(name))
            .findFirst();

        if (opt.isPresent()) {
            return opt.get();
        }

        throw new IllegalArgumentException("ForeignerLiveSts:" + name);
    }
}
