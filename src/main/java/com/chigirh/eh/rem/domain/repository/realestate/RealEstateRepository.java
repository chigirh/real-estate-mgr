package com.chigirh.eh.rem.domain.repository.realestate;

import com.chigirh.eh.rem.domain.model.realestate.RealEstate;
import com.chigirh.eh.rem.domain.model.realestate.RealEstateSearchCondition;

import com.chigirh.eh.rem.domain.model.realestate.RealEstateSearchResult;
import java.util.List;

public interface RealEstateRepository {
    void create(RealEstate model);

    int update(RealEstate model);

    List<String> fetchRegisterAreas();

    RealEstateSearchResult fetchByCondition(RealEstateSearchCondition condition);

    RealEstate fetchByReId(String reId);

    int deleteByReId(String reId);
}
