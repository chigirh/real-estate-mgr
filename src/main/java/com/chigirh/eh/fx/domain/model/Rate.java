package com.chigirh.eh.fx.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rate {
    private float high;
    private float open;
    private float bid;
    private String currencyPairCode;
    private float ask;
    private float low;
}
