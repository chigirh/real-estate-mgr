package com.chigirh.eh.rem.infra.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.chigirh.eh.rem.infra.mapper"})
public class MapperConfig {
}
