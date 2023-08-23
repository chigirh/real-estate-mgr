package com.chigirh.eh.fx.infra.mapper;

import com.chigirh.eh.fx.infra.model.mysql.RateEntity;
import java.time.LocalDateTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RateMapper {
    int insert(@Param("entity") RateEntity entity);

    RateEntity findByKey(@Param("id") String id);

    RateEntity findByLatestBefore(@Param("dateTime") LocalDateTime dateTime);
}
