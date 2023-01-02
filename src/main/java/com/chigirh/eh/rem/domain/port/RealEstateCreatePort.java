package com.chigirh.eh.rem.domain.port;

import com.chigirh.eh.rem.domain.model.realestate.RealEstate;
import com.chigirh.eh.rem.domain.repository.realestate.RealEstateRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RealEstateCreatePort {

    private final RealEstateRepository realEstateRepository;

    @Transactional
    public Output useCase(Input input) {
        var model = input.model;

        var reId = UUID.randomUUID().toString();
        model.setReId(reId);

        model.setUpdatedAt(LocalDateTime.now());

        realEstateRepository.create(model);

        return new Output(reId);
    }

    public record Input(RealEstate model) {
    }

    // result is reId
    public record Output(String result) {
    }
}
