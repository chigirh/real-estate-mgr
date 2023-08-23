package com.chigirh.eh.fx.web.process;

import com.chigirh.eh.fx.domain.service.OandaChartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OandaChartPathProcess {

    private final OandaChartService service;

    @Scheduled(cron = "*/15 * * * * *")
    public void process() {
        var result = service.usecase();
    }
}
