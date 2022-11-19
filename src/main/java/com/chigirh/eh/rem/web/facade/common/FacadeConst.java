package com.chigirh.eh.rem.web.facade.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeConst {

    public static final String PATH_FORMAT = "%s:%s";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public final class ForeignerLiveStatus {
        public static final String SET = "FLS_1";
        public static final String SET_BY_ROLE = "FLS_2";
    }

}
