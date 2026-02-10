package com.dynamicrbac.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.dynamicrbac.dto.RoleRequest;
import com.dynamicrbac.model.Role;
import com.dynamicrbac.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasPermission(null,'CREATE_ROLE')")
    public Role create(@RequestBody @Valid RoleRequest request) {
        return service.createRole(request.getName());
    }
}
