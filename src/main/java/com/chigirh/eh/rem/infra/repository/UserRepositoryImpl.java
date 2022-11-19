package com.chigirh.eh.rem.infra.repository;

import com.chigirh.eh.rem.domain.common.Role;
import com.chigirh.eh.rem.domain.repository.common.UserRepository;
import com.chigirh.eh.rem.infra.config.DataAccess;
import com.chigirh.eh.rem.infra.mapper.RoleMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final RoleMapper roleMapper;

    @Override
    @DataAccess(process = "roles find.")
    public List<Role> fetchRolesByUserId(String userId) {
        var entities = roleMapper.findByUserId(userId);

        return entities.stream().map(e -> Role.of(e.getRole())).collect(Collectors.toList());
    }
}
