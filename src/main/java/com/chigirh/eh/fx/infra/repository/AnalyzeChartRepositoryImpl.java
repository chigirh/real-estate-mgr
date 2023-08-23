package com.chigirh.eh.fx.infra.repository;

import com.chigirh.eh.fx.domain.model.AnalyzeChart;
import com.chigirh.eh.fx.domain.model.ChartLink;
import com.chigirh.eh.fx.domain.repository.AnalyzeChartRepository;
import com.chigirh.eh.fx.infra.mapper.RateMapper;
import com.chigirh.eh.fx.infra.model.mysql.RateEntity;
import com.chigirh.eh.rem.infra.config.DataAccess;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AnalyzeChartRepositoryImpl implements AnalyzeChartRepository {

    private final RateMapper rateMapper;

    @Override
    @DataAccess(process = "rate fetch")
    public AnalyzeChart fetchByKey(String id) {
        var entity = rateMapper.findByKey(id);
        if (entity == null) {
            return null;
        }
        return toModel(entity);
    }

    @Override
    @DataAccess(process = "rate fetch")
    public AnalyzeChart fetchByLatestBefore(LocalDateTime dateTime) {
        var entity = rateMapper.findByLatestBefore(dateTime);
        return toModel(entity);
    }

    private AnalyzeChart toModel(RateEntity entity) {
        var model = new AnalyzeChart();
        model.setDateTime(new ChartLink(entity.getDateTime()));
        model.setHigh(entity.getHigh());
        model.setOpen(entity.getOpen());
        model.setBid(entity.getBid());
        model.setAsk(entity.getAsk());
        model.setLow(entity.getLow());
        model.setRateChangeBid5Minutes(entity.getRateChangeBid5Minutes());
        return model;
    }
}
