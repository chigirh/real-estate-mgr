package com.chigirh.eh.rem.web.facade.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacadeConfig {


    @Bean(name = "facadeSettingsMap")
    public Map<String, Set<String>> facadeSettingsMap() {
        return new HashMap<>() {
            {
                put(FacadeConst.ForeignerLiveStatus.SET,
                    new HashSet<>(List.of(
                        "GET:/real-estate/register",
                        "GET:/real-estate/detail"
                    )));
                put(FacadeConst.ForeignerLiveStatus.SET_BY_ROLE,
                    new HashSet<>(List.of(
                        "GET:/real-estate/list",
                        "POST:/real-estate/list"
                    )));
            }
        };
    }

}
