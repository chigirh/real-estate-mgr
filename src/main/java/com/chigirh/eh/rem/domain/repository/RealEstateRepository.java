package com.chigirh.eh.rem.domain.repository;

import com.chigirh.eh.rem.domain.model.RealEstate;
import com.chigirh.eh.rem.domain.model.RealEstateSearchCondition;

import java.util.List;

public interface RealEstateRepository {
    void create(RealEstate model);

    int update(RealEstate model);

    List<String> fetchRegisterAreas();

    List<RealEstate> fetchByCondition(RealEstateSearchCondition condition);

    RealEstate fetchByReId(String reId);
}
