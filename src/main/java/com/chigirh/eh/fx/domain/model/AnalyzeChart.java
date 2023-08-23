package com.chigirh.eh.fx.domain.model;

import lombok.Data;

@Data
public class AnalyzeChart {
    private ChartLink dateTime;
    private float high;
    private float open;
    private float bid;
    private float ask;
    private float low;
    private float rateChangeBid1Minutes;
    private float rateChangeBid5Minutes;
    private float fluctuationBid1Minutes;
    private float fluctuationBid5Minutes;
    private float rateChangeAsk1Minutes;
    private float rateChangeAsk5Minutes;
    private float fluctuationAsk1Minutes;
    private float fluctuationAsk5Minutes;
}
