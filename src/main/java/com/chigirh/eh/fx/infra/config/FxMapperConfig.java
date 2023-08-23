package com.chigirh.eh.fx.infra.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.chigirh.eh.fx.infra.mapper"})
public class FxMapperConfig {
}
