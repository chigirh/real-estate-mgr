package com.chigirh.eh.rem.infra.mapper;

import com.chigirh.eh.rem.infra.entity.RealEstateAreaEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RealEstateAreaMapper {
    int bulkInsert(@Param("entities") List<RealEstateAreaEntity> entities);

    int deleteByReId(@Param("reId") String reId);

    List<RealEstateAreaEntity> findByReId(@Param("reId") String reId);

    List<String> findAllAreas();
}
