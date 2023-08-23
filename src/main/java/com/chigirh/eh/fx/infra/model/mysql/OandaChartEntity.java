package com.chigirh.eh.fx.infra.model.mysql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OandaChartEntity {
    private String id;
    private LocalDateTime dateTime;
    private LocalDate day;
    private float open;
    private float high;
    private float low;
    private float close;
    private float volume;
    private float volumeMa;
    private String prev5MinutesId;
    private String next5MinutesId;
    private float rateChangeOpen5Minutes;
    private float fluctuationOpen5Minutes;
    private float rateChangeClose5Minutes;
    private float fluctuationClose5Minutes;
    private float rateChangeHigh5Minutes;
    private float fluctuationHigh5Minutes;
    private float rateChangeLow5Minutes;
    private float fluctuationLow5Minutes;
}
