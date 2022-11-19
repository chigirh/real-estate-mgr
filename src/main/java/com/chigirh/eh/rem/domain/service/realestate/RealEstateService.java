package com.chigirh.eh.rem.domain.service.realestate;

import com.chigirh.eh.rem.domain.common.AreasConst;
import com.chigirh.eh.rem.domain.repository.realestate.RealEstateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RealEstateService {

    private final RealEstateRepository realEstateRepository;

    public List<String> fetchAreas() {
        var areas = new ArrayList<String>();
        areas.add(AreasConst.DEFAULT);
        areas.addAll(realEstateRepository.fetchRegisterAreas());
        return areas;
    }
}
