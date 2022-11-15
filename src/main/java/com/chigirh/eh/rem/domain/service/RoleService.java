package com.chigirh.eh.rem.domain.service;

import com.chigirh.eh.rem.domain.common.Role;
import com.chigirh.eh.rem.domain.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final UserRepository userRepository;

    @Transactional
    public List<Role> fetchByUserId(String userId) {
        return userRepository.fetchRolesByUserId(userId);
    }
}
