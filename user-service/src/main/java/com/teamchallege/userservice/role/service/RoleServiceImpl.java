package com.teamchallege.userservice.role.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.teamchallege.common.entities.user.Role;
import com.teamchallege.userservice.role.entity.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElse(null);
    }
}
