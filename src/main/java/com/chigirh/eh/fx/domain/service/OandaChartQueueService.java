package com.chigirh.eh.fx.domain.service;

import com.chigirh.eh.fx.domain.model.OandaChart;
import com.chigirh.eh.fx.domain.repository.OandaChartQueueRepository;
import com.chigirh.eh.rem.web.dto.session.Notice;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OandaChartQueueService {

    private final OandaChartQueueRepository oandaChartQueueRepository;

    @Transactional
    public void usecase(List<OandaChart> oandaCharts) {
        oandaChartQueueRepository.insert(oandaCharts);

    }
}
