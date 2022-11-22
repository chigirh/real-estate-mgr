package com.chigirh.eh.rem.domain.port;

import com.chigirh.eh.rem.domain.repository.realestate.RealEstateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RealEstatcDeletePort {

    private final RealEstateRepository realEstateRepository;

    @Transactional
    public Output useCase(Input input) {
        var result = realEstateRepository.deleteByReId(input.reId);
        return new Output(result);
    }

    public record Input(String reId) {
    }

    public record Output(int result) {
    }
}
