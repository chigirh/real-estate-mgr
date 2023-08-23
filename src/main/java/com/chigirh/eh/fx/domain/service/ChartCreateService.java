package com.chigirh.eh.fx.domain.service;

import com.chigirh.eh.fx.domain.model.Chart;
import com.chigirh.eh.fx.domain.model.ChartLink;
import com.chigirh.eh.fx.domain.repository.ChartRepository;
import com.chigirh.eh.fx.domain.repository.RateRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChartCreateService {

    private final RateRepository rateRepository;
    private final ChartRepository chartRepository;

    @Transactional
    public void usecase() {

        var chartLink = new ChartLink(LocalDateTime.now());
        var rate = rateRepository.fetchUsdJpy();

        var chart = new Chart(chartLink, rate);

        chartRepository.insert(chart);

    }
}
