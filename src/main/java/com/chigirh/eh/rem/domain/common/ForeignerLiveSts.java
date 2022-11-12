package com.chigirh.eh.rem.domain.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ForeignerLiveSts {

    UNCONFIRMED("1", "未確認"),
    ARROWED("2", "入居可能"),
    NOT_ARROWED("3", "入居不可");

    private final String value;
    private final String name;

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
