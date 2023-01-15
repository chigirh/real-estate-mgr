package com.chigirh.eh.rem.infra.mapper;

import com.chigirh.eh.rem.infra.entity.RealEstateEntity;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RealEstateMapper {
    int insert(@Param("entity") RealEstateEntity entity);

    int updateByKey(@Param("entity") RealEstateEntity entity);

    RealEstateEntity findByKey(@Param("reId") String reId);

    int deleteByKey(@Param("reId") String reId);

    List<RealEstateEntity> findAll(@Param("offset") int offset);

    List<RealEstateEntity> findByCondition(@Param("condition") Condition condition);

    int countByCondition(@Param("condition") Condition condition);

    @Data
    @NoArgsConstructor
    class Condition {
        private String reName;
        private String mgrCompanyName;
        private String area;
        private Integer rentPrice;
        private String foreignerLiveSts;
        private int offset;
        private int limit;
    }
}
