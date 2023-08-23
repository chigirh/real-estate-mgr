package com.chigirh.eh.fx.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Chart {
    private ChartLink chartLink;
    private Rate rate;
}
