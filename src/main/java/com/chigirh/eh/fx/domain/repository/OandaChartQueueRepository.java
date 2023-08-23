package com.chigirh.eh.fx.domain.repository;

import com.chigirh.eh.fx.domain.model.OandaChart;
import java.util.List;

public interface OandaChartQueueRepository {
    void insert(List<OandaChart> oandaCharts);

    List<OandaChart> fetch(int limit);

    void deleteByKey(List<OandaChart> oandaCharts);
}
