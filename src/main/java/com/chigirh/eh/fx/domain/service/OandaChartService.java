package com.chigirh.eh.fx.domain.service;

import com.chigirh.eh.fx.domain.repository.OandaChartQueueRepository;
import com.chigirh.eh.fx.domain.repository.OandaChartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class OandaChartService {

    private final OandaChartQueueRepository oandaChartQueueRepository;
    private final OandaChartRepository oandaChartRepository;

    @Transactional
    public int usecase() {
        var oandaCharts = oandaChartQueueRepository.fetch(1000);
        if (CollectionUtils.isEmpty(oandaCharts)) {
            return 0;
        }
        oandaCharts.stream().forEach(oandaChartRepository::insert);
        oandaChartQueueRepository.deleteByKey(oandaCharts);
        return 100;
    }
}
