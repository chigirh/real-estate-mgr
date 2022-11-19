package com.chigirh.eh.rem.web.facade;

import com.chigirh.eh.rem.domain.common.ForeignerLiveSts;
import com.chigirh.eh.rem.domain.common.Role;
import com.chigirh.eh.rem.domain.service.common.CodeMasterService;
import com.chigirh.eh.rem.web.converter.SelectDtoConverter;
import com.chigirh.eh.rem.web.dto.RoleDto;
import com.chigirh.eh.rem.web.dto.SelectDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
@RequiredArgsConstructor
public class ForeignerLiveStatusFacade {

    public static final String ATTRIBUTE_NAME = "foreignerLiveStatuses";

    private final CodeMasterService codeMasterService;
    private final SelectDtoConverter converter;

    public void set(Model model) {
        var codes = codeMasterService.fetchForeignerLiveStatus();

        var selects = converter.convert(codes);

        model.addAttribute(ATTRIBUTE_NAME, selects);
    }

    public void setRoleUser(RoleDto role, Model model) {
        var codes = codeMasterService.fetchForeignerLiveStatus();

        List<SelectDto> selects;

        if (role.hasRole(Role.USER.getValue())) {
            var roleUserArrowed = codes.stream()
                .filter(e -> ForeignerLiveSts.ARROWED.getValue().equals(e.getValue()))
                .collect(Collectors.toList());
            selects = converter.convert(roleUserArrowed);
        } else {
            selects = converter.convert(codes);
        }

        model.addAttribute(ATTRIBUTE_NAME, selects);
    }
}
