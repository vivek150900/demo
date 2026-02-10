package com.dynamicrbac.service;

import org.springframework.stereotype.Service;

import com.dynamicrbac.model.Permission;
import com.dynamicrbac.model.Role;
import com.dynamicrbac.model.RolePermission;
import com.dynamicrbac.repository.PermissionRepository;
import com.dynamicrbac.repository.RolePermissionRepository;
import com.dynamicrbac.repository.RoleRepository;

@Service
public class RolePermissionService {

    private final RoleRepository roleRepo;
    private final PermissionRepository permissionRepo;
    private final RolePermissionRepository rolePermissionRepo;

    public RolePermissionService(RoleRepository roleRepo,
                                 PermissionRepository permissionRepo,
                                 RolePermissionRepository rolePermissionRepo) {
        this.roleRepo = roleRepo;
        this.permissionRepo = permissionRepo;
        this.rolePermissionRepo = rolePermissionRepo;
    }

    public void assignPermission(Long roleId, Long permissionId) {

        Role role = roleRepo.findById(roleId).orElseThrow();
        Permission permission = permissionRepo.findById(permissionId).orElseThrow();

        RolePermission rp = new RolePermission();
        rp.setRole(role);
        rp.setPermission(permission);

        rolePermissionRepo.save(rp);
    }
}
