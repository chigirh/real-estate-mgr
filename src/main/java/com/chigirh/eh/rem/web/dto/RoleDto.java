package com.chigirh.eh.rem.web.dto;

import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleDto {
    private final Set<String> roles;

    public boolean hasRole(String role) {
        return roles.contains(role);
    }
}
