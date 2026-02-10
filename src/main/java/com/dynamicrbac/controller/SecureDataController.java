package com.dynamicrbac.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class SecureDataController {

    @GetMapping("/secure-data")
    @PreAuthorize("hasPermission(null,'READ_SECURE_DATA')")
    public String secureData() {
        return "This is secured data";
    }
}
