package com.chigirh.eh.fx.infra.repository;

import com.chigirh.eh.fx.domain.model.ChartLink;
import com.chigirh.eh.fx.domain.model.OandaChart;
import com.chigirh.eh.fx.domain.repository.OandaChartQueueRepository;
import com.chigirh.eh.fx.infra.mapper.OandaChartQueueMapper;
import com.chigirh.eh.fx.infra.model.mysql.OandaChartQueueEntity;
import com.chigirh.eh.rem.infra.config.DataAccess;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OandaChartQueueRepositoryImpl implements OandaChartQueueRepository {

    private final OandaChartQueueMapper oandaChartQueueMapper;


    @Override
    @DataAccess(process = "oanda_chart_queue insert.")
    public void insert(List<OandaChart> oandaCharts) {
        var entities = new ArrayList<OandaChartQueueEntity>();

        for (int i = 0; i < oandaCharts.size(); i++) {
            entities.add(toEntity(oandaCharts.get(i)));

            if (100 == entities.size()) {
                oandaChartQueueMapper.insert(entities);
                entities.clear();
            }
        }

        if (!CollectionUtils.isEmpty(entities)) {
            oandaChartQueueMapper.insert(entities);
        }
    }

    @Override
    @DataAccess(process = "oanda_chart_queue fetch.")
    public List<OandaChart> fetch(int limit) {
        var entities = oandaChartQueueMapper.find(limit);
        return entities.stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    @DataAccess(process = "oanda_chart_queue delete.")
    public void deleteByKey(List<OandaChart> oandaCharts) {
        var entities = oandaCharts.stream().map(this::toEntity).collect(Collectors.toList());
        oandaChartQueueMapper.deleteByKey(entities);
    }

    private OandaChartQueueEntity toEntity(OandaChart model) {
        var entity = new OandaChartQueueEntity();

        var uuid = model.getUuid();
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }

        entity.setUuid(uuid);
        entity.setDateTime(model.getDateTime().getDateTime());
        entity.setOpen(model.getOpen());
        entity.setHigh(model.getHigh());
        entity.setLow(model.getLow());
        entity.setClose(model.getClose());
        entity.setVolume(model.getVolume());
        entity.setVolumeMa(model.getVolumeMa());
        return entity;
    }

    private OandaChart toModel(OandaChartQueueEntity entity) {
        var model = new OandaChart();
        model.setUuid(entity.getUuid());
        model.setDateTime(new ChartLink(entity.getDateTime()));
        model.setOpen(entity.getOpen());
        model.setHigh(entity.getHigh());
        model.setLow(entity.getLow());
        model.setClose(entity.getClose());
        model.setVolume(entity.getVolume());
        model.setVolumeMa(entity.getVolumeMa());
        return model;
    }
}
