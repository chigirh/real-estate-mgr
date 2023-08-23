package com.chigirh.eh.fx.domain.repository;

import com.chigirh.eh.fx.domain.model.OandaChart;
import java.util.List;

public interface OandaChartRepository {
    void insert(OandaChart oandaChart);

    OandaChart fetchByKey(String id);

    List<OandaChart> fetchByBeforeRateChangeBetween(float approximationLower, float approximationUpper);

    OandaChart fetchByBeforeRateChangeBetween(String id, float approximationLower, float approximationUpper);
}
