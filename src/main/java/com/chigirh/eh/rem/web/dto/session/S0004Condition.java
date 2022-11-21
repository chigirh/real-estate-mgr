package com.chigirh.eh.rem.web.dto.session;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class S0004Condition {
    private String reName;
    private String area;
    private Integer rentPrice;
    private String foreignerLiveSts;
    // pageable
    private int pageNumber = 0;
    // init
    private boolean isSearched = false;
}
