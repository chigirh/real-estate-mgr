package com.chigirh.eh.rem.web.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Notice {
    private String info;
    private String success;
    private String warn;
    private String error;
}
