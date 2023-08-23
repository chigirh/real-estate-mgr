package com.chigirh.eh.fx.domain.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ChartAnalyzeCondition {
    private LocalDateTime analyzeDateTime;
    private int beforeScopeMinutes;
    private int afterScopeMinutes;
    private float tolerance;
}
