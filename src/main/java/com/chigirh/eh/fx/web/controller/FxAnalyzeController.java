package com.chigirh.eh.fx.web.controller;

import com.chigirh.eh.fx.domain.model.ChartAnalyzeCondition;
import com.chigirh.eh.fx.domain.model.OandaChart;
import com.chigirh.eh.fx.domain.service.ChartAnalyzeService;
import com.chigirh.eh.fx.web.model.AnalyzeChart;
import com.chigirh.eh.fx.web.model.CandleStick;
import com.chigirh.eh.rem.web.dto.session.Notice;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FxAnalyzeController {

    private final Notice notice;

    private final ChartAnalyzeService chartAnalyzeService;

    @GetMapping("/fx/analyze")
    public String submit(
        @AuthenticationPrincipal OidcUser user,
        Model model
    ) {

        var condition = new ChartAnalyzeCondition();
        condition.setAnalyzeDateTime(LocalDateTime.of(2023, 8, 23, 11, 55, 0));
        condition.setBeforeScopeMinutes(30);
        condition.setAfterScopeMinutes(120);
        condition.setTolerance(0.007F);

        var predicationCharts = chartAnalyzeService.usecase(condition);

        var analyzeCharts = new ArrayList<AnalyzeChart>();
        for (var predicationChart : predicationCharts) {
            var start = predicationChart.getStart().getDateTime();
            var end = predicationChart.getEnd().getDateTime();
            var title = String.format("%s ～ %s", start, end);
            analyzeCharts.add(createAnalyzeChart(title, predicationChart.getOandaCharts()));
        }

        model.addAttribute("analyzeCharts", analyzeCharts);

        return "fx/index";
    }

    private AnalyzeChart createAnalyzeChart(String title, List<OandaChart> oandaCharts) {
        var analyzeChart = oandaCharts.stream()
            .sorted(Comparator.comparing(c -> c.getDateTime().getDateTime()))
            .map(e -> new CandleStick(e.getDateTime().toString(), e.getOpen(), e.getHigh(), e.getClose(), e.getLow(), e.getVolume()))
            .collect(Collectors.toList());

        return new AnalyzeChart(title, analyzeChart);
    }
}
