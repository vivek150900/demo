package com.dynamicrbac.service;

import org.springframework.stereotype.Service;

import com.dynamicrbac.model.Permission;
import com.dynamicrbac.repository.PermissionRepository;

@Service
public class PermissionService {

    private final PermissionRepository repo;

    public PermissionService(PermissionRepository repo) {
        this.repo = repo;
    }

    public Permission createPermission(String name) {
        Permission p = new Permission();
        p.setName(name);
        return repo.save(p);
    }
}
