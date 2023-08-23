package com.chigirh.eh.fx.domain.service;

import com.chigirh.eh.fx.domain.model.ChartAnalyzeCondition;
import com.chigirh.eh.fx.domain.repository.AnalyzeChartRepository;
import com.chigirh.eh.fx.domain.repository.OandaChartRepository;
import com.chigirh.eh.fx.web.model.PredictionChart;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChartAnalyzeService {

    private final OandaChartRepository oandaChartRepository;
    private final AnalyzeChartRepository analyzeChartRepository;

    @Transactional
    public List<PredictionChart> usecase(ChartAnalyzeCondition condition) {

        var sequenceChart = analyzeChartRepository.fetchByLatestBefore(condition.getAnalyzeDateTime());

        var tolerance = condition.getTolerance();

        // before 5 approximation
        var rateChangeBid5Minutes = sequenceChart.getRateChangeBid5Minutes();
        var approximationLower = rateChangeBid5Minutes - tolerance;
        var approximationUpper = rateChangeBid5Minutes + tolerance;
        var approximationCharts = oandaChartRepository.fetchByBeforeRateChangeBetween(approximationLower, approximationUpper);

        if (CollectionUtils.isEmpty(approximationCharts)) {
            return new ArrayList<>();
        }

        var predictionCharts = new ArrayList<PredictionChart>();

        for (var approximationChart : approximationCharts) {
            var predicationChart = new PredictionChart();
            predictionCharts.add(predicationChart);
            predicationChart.addChart(approximationChart);
        }

        var beforeScopeMinutes = condition.getBeforeScopeMinutes();
        var scopeSequence = 5;

        while (scopeSequence < beforeScopeMinutes) {

            var sequenceId = sequenceChart.getDateTime().getPrev5Minutes().toString();
            sequenceChart = analyzeChartRepository.fetchByKey(sequenceId);

            if (sequenceChart == null) {
                break;
            }

            rateChangeBid5Minutes = sequenceChart.getRateChangeBid5Minutes();
            approximationLower = rateChangeBid5Minutes - tolerance;
            approximationUpper = rateChangeBid5Minutes + tolerance;

            for (var predicationChart : predictionCharts) {
                if (predicationChart.isAborted()) {
                    continue;
                }

                var approximationId = predicationChart.getStart().getPrev5Minutes().toString();
                var approximationChart =
                    oandaChartRepository.fetchByBeforeRateChangeBetween(approximationId, approximationLower, approximationUpper);
                if (approximationChart == null) {
                    predicationChart.abortAnalyze();
                    continue;
                }

                predicationChart.addChart(approximationChart);
            }
            scopeSequence += 5;
        }

        var afterScopeMinutes = condition.getAfterScopeMinutes();
        scopeSequence = 0;

        while (scopeSequence <= afterScopeMinutes) {
            for (var predicationChart : predictionCharts) {
                if (predicationChart.isAborted()) {
                    continue;
                }

                var nextId = predicationChart.getEnd().getNext5Minutes().toString();
                var approximationChart = oandaChartRepository.fetchByKey(nextId);
                if (approximationChart == null) {
                    predicationChart.abortAnalyze();
                    continue;
                }

                predicationChart.addChart(approximationChart);
            }
            scopeSequence += 5;
        }


        return predictionCharts.stream().filter(e -> !e.isAborted()).collect(Collectors.toList());

    }
}
