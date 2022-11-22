package com.chigirh.eh.rem.domain.port;

import com.chigirh.eh.rem.domain.repository.realestate.RealEstateRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RealEstatePdfUpdatePort {

    private final RealEstateRepository realEstateRepository;

    @Transactional
    public Output useCase(Input input) {
        var model = realEstateRepository.fetchByReId(input.reId);

        model.setPdf(input.pdf);
        model.setUpdatedAt(LocalDateTime.now());
        var result = realEstateRepository.update(model);
        return new Output(result);
    }

    public record Input(String reId, String pdf) {
    }

    public record Output(int result) {
    }
}
