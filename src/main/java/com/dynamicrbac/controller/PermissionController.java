package com.dynamicrbac.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.dynamicrbac.dto.PermissionRequest;
import com.dynamicrbac.model.Permission;
import com.dynamicrbac.service.PermissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasPermission(null,'CREATE_PERMISSION')")
    public Permission create(@RequestBody @Valid PermissionRequest request) {
        return service.createPermission(request.getName());
    }
}
