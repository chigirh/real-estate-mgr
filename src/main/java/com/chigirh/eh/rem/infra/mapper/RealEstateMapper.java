package com.chigirh.eh.rem.infra.mapper;

import com.chigirh.eh.rem.infra.entity.RealEstateEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RealEstateMapper {
    int insert(@Param("entity") RealEstateEntity entity);

    int updateByKey(@Param("entity") RealEstateEntity entity);

    RealEstateEntity findByKey(@Param("reId") String reId);

    List<RealEstateEntity> findByCondition(@Param("condition") Condition condition);

    @Data
    @NoArgsConstructor
    class Condition {
        private String reName;
        private String area;
        private Integer rentPrice;
        private String foreignerLiveSts;
    }
}
