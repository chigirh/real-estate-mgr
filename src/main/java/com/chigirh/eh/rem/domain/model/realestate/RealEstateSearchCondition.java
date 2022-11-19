package com.chigirh.eh.rem.domain.model.realestate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealEstateSearchCondition {
    private String reName;
    private String area;
    private Integer rentPrice;
    private String foreignerLiveSts;
    private int offset;
    private int size;
}
