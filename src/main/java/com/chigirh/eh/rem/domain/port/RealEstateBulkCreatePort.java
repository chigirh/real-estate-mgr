package com.chigirh.eh.rem.domain.port;

import com.chigirh.eh.rem.domain.model.realestate.RealEstate;
import com.chigirh.eh.rem.domain.repository.realestate.RealEstateRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RealEstateBulkCreatePort {

    private final RealEstateRepository realEstateRepository;

    @Transactional
    public void useCase(Input input) {
        var models = input.models;

        for (var model : models) {
            var reId = UUID.randomUUID().toString();
            model.setReId(reId);
            model.setUpdatedAt(LocalDateTime.now());
            realEstateRepository.create(model);
        }
    }

    public record Input(List<RealEstate> models) {
    }
}
