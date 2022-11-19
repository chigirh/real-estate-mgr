package com.chigirh.eh.rem.domain.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ForeignerLiveSts {

    UNCONFIRMED("1"),
    ARROWED("2"),
    NOT_ARROWED("3");

    private final String value;

    public static ForeignerLiveSts of(String value) {
        var opt = Stream.of(values())
            .filter(e -> e.value.equals(value))
            .findFirst();

        if (opt.isPresent()) {
            return opt.get();
        }

        throw new IllegalArgumentException("ForeignerLiveSts:" + value);
    }
}
