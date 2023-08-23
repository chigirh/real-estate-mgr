package com.chigirh.eh.fx.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
    "time",
    "open",
    "high",
    "low",
    "close",
    "Volume",
    "Volume MA"
})
public class OandaChartCsvData {
    @JsonProperty("time")
    private String dateTime;
    @JsonProperty("open")
    private Float open;
    @JsonProperty("high")
    private Float high;
    @JsonProperty("low")
    private Float low;
    @JsonProperty("close")
    private Float close;
    @JsonProperty("Volume")
    private Float volume;
    @JsonProperty("Volume MA")
    private Float volumeMa;
}