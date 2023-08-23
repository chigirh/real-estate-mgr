package com.chigirh.eh.fx.infra.repository;

import com.chigirh.eh.fx.domain.model.ChartLink;
import com.chigirh.eh.fx.domain.model.OandaChart;
import com.chigirh.eh.fx.domain.repository.OandaChartRepository;
import com.chigirh.eh.fx.infra.mapper.OandaChartMapper;
import com.chigirh.eh.fx.infra.model.mysql.OandaChartEntity;
import com.chigirh.eh.rem.infra.config.DataAccess;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OandaChartRepositoryImpl implements OandaChartRepository {

    private final OandaChartMapper oandaChartMapper;

    @Override
    @DataAccess(process = "oanda_chart insert.")
    public void insert(OandaChart oandaChart) {
        var entity = new OandaChartEntity();

        var current = oandaChart.getDateTime();
        entity.setId(current.toString());
        entity.setDateTime(current.getDateTime());
        entity.setDay(current.getDay());

        entity.setOpen(oandaChart.getOpen());
        entity.setHigh(oandaChart.getHigh());
        entity.setLow(oandaChart.getLow());
        entity.setClose(oandaChart.getClose());
        entity.setVolume(oandaChart.getVolume());
        entity.setVolumeMa(oandaChart.getVolumeMa());

        var prev5Minutes = current.getPrev5Minutes();
        var prev5OandaChartEntity = oandaChartMapper.findByKey(prev5Minutes.toString());
        if (prev5OandaChartEntity != null) {
            entity.setRateChangeOpen5Minutes(calculateRateChangeOpen(oandaChart, prev5OandaChartEntity));
            entity.setRateChangeHigh5Minutes(calculateRateChangeHigh(oandaChart, prev5OandaChartEntity));
            entity.setRateChangeLow5Minutes(calculateRateChangeLow(oandaChart, prev5OandaChartEntity));
            entity.setRateChangeClose5Minutes(calculateRateChangeClose(oandaChart, prev5OandaChartEntity));
            entity.setFluctuationOpen5Minutes(calculateFluctuationOpen(oandaChart, prev5OandaChartEntity));
            entity.setFluctuationHigh5Minutes(calculateFluctuationHigh(oandaChart, prev5OandaChartEntity));
            entity.setFluctuationLow5Minutes(calculateFluctuationLow(oandaChart, prev5OandaChartEntity));
            entity.setFluctuationClose5Minutes(calculateFluctuationClose(oandaChart, prev5OandaChartEntity));
        }

        entity.setPrev5MinutesId(current.getPrev5Minutes().toString());
        entity.setNext5MinutesId(current.getNext5Minutes().toString());

        oandaChartMapper.insert(entity);

        var next5Minutes = current.getNext5Minutes();
        var next5OandaChartEntity = oandaChartMapper.findByKey(next5Minutes.toString());
        if (next5OandaChartEntity != null) {
            var next5OandaChart = toModel(next5OandaChartEntity);
            next5OandaChartEntity.setRateChangeOpen5Minutes(calculateRateChangeOpen(next5OandaChart, entity));
            next5OandaChartEntity.setRateChangeHigh5Minutes(calculateRateChangeHigh(next5OandaChart, entity));
            next5OandaChartEntity.setRateChangeLow5Minutes(calculateRateChangeLow(next5OandaChart, entity));
            next5OandaChartEntity.setRateChangeClose5Minutes(calculateRateChangeClose(next5OandaChart, entity));
            next5OandaChartEntity.setFluctuationOpen5Minutes(calculateFluctuationOpen(next5OandaChart, entity));
            next5OandaChartEntity.setFluctuationHigh5Minutes(calculateFluctuationHigh(next5OandaChart, entity));
            next5OandaChartEntity.setFluctuationLow5Minutes(calculateFluctuationLow(next5OandaChart, entity));
            next5OandaChartEntity.setFluctuationClose5Minutes(calculateFluctuationClose(next5OandaChart, entity));
            oandaChartMapper.updateByKey(next5OandaChartEntity);
        }
    }

    @Override
    @DataAccess(process = "oanda_chart insert.")
    public OandaChart fetchByKey(String id) {
        var entity = oandaChartMapper.findByKey(id);
        if (entity == null) {
            return null;
        }
        return toModel(entity);
    }

    @Override
    public List<OandaChart> fetchByBeforeRateChangeBetween(float approximationLower, float approximationUpper) {
        var entities = oandaChartMapper.findByBeforeRateChangeBetweens(approximationLower, approximationUpper);
        return entities.stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public OandaChart fetchByBeforeRateChangeBetween(String id, float approximationLower, float approximationUpper) {
        var entity = oandaChartMapper.findByBeforeRateChangeBetween(id, approximationLower, approximationUpper);
        if (entity == null) {
            return null;
        }
        return toModel(entity);
    }

    private OandaChart toModel(OandaChartEntity entity) {
        var model = new OandaChart();
        model.setDateTime(new ChartLink(entity.getDateTime()));
        model.setOpen(entity.getOpen());
        model.setHigh(entity.getHigh());
        model.setLow(entity.getLow());
        model.setClose(entity.getClose());
        model.setVolume(entity.getVolume());
        model.setVolumeMa(entity.getVolumeMa());
        return model;
    }

    private float calculateRateChangeOpen(OandaChart after, OandaChartEntity before) {
        return calculateRateChange(after.getOpen(), before.getOpen());
    }

    private float calculateRateChangeHigh(OandaChart after, OandaChartEntity before) {
        return calculateRateChange(after.getHigh(), before.getHigh());
    }

    private float calculateRateChangeLow(OandaChart after, OandaChartEntity before) {
        return calculateRateChange(after.getLow(), before.getLow());
    }

    private float calculateRateChangeClose(OandaChart after, OandaChartEntity before) {
        return calculateRateChange(after.getClose(), before.getClose());
    }

    private float calculateRateChange(float afterRaw, float beforeRaw) {
        var after = floor(afterRaw);
        var before = floor(beforeRaw);
        var rateChange = ((after / before) - 1) * 100;
        return rateChange;
    }

    private float calculateFluctuationOpen(OandaChart after, OandaChartEntity before) {
        return calculateFluctuation(after.getOpen(), before.getOpen());
    }

    private float calculateFluctuationHigh(OandaChart after, OandaChartEntity before) {
        return calculateFluctuation(after.getHigh(), before.getHigh());
    }

    private float calculateFluctuationLow(OandaChart after, OandaChartEntity before) {
        return calculateFluctuation(after.getLow(), before.getLow());
    }

    private float calculateFluctuationClose(OandaChart after, OandaChartEntity before) {
        return calculateFluctuation(after.getClose(), before.getClose());
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
