package com.chigirh.eh.fx.web.model;

import com.chigirh.eh.fx.domain.model.ChartLink;
import com.chigirh.eh.fx.domain.model.OandaChart;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class PredictionChart {
    private final String id = UUID.randomUUID().toString();
    private ChartLink start = null;
    private ChartLink end = null;
    private List<OandaChart> oandaCharts = new ArrayList<>();

    private boolean isAborted = false;

    public void addChart(OandaChart oandaChart) {

        oandaCharts.add(oandaChart);

        var chartDateTime = oandaChart.getDateTime();
        if (start == null) {
            start = chartDateTime;
        }
        if (end == null) {
            end = chartDateTime;
        }

        if (chartDateTime.getDateTime().isBefore(start.getDateTime())) {
            start = chartDateTime;
        }

        if (chartDateTime.getDateTime().isAfter(end.getDateTime())) {
            end = chartDateTime;
        }
    }

    public void abortAnalyze() {
        isAborted = true;
    }
}
