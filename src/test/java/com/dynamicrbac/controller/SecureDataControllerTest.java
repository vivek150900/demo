package com.dynamicrbac.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SecureDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user")
    void userCanAccessSecureData() throws Exception {
        mockMvc.perform(get("/secure-data"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "random")
    void randomUserDeniedSecureData() throws Exception {
        mockMvc.perform(get("/secure-data"))
                .andExpect(status().isForbidden());
    }
}
