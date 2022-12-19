package com.kozeljko.expensetracker.controller;

import com.kozeljko.expensetracker.configuration.SecurityConfig;
import com.kozeljko.expensetracker.entity.User;
import com.kozeljko.expensetracker.service.UserService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Import(SecurityConfig.class)
@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    // Init admin

    @Test
    public void shouldInitializeAdminUser() throws Exception {
        Mockito.when(userService.isAdminInitialized()).thenReturn(false);

        var mockUser = new User();
        mockUser.setUsername("testuser");
        Mockito.when(userService.createAdminUser()).thenReturn(mockUser);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/users/init-admin"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Is.is("testuser")));
    }

    @Test
    public void shouldFailInitializingExistingAdminUser() throws Exception {
        Mockito.when(userService.isAdminInitialized()).thenReturn(true);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/users/init-admin"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
