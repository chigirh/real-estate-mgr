package com.chigirh.eh.rem.domain.port;

import com.chigirh.eh.rem.domain.model.RealEstate;
import com.chigirh.eh.rem.domain.repository.RealEstateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RealEstatcFetchPort {

    private final RealEstateRepository realEstateRepository;

    public Output useCase(Input input) {
        var result = realEstateRepository.fetchByReId(input.reId);
        return new Output(result);
    }

    public record Input(String reId) {
    }

    public record Output(RealEstate result) {
    }
}
