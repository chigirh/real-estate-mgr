package com.chigirh.eh.fx.infra.model.mysql;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OandaChartQueueEntity {
    private String uuid;
    private LocalDateTime dateTime;
    private float open;
    private float high;
    private float low;
    private float close;
    private float volume;
    private float volumeMa;
}
