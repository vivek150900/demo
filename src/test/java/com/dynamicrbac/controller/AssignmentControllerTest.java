package com.dynamicrbac.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AssignmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin")
    void adminCanAssignRoleToUser() throws Exception {
        mockMvc.perform(post("/users/1/roles/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user")
    void userCannotAssignRole() throws Exception {
        mockMvc.perform(post("/users/1/roles/1"))
                .andExpect(status().isForbidden());
    }
}
