package com.chigirh.eh.rem.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class S0004TableRow {
    private String reId;
    private String reName;
    private String address;
    private int rentPrice;
    private String mgrCompanyName;
    private String mgrCompanyTel;
    private String foreignerLiveSts;
    private String updatedAt;
}
