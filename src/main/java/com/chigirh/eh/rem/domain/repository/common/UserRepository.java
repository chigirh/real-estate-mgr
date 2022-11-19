package com.chigirh.eh.rem.domain.repository.common;

import com.chigirh.eh.rem.domain.common.Role;
import java.util.List;

public interface UserRepository {
    List<Role> fetchRolesByUserId(String userId);
}
