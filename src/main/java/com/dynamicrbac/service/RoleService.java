package com.dynamicrbac.service;

import org.springframework.stereotype.Service;

import com.dynamicrbac.model.Role;
import com.dynamicrbac.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(String name) {
        Role role = new Role();
        role.setName(name);
        return roleRepository.save(role);
    }
}
