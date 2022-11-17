package com.chigirh.eh.rem.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Data
@NoArgsConstructor
public class S0004Form {
    private String reName;
    private String area;
    private Integer rentPrice;
    private String foreignerLiveSts;
}
