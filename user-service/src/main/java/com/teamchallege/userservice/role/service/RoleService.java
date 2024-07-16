package com.teamchallege.userservice.role.service;

import com.teamchallege.common.entities.user.Role;

public interface RoleService {
    Role findByName(String name);
}
