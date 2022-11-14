package com.chigirh.eh.rem.domain.port;

import com.chigirh.eh.rem.domain.model.RealEstate;
import com.chigirh.eh.rem.domain.repository.RealEstateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RealEstateCreatePort {

    private final RealEstateRepository realEstateRepository;

    @Transactional
    public void useCase(Input input) {
        var model = input.model;

        var reId = UUID.randomUUID().toString();
        model.setReId(reId);

        model.setUpdatedAt(LocalDateTime.now());

        realEstateRepository.create(model);
    }

    public record Input(RealEstate model) {
    }
}
