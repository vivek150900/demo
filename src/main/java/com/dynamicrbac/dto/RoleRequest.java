package com.dynamicrbac.dto;

import jakarta.validation.constraints.NotBlank;

public class RoleRequest {

    @NotBlank(message = "Role name must not be blank")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
