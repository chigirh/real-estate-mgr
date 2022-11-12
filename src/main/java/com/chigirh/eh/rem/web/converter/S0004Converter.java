package com.chigirh.eh.rem.web.converter;

import com.chigirh.eh.rem.domain.common.ForeignerLiveSts;
import com.chigirh.eh.rem.domain.port.RealEstateSearchPort;
import com.chigirh.eh.rem.web.dto.S0004TableRow;
import com.chigirh.eh.rem.web.dto.converter.DateTimeConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class S0004Converter {
    public List<S0004TableRow> convert(RealEstateSearchPort.Output output) {
        return output.results().stream()
            .map(e -> new S0004TableRow(
                    e.getReId(),
                    e.getReName(),
                    e.getAddress(),
                    e.getRentPrice(),
                    e.getMgrCompanyName(),
                    e.getMgrCompanyTel(),
                    ForeignerLiveSts.of(e.getForeignerLiveSts()).getName(),
                    DateTimeConverter.convertAtDate(e.getUpdatedAt())
                )
            ).collect(Collectors.toList());
    }
}
