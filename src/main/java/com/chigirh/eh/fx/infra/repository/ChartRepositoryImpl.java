package com.chigirh.eh.fx.infra.repository;

import com.chigirh.eh.fx.domain.model.Chart;
import com.chigirh.eh.fx.domain.model.Rate;
import com.chigirh.eh.fx.domain.repository.ChartRepository;
import com.chigirh.eh.fx.infra.mapper.RateMapper;
import com.chigirh.eh.fx.infra.model.mysql.RateEntity;
import com.chigirh.eh.rem.infra.config.DataAccess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ChartRepositoryImpl implements ChartRepository {

    private final RateMapper rateMapper;

    @Override
    @DataAccess(process = "rate insert.")
    public void insert(Chart chart) {
        var entity = new RateEntity();

        var current = chart.getChartLink();
        var rate = chart.getRate();
        entity.setId(current.toString());
        entity.setDateTime(current.getDateTime());
        entity.setDay(current.getDay());

        entity.setBid(rate.getBid());
        entity.setAsk(rate.getAsk());
        entity.setOpen(rate.getOpen());
        entity.setHigh(rate.getHigh());
        entity.setLow(rate.getLow());

        var prev1Minutes = current.getPrev1Minutes();
        var prev1Rate = rateMapper.findByKey(prev1Minutes.toString());
        if (prev1Rate != null) {
            entity.setRateChangeBid1Minutes(calculateRateChangeBid(rate, prev1Rate));
            entity.setRateChangeAsk1Minutes(calculateRateChangeAsk(rate, prev1Rate));
            entity.setFluctuationBid1Minutes(calculateFluctuationBid(rate, prev1Rate));
            entity.setFluctuationAsk1Minutes(calculateFluctuationAsk(rate, prev1Rate));
        }

        var prev5Minutes = current.getPrev5Minutes();
        var prev5Rate = rateMapper.findByKey(prev5Minutes.toString());
        if (prev5Rate != null) {
            entity.setRateChangeBid5Minutes(calculateRateChangeBid(rate, prev5Rate));
            entity.setRateChangeAsk5Minutes(calculateRateChangeAsk(rate, prev5Rate));
            entity.setFluctuationBid5Minutes(calculateFluctuationBid(rate, prev5Rate));
            entity.setFluctuationAsk5Minutes(calculateFluctuationAsk(rate, prev5Rate));
        }

        entity.setPrev1MinutesId(current.getPrev1Minutes().toString());
        entity.setPrev5MinutesId(current.getPrev5Minutes().toString());
        entity.setNext1MinutesId(current.getNext1Minutes().toString());
        entity.setNext5MinutesId(current.getNext5Minutes().toString());

        rateMapper.insert(entity);
    }

    private float calculateRateChangeBid(Rate after, RateEntity before) {
        return calculateRateChange(after.getBid(), before.getBid());
    }

    private float calculateRateChangeAsk(Rate after, RateEntity before) {
        return calculateRateChange(after.getAsk(), before.getAsk());
    }

    private float calculateRateChange(float afterRaw, float beforeRaw) {
        var after = floor(afterRaw);
        var before = floor(beforeRaw);
        var rateChange = ((after / before) - 1) * 100;
        return rateChange;
    }

    private float calculateFluctuationBid(Rate after, RateEntity before) {
        return calculateFluctuation(after.getBid(), before.getBid());
    }

    private float calculateFluctuationAsk(Rate after, RateEntity before) {
        return calculateFluctuation(after.getAsk(), before.getAsk());
    }

    private float calculateFluctuation(float afterRaw, float beforeRaw) {
        var after = floor(afterRaw);
        var before = floor(beforeRaw);
        var fluctuation = after - before;
        return fluctuation / 100;
    }

    private float floor(float value) {
        return (float) Math.floor(value * 100);
    }
}
