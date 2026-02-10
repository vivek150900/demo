package com.dynamicrbac.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.dynamicrbac.service.RolePermissionService;

@RestController
@RequestMapping("/roles")
public class RolePermissionController {

    private final RolePermissionService service;

    public RolePermissionController(RolePermissionService service) {
        this.service = service;
    }

    @PostMapping("/{roleId}/permissions/{permissionId}")
    @PreAuthorize("hasPermission(null,'ASSIGN_PERMISSION')")
    public String assignPermissionToRole(@PathVariable Long roleId,
                                         @PathVariable Long permissionId) {
        service.assignPermission(roleId, permissionId);
        return "Permission assigned to role";
    }
}
