package com.chigirh.eh.rem.domain.port;

import com.chigirh.eh.rem.domain.model.RealEstate;
import com.chigirh.eh.rem.domain.repository.RealEstateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RealEstateUpdatePort {

    private final RealEstateRepository realEstateRepository;

    public Output useCase(Input input) {
        var model = input.model;
        model.setUpdatedAt(LocalDateTime.now());

        var result = realEstateRepository.update(model);

        return new Output(result);
    }

    public record Input(RealEstate model) {
    }

    public record Output(int result) {
    }
}
