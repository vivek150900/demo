package com.dynamicrbac.service;

import org.springframework.stereotype.Service;

import com.dynamicrbac.model.UserRole;
import com.dynamicrbac.repository.RoleRepository;
import com.dynamicrbac.repository.UserRepository;
import com.dynamicrbac.repository.UserRoleRepository;

@Service
public class UserRoleService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final UserRoleRepository userRoleRepo;

    public UserRoleService(UserRepository u,
                           RoleRepository r,
                           UserRoleRepository ur) {
        this.userRepo = u;
        this.roleRepo = r;
        this.userRoleRepo = ur;
    }

    public void assignRole(Long userId, Long roleId) {
        UserRole ur = new UserRole();
        ur.setUser(userRepo.findById(userId).orElseThrow());
        ur.setRole(roleRepo.findById(roleId).orElseThrow());
        userRoleRepo.save(ur);
    }
}
