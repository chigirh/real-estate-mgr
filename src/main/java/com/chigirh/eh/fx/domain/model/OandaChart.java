package com.chigirh.eh.fx.domain.model;

import lombok.Data;

@Data
public class OandaChart {
    private String uuid;
    private ChartLink dateTime;
    private float open;
    private float high;
    private float low;
    private float close;
    private float volume;
    private float volumeMa;
}
