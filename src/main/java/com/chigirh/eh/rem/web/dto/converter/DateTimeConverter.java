package com.chigirh.eh.rem.web.dto.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter {
    private static DateTimeFormatter atDate = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

    public static String convertAtDate(LocalDateTime localDateTime) {
        return atDate.format(localDateTime);
    }
}
