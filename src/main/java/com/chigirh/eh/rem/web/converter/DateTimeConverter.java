package com.chigirh.eh.rem.web.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter {
    private static DateTimeFormatter atDate = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    public static String convertAtDateTime(LocalDateTime localDateTime) {
        return atDate.format(localDateTime);
    }
}
