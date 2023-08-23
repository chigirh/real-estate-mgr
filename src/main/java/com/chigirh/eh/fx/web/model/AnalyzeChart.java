package com.chigirh.eh.fx.web.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalyzeChart {
    private String title;
    private List<CandleStick> analyzeChart = new ArrayList<>();
}