package com.chigirh.eh.fx.infra.mapper;

import com.chigirh.eh.fx.infra.model.mysql.OandaChartQueueEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OandaChartQueueMapper {
    int insert(@Param("entities") List<OandaChartQueueEntity> entities);

    List<OandaChartQueueEntity> find(@Param("limit") int limit);

    int deleteByKey(@Param("entities") List<OandaChartQueueEntity> entities);
}
