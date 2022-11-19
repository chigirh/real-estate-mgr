package com.chigirh.eh.rem.domain.port;

import com.chigirh.eh.rem.domain.model.realestate.RealEstate;
import com.chigirh.eh.rem.domain.repository.realestate.RealEstateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RealEstatcFetchPort {

    private final RealEstateRepository realEstateRepository;

    @Transactional
    public Output useCase(Input input) {
        var result = realEstateRepository.fetchByReId(input.reId);
        return new Output(result);
    }

    public record Input(String reId) {
    }

    public record Output(RealEstate result) {
    }
}
