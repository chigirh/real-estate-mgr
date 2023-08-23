package com.chigirh.eh.fx.infra.model.restapi;

import lombok.Data;

@Data
public class RateGetRecord {
    private float high;
    private float open;
    private float bid;
    private String currencyPairCode;
    private float ask;
    private float low;
}
