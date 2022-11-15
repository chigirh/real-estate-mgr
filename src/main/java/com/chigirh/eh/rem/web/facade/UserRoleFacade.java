package com.chigirh.eh.rem.web.facade;

import com.chigirh.eh.rem.domain.common.Role;
import com.chigirh.eh.rem.domain.service.RoleService;
import com.chigirh.eh.rem.web.dto.RoleDto;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
@RequiredArgsConstructor
public class UserRoleFacade {

    private final RoleService roleService;

    public void setRoles(OidcUser user, Model model) {
        var roles = roleService.fetchByUserId(user.getEmail());

        var roleSet = roles.stream().map(Role::getValue).collect(Collectors.toSet());
        roleSet.add(Role.USER.getValue());

        model.addAttribute("role", new RoleDto(roleSet));

    }
}
