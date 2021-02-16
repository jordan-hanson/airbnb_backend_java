package com.jh22.airbnb.services;

import com.jh22.airbnb.exceptions.ResourceNotFoundException;
import com.jh22.airbnb.models.Role;
import com.jh22.airbnb.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository rolerepos;

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();

        rolerepos.findAll()
                .iterator()
                .forEachRemaining(roles::add);
        return roles;
    }

    @Override
    public Role findRoleById(long roleId) {
        return rolerepos.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role id" + roleId + "Not Found."));

    }
}
