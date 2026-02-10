package com.dynamicrbac.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.dynamicrbac.service.UserRoleService;

@RestController
@RequestMapping("/users")
public class UserRoleController {

    private final UserRoleService service;

    public UserRoleController(UserRoleService service) {
        this.service = service;
    }

    @PostMapping("/{userId}/roles/{roleId}")
    @PreAuthorize("hasPermission(null,'ASSIGN_ROLE')")
    public String assignRole(@PathVariable Long userId,
                             @PathVariable Long roleId) {
        service.assignRole(userId, roleId);
        return "Role assigned to user";
    }
}
