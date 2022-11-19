package com.chigirh.eh.rem.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealEstateSearchResult {
    private List<RealEstate> record;
    private int total;
}
