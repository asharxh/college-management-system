package com.ashar.collegemanagementsystem.controller;

import com.ashar.collegemanagementsystem.TestSecurityConfig;
import com.ashar.collegemanagementsystem.dto.request.LoginDTO;
import com.ashar.collegemanagementsystem.dto.request.StudentRegisterDTO;
import com.ashar.collegemanagementsystem.dto.response.ApiResponse;
import com.ashar.collegemanagementsystem.security.JwtUtil;
import com.ashar.collegemanagementsystem.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(TestSecurityConfig.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void testRegisterStudent() throws Exception {

        StudentRegisterDTO request = new StudentRegisterDTO();
        request.setName("Rahul");
        request.setEmail("rahul@test.com");
        request.setPhone("9999999999");
        request.setPassword("Password@123");
        request.setBranch("CSE");
        request.setEnrollmentYear(2024);

        ApiResponse response = ApiResponse.builder()
                .success(true)
                .message("Student registered successfully")
                .build();

        Mockito.when(authService.registerStudent(Mockito.any()))
                .thenReturn(response);

        mockMvc.perform(post("/api/auth/student/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void testLoginStudent() throws Exception {

        LoginDTO request = new LoginDTO();
        request.setEmail("rahul@test.com");
        request.setPassword("Password@123");

        ApiResponse response = ApiResponse.builder()
                .success(true)
                .message("Login successful")
                .build();

        Mockito.when(authService.loginStudent(Mockito.any()))
                .thenReturn(response);

        mockMvc.perform(post("/api/auth/student/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Login successful"));
    }

    @Test
    void testForgotPassword() throws Exception {

        String json = """
                {
                  "email": "test@test.com"
                }
                """;

        ApiResponse response = ApiResponse.builder()
                .success(true)
                .message("Reset link sent to email")
                .build();

        Mockito.when(authService.forgotPassword(Mockito.any()))
                .thenReturn(response);

        mockMvc.perform(post("/api/auth/forgot-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}