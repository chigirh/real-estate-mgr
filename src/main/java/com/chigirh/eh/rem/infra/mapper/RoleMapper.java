package com.chigirh.eh.rem.infra.mapper;

import com.chigirh.eh.rem.infra.entity.RoleEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper {
    List<RoleEntity> findByUserId(@Param("userId") String userId);
}
