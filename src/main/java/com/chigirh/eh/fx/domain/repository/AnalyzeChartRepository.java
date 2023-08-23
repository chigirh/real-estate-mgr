package com.chigirh.eh.fx.domain.repository;

import com.chigirh.eh.fx.domain.model.AnalyzeChart;
import java.time.LocalDateTime;

public interface AnalyzeChartRepository {
    AnalyzeChart fetchByKey(String id);

    AnalyzeChart fetchByLatestBefore(LocalDateTime dateTime);
}
