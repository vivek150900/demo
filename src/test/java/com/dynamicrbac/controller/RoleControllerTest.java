package com.dynamicrbac.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // ADMIN should be allowed
    @Test
    @WithMockUser(username = "admin")
    void adminCanCreateRole() throws Exception {
        mockMvc.perform(post("/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"TEST_ROLE\"}"))
                .andExpect(status().isOk());
    }

    // USER should be forbidden
    @Test
    @WithMockUser(username = "user")
    void userCannotCreateRole() throws Exception {
        mockMvc.perform(post("/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"FAIL_ROLE\"}"))
                .andExpect(status().isForbidden());
    }
}
