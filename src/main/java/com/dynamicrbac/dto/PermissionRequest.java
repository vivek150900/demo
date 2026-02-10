package com.dynamicrbac.dto;

import jakarta.validation.constraints.NotBlank;

public class PermissionRequest {

    @NotBlank(message = "Permission name must not be blank")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
