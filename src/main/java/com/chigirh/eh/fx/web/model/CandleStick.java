package com.chigirh.eh.fx.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CandleStick {
    @JsonProperty("x")
    private String dateTime;
    @JsonProperty("o")
    private float open;
    @JsonProperty("h")
    private float high;
    @JsonProperty("c")
    private float close;
    @JsonProperty("l")
    private float low;
    @JsonProperty("v")
    private float volume;
}