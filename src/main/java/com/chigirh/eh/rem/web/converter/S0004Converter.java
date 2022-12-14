package com.chigirh.eh.rem.web.converter;

import com.chigirh.eh.rem.domain.port.RealEstateSearchPort;
import com.chigirh.eh.rem.web.dto.S0004TableRow;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class S0004Converter {
    public List<S0004TableRow> convert(RealEstateSearchPort.Output output) {
        return output.result().getRecord().stream()
            .map(e -> new S0004TableRow(
                    e.getReId(),
                    e.getReName(),
                    e.getAddress(),
                    e.getRentPrice(),
                    e.getMgrCompanyName(),
                    e.getMgrCompanyTel(),
                    e.getForeignerLiveSts(),
                    DateTimeConverter.convertAtDateTime(e.getUpdatedAt()),
                    StringUtils.isEmpty(e.getPdf()) ? null : e.getPdf()
                )
            ).collect(Collectors.toList());
    }
}
