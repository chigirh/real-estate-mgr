package com.chigirh.eh.fx.web.process;

import com.chigirh.eh.fx.domain.service.ChartCreateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsdPjyGetProcess {

    private final ChartCreateService service;

    @Scheduled(cron = "0 */1 * * * *")
    public void process() {
        service.usecase();
    }
}
