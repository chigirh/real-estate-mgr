package com.chigirh.eh.fx.infra.model.mysql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RateEntity {
    private String id;
    private LocalDateTime dateTime;
    private LocalDate day;
    private float bid;
    private float ask;
    private float open;
    private float high;
    private float low;
    private String prev1MinutesId;
    private String prev5MinutesId;
    private String next1MinutesId;
    private String next5MinutesId;
    private float rateChangeBid1Minutes;
    private float rateChangeBid5Minutes;
    private float fluctuationBid1Minutes;
    private float fluctuationBid5Minutes;
    private float rateChangeAsk1Minutes;
    private float rateChangeAsk5Minutes;
    private float fluctuationAsk1Minutes;
    private float fluctuationAsk5Minutes;
}
