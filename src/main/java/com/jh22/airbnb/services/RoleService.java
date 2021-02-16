package com.jh22.airbnb.services;

import com.jh22.airbnb.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role findRoleById(long roleId);
}
