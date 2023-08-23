package com.chigirh.eh.fx.infra.mapper;

import com.chigirh.eh.fx.infra.model.mysql.OandaChartEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OandaChartMapper {
    int insert(@Param("entity") OandaChartEntity entity);

    int updateByKey(@Param("entity") OandaChartEntity entity);

    OandaChartEntity findByKey(@Param("id") String id);

    List<OandaChartEntity> findByBeforeRateChangeBetweens(@Param("lower") float lower, @Param("upper") float upper);

    OandaChartEntity findByBeforeRateChangeBetween(@Param("id") String id, @Param("lower") float lower, @Param("upper") float upper);
}
