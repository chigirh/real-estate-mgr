package com.chigirh.eh.fx.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import lombok.Getter;

@Getter
public class ChartLink {

    private static final DateTimeFormatter DT_FT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final LocalDateTime dateTime;
    private final LocalDate day;

    public ChartLink(LocalDateTime dateTime) {
        var dt = dateTime.truncatedTo(ChronoUnit.MINUTES);
        this.dateTime = dt;
        this.day = dateTime.toLocalDate();
    }

    public ChartLink(String dateTimeStr) {
        this(LocalDateTime.parse(dateTimeStr, DT_FT));
    }

    public ChartLink getPrev1Minutes() {
        return new ChartLink(dateTime.minusMinutes(1));
    }

    public ChartLink getPrev5Minutes() {
        return new ChartLink(dateTime.minusMinutes(5));
    }

    public ChartLink getNext1Minutes() {
        return new ChartLink(dateTime.plusMinutes(1));
    }

    public ChartLink getNext5Minutes() {
        return new ChartLink(dateTime.plusMinutes(5));
    }

    @Override
    public String toString() {
        return DT_FT.format(dateTime);
    }
}
