package com.chigirh.eh.rem.domain.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CodeMasterType {

    FOREIGNER_LIVE_STS("01");

    private final String value;
}
