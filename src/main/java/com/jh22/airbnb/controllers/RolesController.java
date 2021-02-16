package com.jh22.airbnb.controllers;

import com.jh22.airbnb.models.Role;
import com.jh22.airbnb.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Transactional
@RequestMapping("roles")
public class RolesController {

    @Autowired
    RoleService roleService;

//    Find All roles
//    http://localhost:2019/roles/roles
    @GetMapping(value = "roles", produces = "application/json")
    public ResponseEntity<?> listRoles()
    {
        List<Role> allRoles = roleService.findAll();
        return new ResponseEntity<>(allRoles, HttpStatus.OK);
    }
//    Find role by roleid
//    http://localhost:2019/roles/role/1
    @GetMapping(value = "role/{roleId}", produces = "application/json")
    public ResponseEntity<?> findRoleById(@PathVariable long roleId)
    {
        Role r = roleService.findRoleById(roleId);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

}
