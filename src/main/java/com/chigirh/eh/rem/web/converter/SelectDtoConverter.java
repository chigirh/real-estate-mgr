package com.chigirh.eh.rem.web.converter;

import com.chigirh.eh.rem.domain.model.common.CodeMaster;
import com.chigirh.eh.rem.web.dto.SelectDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SelectDtoConverter {
    public List<SelectDto> convert(List<CodeMaster> models) {
        return models.stream()
            .map(e -> new SelectDto(e.getName(), e.getValue()))
            .collect(Collectors.toList());
    }
}
