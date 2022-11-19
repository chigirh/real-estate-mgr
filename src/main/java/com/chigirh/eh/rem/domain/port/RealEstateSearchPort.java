package com.chigirh.eh.rem.domain.port;

import com.chigirh.eh.rem.domain.common.AreasConst;
import com.chigirh.eh.rem.domain.model.RealEstateSearchCondition;
import com.chigirh.eh.rem.domain.model.RealEstateSearchResult;
import com.chigirh.eh.rem.domain.repository.RealEstateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RealEstateSearchPort {

    private final RealEstateRepository realEstateRepository;

    @Transactional
    public Output useCase(Input input) {
        var condition = input.condition();
        if (AreasConst.DEFAULT.equals(condition.getArea())) {
            condition.setArea(null);
        }

        var results = realEstateRepository.fetchByCondition(condition);
        return new Output(results);
    }

    public record Input(RealEstateSearchCondition condition) {
    }

    public record Output(RealEstateSearchResult result) {
    }
}
